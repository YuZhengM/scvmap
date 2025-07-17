package com.spring.boot.service;

import com.spring.boot.pojo.*;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;

import java.util.List;

public interface GeneTfDetailService {
    List<Gene> listGeneInfo(String gene, String genome);

    Gene listGeneById(String geneId, String genome);

    Tf listTfInfo(String tf);

    List<? extends CommonSnp> listGeneInteractiveCommonSnp(String geneId, String genome);

    List<? extends EnhancerSea> listGeneInteractiveEnhancerSea(String geneId, String genome);

    List<? extends EnhancerSedb> listGeneInteractiveEnhancerSedb(String geneId, String genome);

    List<? extends Eqtl> listEqtlByGene(String geneId, String genome);

    List<? extends RiskSnp> listGeneInteractiveRiskSnp(String geneId, String genome);

    List<? extends SuperEnhancerDbsuper> listGeneInteractiveSuperEnhancerDbsuper(String geneId, String genome);

    List<? extends SuperEnhancerSea> listGeneInteractiveSuperEnhancerSea(String geneId, String genome);

    List<? extends SuperEnhancerSedb> listGeneInteractiveSuperEnhancerSedb(String geneId, String genome);

    List<Trait> listTraitByGeneGenome(String gene, String genome);

    List<Magma> listMagmaInfoDataByTraitIdAndGene(String traitId, String gene, String genome);

    EchartsPieData<String, String> getGeneTraitCount(String gene);

    EchartsPieData<String, String> getTfTraitCount(String tf);

    List<Homer> listHomerInfoDataByTraitIdAndTf(String traitId, String tf, String genome);

    List<Trait> listTraitByTfGenome(String tf, String genome);

}
