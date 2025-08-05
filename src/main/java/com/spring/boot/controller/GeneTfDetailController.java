package com.spring.boot.controller;

import com.spring.boot.pojo.*;
import com.spring.boot.service.GeneTfDetailService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.boot.util.constant.ApplicationConstant.*;
import static com.spring.boot.util.util.ApplicationUtil.*;

/**
 * Controller for handling requests related to gene and TF (Transcription Factor) details.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/element/detail")
@CrossOrigin
@RestController
@Tag(name = "Gene/TF-Detail-API", description = "Controller for handling requests related to gene and TF details.")
public class GeneTfDetailController {

    private GeneTfDetailService geneTfDetailService;

    /**
     * Default constructor.
     */
    public GeneTfDetailController() {
    }

    /**
     * Constructor with dependency injection.
     *
     * @param geneTfDetailService Service for handling gene and TF details.
     */
    @Autowired
    public GeneTfDetailController(GeneTfDetailService geneTfDetailService) {
        this.geneTfDetailService = geneTfDetailService;
    }

    /**
     * Retrieves gene information by gene name and genome.
     *
     * @param gene   The gene name.
     * @param genome The genome.
     * @return Result containing the gene information.
     */
    @Operation(
        summary = "Retrieve gene information by gene name and genome",
        description = "Retrieves gene information based on the provided gene name and genome.",
        parameters = {
            @Parameter(name = "gene", in = ParameterIn.PATH, description = "The name of the gene to search for.", required = true, example = GENE_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/{gene}/{genome}")
    public Result<List<Gene>> listGeneInfo(@PathVariable String gene, @PathVariable String genome) {
        checkGenome(genome);
        List<Gene> geneList = geneTfDetailService.listGeneInfo(gene, genome);
        return ResultUtil.success("[getGeneInfo]: success", geneList);
    }

    @Operation(
        summary = "Retrieve gene information by gene ID and genome",
        description = "Retrieves gene information based on the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/id/{geneId}/{genome}")
    public Result<Gene> listGeneById(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        Gene gene = geneTfDetailService.listGeneById(geneId, genome);
        return ResultUtil.success("[listGeneById]: success", gene);
    }

    /**
     * Retrieves TF information by TF name.
     *
     * @param tf The TF name.
     * @return Result containing the gene information.
     */
    @Operation(
        summary = "Retrieve TF information by TF name",
        description = "Retrieves TF information based on the provided TF name.",
        parameters = {
            @Parameter(name = "tf", in = ParameterIn.PATH, description = "The name of the TF to search for.", required = true, example = TF_EXAMPLE)
        }
    )
    @GetMapping("/tf/{tf}")
    public Result<Tf> listTfInfo(@PathVariable String tf) {
        String tfName = getTfName(tf);
        Tf tfInfo = geneTfDetailService.listTfInfo(tfName);
        return ResultUtil.success("[listTfInfo]: success", tfInfo);
    }

    /**
     * Retrieves the count of traits associated with a gene.
     *
     * @param gene The gene name.
     * @return Result containing the trait count data in ECharts pie chart format.
     */
    @Operation(
        summary = "Retrieve the count of traits associated with a gene",
        description = "Retrieves the count of traits associated with the provided gene name in ECharts pie chart format.",
        parameters = {
            @Parameter(name = "gene", in = ParameterIn.PATH, description = "The name of the gene to get trait count for.", required = true, example = GENE_EXAMPLE)
        }
    )
    @GetMapping("/gene/trait/count/{gene}")
    public Result<EchartsPieData<String, String>> getGeneTraitCount(@PathVariable String gene) {
        EchartsPieData<String, String> echartsPieData = geneTfDetailService.getGeneTraitCount(gene);
        return ResultUtil.success("[getGeneTraitCount]: success", echartsPieData);
    }

    /**
     * Retrieves the count of traits associated with a TF (Transcription Factor).
     *
     * @param tf The TF name.
     * @return Result containing the trait count data in ECharts pie chart format.
     */
    @Operation(
        summary = "Retrieve the count of traits associated with a TF",
        description = "Retrieves the count of traits associated with the provided TF name in ECharts pie chart format.",
        parameters = {
            @Parameter(name = "tf", in = ParameterIn.PATH, description = "The name of the TF to get trait count for.", required = true, example = TF_EXAMPLE)
        }
    )
    @GetMapping("/tf/trait/count/{tf}")
    public Result<EchartsPieData<String, String>> getTfTraitCount(@PathVariable String tf) {
        String tfName = getTfName(tf);
        EchartsPieData<String, String> echartsPieData = geneTfDetailService.getTfTraitCount(tfName);
        return ResultUtil.success("[getTfTraitCount]: success", echartsPieData);
    }

    /**
     * Retrieves a list of traits associated with a gene and genome.
     *
     * @param gene   The gene name.
     * @param genome The genome.
     * @return Result containing the list of traits.
     */
    @Operation(
        summary = "Retrieve a list of traits associated with a gene and genome",
        description = "Retrieves a list of traits associated with the provided gene name and genome.",
        parameters = {
            @Parameter(name = "gene", in = ParameterIn.PATH, description = "The name of the gene to search for traits.", required = true, example = GENE_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/trait/{gene}/{genome}")
    public Result<List<Trait>> listTraitByGeneGenome(@PathVariable String gene, @PathVariable String genome) {
        checkGenome(genome);
        List<Trait> traitList = geneTfDetailService.listTraitByGeneGenome(gene, genome);
        return ResultUtil.success("[listTraitByGeneGenome]: success", traitList);
    }

    /**
     * Retrieves a list of traits associated with a TF and genome.
     *
     * @param tf     The TF name.
     * @param genome The genome.
     * @return Result containing the list of traits.
     */
    @Operation(
        summary = "Retrieve a list of traits associated with a TF and genome",
        description = "Retrieves a list of traits associated with the provided TF name and genome.",
        parameters = {
            @Parameter(name = "tf", in = ParameterIn.PATH, description = "The name of the TF to search for traits.", required = true, example = TF_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/tf/trait/{tf}/{genome}")
    public Result<List<Trait>> listTraitByTfGenome(@PathVariable String tf, @PathVariable String genome) {
        checkGenome(genome);
        String tfName = getTfName(tf);
        List<Trait> traitList = geneTfDetailService.listTraitByTfGenome(tfName, genome);
        return ResultUtil.success("[listTraitByTfGenome]: success", traitList);
    }

    /**
     * Retrieves a list of MAGMA data associated with a trait ID, gene, and genome.
     *
     * @param traitId The trait ID.
     * @param gene     The gene name.
     * @param genome   The genome.
     * @return Result containing the list of MAGMA data.
     */
    @Operation(
        summary = "Retrieve a list of MAGMA data associated with a trait ID, gene, and genome",
        description = "Retrieves a list of MAGMA data associated with the provided trait ID, gene name, and genome.",
        parameters = {
            @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for MAGMA data.", required = true, example = TRAIT_EXAMPLE),
            @Parameter(name = "gene", in = ParameterIn.PATH, description = "The name of the gene to search for MAGMA data.", required = true, example = GENE_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/trait/{trait_id}/{gene}/{genome}")
    public Result<List<Magma>> listMagmaInfoDataByTraitIdAndGene(@PathVariable("trait_id") String traitId, @PathVariable String gene, @PathVariable String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        List<Magma> magmaList = geneTfDetailService.listMagmaInfoDataByTraitIdAndGene(traitId, gene, genome);
        return ResultUtil.success("[listMagmaInfoDataByTraitIdAndGene]: success", magmaList);
    }

    /**
     * Retrieves a list of HOMER data associated with a trait ID, TF, and genome.
     *
     * @param traitId The trait ID.
     * @param tf       The TF name.
     * @param genome   The genome.
     * @return Result containing the list of HOMER data.
     */
    @Operation(
        summary = "Retrieve a list of HOMER data associated with a trait ID, TF, and genome",
        description = "Retrieves a list of HOMER data associated with the provided trait ID, TF name, and genome.",
        parameters = {
            @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for HOMER data.", required = true, example = TRAIT_EXAMPLE),
            @Parameter(name = "tf", in = ParameterIn.PATH, description = "The name of the TF to search for HOMER data.", required = true, example = TF_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/tf/trait/{trait_id}/{tf}/{genome}")
    public Result<List<Homer>> listHomerInfoDataByTraitIdAndTf(@PathVariable("trait_id") String traitId, @PathVariable String tf, @PathVariable String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        String tfName = getTfName(tf);
        List<Homer> homerList = geneTfDetailService.listHomerInfoDataByTraitIdAndTf(traitId, tfName, genome);
        return ResultUtil.success("[listHomerInfoDataByTraitIdAndTf]: success", homerList);
    }

    /**
     * Retrieves a list of common SNPs associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of common SNPs.
     */
    @Operation(
        summary = "Retrieve a list of common SNPs associated with a gene and genome",
        description = "Retrieves a list of common SNPs associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for common SNPs.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/common_snp/{geneId}/{genome}")
    public Result<List<? extends CommonSnp>> listGeneInteractiveCommonSnp(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends CommonSnp> commonSnpList = geneTfDetailService.listGeneInteractiveCommonSnp(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveCommonSnp]: success", commonSnpList);
    }

    /**
     * Retrieves a list of eQTLs associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of eQTLs.
     */
    @Operation(
        summary = "Retrieve a list of eQTLs associated with a gene and genome",
        description = "Retrieves a list of eQTLs associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for eQTLs.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/eqtl/{geneId}/{genome}")
    public Result<List<? extends Eqtl>> listEqtlByGene(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends Eqtl> eqtlList = geneTfDetailService.listEqtlByGene(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveEqtl]: success", eqtlList);
    }

    /**
     * Retrieves a list of risk SNPs associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of risk SNPs.
     */
    @Operation(
        summary = "Retrieve a list of risk SNPs associated with a gene and genome",
        description = "Retrieves a list of risk SNPs associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for risk SNPs.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/risk_snp/{geneId}/{genome}")
    public Result<List<? extends RiskSnp>> listGeneInteractiveRiskSnp(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends RiskSnp> riskSnpList = geneTfDetailService.listGeneInteractiveRiskSnp(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveRiskSnp]: success", riskSnpList);
    }

    /**
     * Retrieves a list of CRISPR data associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of CRISPR data.
     */
    @Operation(
        summary = "Retrieve a list of CRISPR data associated with a gene and genome",
        description = "Retrieves a list of CRISPR data associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for CRISPR data.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/enhancer_sea/{geneId}/{genome}")
    public Result<List<? extends EnhancerSea>> listGeneInteractiveEnhancerSea(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends EnhancerSea> enhancerSeaList = geneTfDetailService.listGeneInteractiveEnhancerSea(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveCrispr]: success", enhancerSeaList);
    }

    /**
     * Retrieves a list of enhancers associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of enhancers.
     */
    @Operation(
        summary = "Retrieve a list of enhancers associated with a gene and genome",
        description = "Retrieves a list of enhancers associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for enhancers.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/enhancer_se_db/{geneId}/{genome}")
    public Result<List<? extends EnhancerSedb>> listGeneInteractiveEnhancerSedb(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends EnhancerSedb> enhancerList = geneTfDetailService.listGeneInteractiveEnhancerSedb(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveEnhancer]: success", enhancerList);
    }

    /**
     * Retrieves a list of super enhancers from dbSUPER associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of super enhancers from dbSUPER.
     */
    @Operation(
        summary = "Retrieve a list of super enhancers from dbSUPER associated with a gene and genome",
        description = "Retrieves a list of super enhancers from dbSUPER associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for super enhancers from dbSUPER.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/super_enhancer_dbsuper/{geneId}/{genome}")
    public Result<List<? extends SuperEnhancerDbsuper>> listGeneInteractiveSuperEnhancerDbsuper(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends SuperEnhancerDbsuper> superEnhancerDbsuperList = geneTfDetailService.listGeneInteractiveSuperEnhancerDbsuper(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveSuperEnhancerDbsuper]: success", superEnhancerDbsuperList);
    }

    /**
     * Retrieves a list of super enhancers from SEAv3 associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of super enhancers from SEAv3.
     */
    @Operation(
        summary = "Retrieve a list of super enhancers from SEAv3 associated with a gene and genome",
        description = "Retrieves a list of super enhancers from SEAv3 associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for super enhancers from SEAv3.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/super_enhancer_sea/{geneId}/{genome}")
    public Result<List<? extends SuperEnhancerSea>> listGeneInteractiveSuperEnhancerSea(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends SuperEnhancerSea> superEnhancerSeaList = geneTfDetailService.listGeneInteractiveSuperEnhancerSea(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveSuperEnhancerSea]: success", superEnhancerSeaList);
    }

    /**
     * Retrieves a list of super enhancers from SEdbV2 associated with a gene and genome.
     *
     * @param geneId The gene name.
     * @param genome The genome.
     * @return Result containing the list of super enhancers from SEdbV2.
     */
    @Operation(
        summary = "Retrieve a list of super enhancers from SEdbV2 associated with a gene and genome",
        description = "Retrieves a list of super enhancers from SEdbV2 associated with the provided gene ID and genome.",
        parameters = {
            @Parameter(name = "geneId", in = ParameterIn.PATH, description = "The ID of the gene to search for super enhancers from SEdbV2.", required = true, example = GENE_ID_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome to search within.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/gene/annotation/super_enhancer_se_db/{geneId}/{genome}")
    public Result<List<? extends SuperEnhancerSedb>> listGeneInteractiveSuperEnhancerSedb(@PathVariable String geneId, @PathVariable String genome) {
        checkGenome(genome);
        List<? extends SuperEnhancerSedb> superEnhancerSedbList = geneTfDetailService.listGeneInteractiveSuperEnhancerSedb(geneId, genome);
        return ResultUtil.success("[listGeneInteractiveSuperEnhancerSedb]: success", superEnhancerSedbList);
    }

}
