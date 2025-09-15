package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.mapper.MagmaAnnoMapper;
import com.spring.boot.mapper.TraitMapper;
import com.spring.boot.mapper.TraitVariantMapMapper;
import com.spring.boot.pojo.MagmaAnno;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.TraitVariantMap;
import com.spring.boot.service.VariantDetailService;
import com.spring.boot.util.model.vo.echarts.*;
import com.spring.boot.util.util.ListUtil;
import com.spring.boot.util.util.NullUtil;
import com.spring.boot.util.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.spring.boot.util.util.ApplicationUtil.getElementSignalId;
import static com.spring.boot.util.util.ApplicationUtil.getTraitSignalId;
import static com.spring.boot.util.util.number.MathUtil.generateCircleRandomCoordinates;

/**
 * @author Administrator
 */
@CacheConfig(cacheNames = "variant_detail", keyGenerator = "cacheKeyGenerator")
@Service
public class VariantDetailServiceImpl implements VariantDetailService {

    private TraitMapper traitMapper;
    private TraitVariantMapMapper traitVariantMapMapper;
    private MagmaAnnoMapper magmaAnnoMapper;

    public VariantDetailServiceImpl() {
    }

    @Autowired
    public VariantDetailServiceImpl(TraitMapper traitMapper, TraitVariantMapMapper traitVariantMapMapper, MagmaAnnoMapper magmaAnnoMapper) {
        this.traitMapper = traitMapper;
        this.traitVariantMapMapper = traitVariantMapMapper;
        this.magmaAnnoMapper = magmaAnnoMapper;
    }

    private void addNode(String id, String name, String category, double size, List<EchartsNode> echartsNodeList, double[] doubles, boolean isShow) {
        EchartsNode echartsNode = new EchartsNode();
        echartsNode.setId(id);
        echartsNode.setName(name);
        echartsNode.setX(doubles[0]);
        echartsNode.setY(doubles[1]);
        echartsNode.setCategory(category);
        echartsNode.setLabel(EchartsLabel.builder().show(isShow).build());
        echartsNode.setItemStyle(EchartsItemStyle.builder().build());
        echartsNode.setSymbolSize(size <= 1 ? 10 * Math.log(1000 * size): size);

        echartsNode.setEmphasis(EchartsEmphasis.builder().label(EchartsLabel.builder()
                .show(true).fontSize(17D).position("top").color("#000000").build()).build());

        echartsNode.setValue(String.valueOf(size));
        echartsNodeList.add(echartsNode);
    }

    private void addLink(String source, String target, Double width, List<EchartsLink> echartsLinkList) {
        EchartsLink echartsLink = new EchartsLink();
        echartsLink.setSource(source);
        echartsLink.setTarget(target);
        echartsLink.setValue(width);
        echartsLink.setLineStyle(EchartsLineStyle.builder().width(Math.log(5000 * width)).curveness(0.3D).build());
        echartsLinkList.add(echartsLink);
    }

    @Cacheable
    @Override
    public List<Trait> listTraitByRsId(String rsId, String genome) {

        int signalId = getElementSignalId(rsId);
        List<String> traitIdList = traitVariantMapMapper.selectTraitIdByRsIdAndGenome(rsId, signalId, genome);

        if (ListUtil.isEmpty(traitIdList)) {
            return NullUtil.listEmpty();
        }

        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Trait::getTraitId, traitIdList);
        return traitMapper.selectList(queryWrapper);
    }

    @Override
    public EchartsGraphData getVariantRelevanceGraph(String rsId, String genome) {

        int signalId = getElementSignalId(rsId);
        List<TraitVariantMap> traitVariantMapList = traitVariantMapMapper.selectByRsIdAndGenome(rsId, signalId, genome);

        if (ListUtil.isEmpty(traitVariantMapList)) {
            return EchartsGraphData.builder().build();
        }

        List<EchartsLink> echartsLinkList = Lists.newArrayListWithExpectedSize(32);
        List<EchartsNode> echartsNodeList = Lists.newArrayListWithExpectedSize(32);

        List<EchartsCategories> categoriesList = Lists.newArrayListWithCapacity(7);
        categoriesList.add(EchartsCategories.builder().name("SNP").symbolSize(30D).itemStyle(EchartsItemStyle.builder().color("#e16563").build()).build());
        categoriesList.add(EchartsCategories.builder().name("Trait").symbolSize(20D).symbol("roundRect").itemStyle(EchartsItemStyle.builder().color("#8cc372").build()).build());
        categoriesList.add(EchartsCategories.builder().name("Gene").symbolSize(20D).symbol("diamond").itemStyle(EchartsItemStyle.builder().color("#506bb2").build()).build());

        int step = 100;

        // get sample ID list
        Map<String, List<String>> signalIdTraitListMap = Maps.newHashMapWithExpectedSize(traitVariantMapList.size());

        Map<String, Double> traitIdPpMap = Maps.newHashMapWithExpectedSize(traitVariantMapList.size());

        for (TraitVariantMap traitVariantMap : traitVariantMapList) {
            String traitId = traitVariantMap.getTraitId();
            String traitSignalId = getTraitSignalId(traitId);
            signalIdTraitListMap.computeIfAbsent(traitSignalId, k -> new ArrayList<>()).add(traitId);
            traitIdPpMap.put(traitId, traitVariantMap.getPp());
        }
        double maxPp = traitIdPpMap.values().stream().max(Double::compare).orElse(1D);
        addNode(rsId, rsId, "SNP", maxPp, echartsNodeList, new double[]{0, 0}, true);

        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Trait::getTraitId, traitIdPpMap.keySet());
        List<Trait> traitList = traitMapper.selectList(queryWrapper);

        for (Trait trait : traitList) {
            double[] doubles = generateCircleRandomCoordinates(0, 0, step, 1.5 * step);
            Double pp = traitIdPpMap.get(trait.getTraitId());
            addNode(trait.getTraitId(), trait.getTrait(), "Trait", pp, echartsNodeList, doubles, false);
        }

        List<MagmaAnno> magmaAnnoList = magmaAnnoMapper.selectByTraitIdListAndRsId(rsId, genome, signalIdTraitListMap);

        if (ListUtil.isNotEmpty(magmaAnnoList)) {
            List<String> geneList = Lists.newArrayListWithExpectedSize(8);

            for (MagmaAnno magmaAnno : magmaAnnoList) {
                String traitId = magmaAnno.getTraitId();
                String gene = magmaAnno.getGene();

                if (StringUtil.isNotContain(gene, geneList)) {
                    geneList.add(gene);
                    double[] doubles = generateCircleRandomCoordinates(0, 0, 0.2 * step, 0.8 * step);
                    addNode(gene, gene, "Gene", maxPp, echartsNodeList, doubles, true);
                }

                addLink(gene, traitId, 0.01D, echartsLinkList);
                addLink(magmaAnno.getRsId(), gene, 0.01D, echartsLinkList);

            }
        }

        // The line is on top
        for (TraitVariantMap traitVariantMap : traitVariantMapList) {
            addLink(traitVariantMap.getRsId(), traitVariantMap.getTraitId(), traitVariantMap.getPp(), echartsLinkList);
        }

        return EchartsGraphData.builder()
                .nodes(echartsNodeList)
                .links(echartsLinkList)
                .categories(categoriesList)
                .build();
    }
}
