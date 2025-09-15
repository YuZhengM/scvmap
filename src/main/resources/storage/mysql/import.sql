-- docker exec -it scvdb_mysql bash
-- mysql --local-infile=1 -u root -p
-- ${mysql_password}
-- mysql --local-infile=1 -u root -p${mysql_password} </root/variant/create_variant_info.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/variant/create_variant_trait_map.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/magma_homer/create_magma.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/magma_homer/create_magma_anno.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/magma_homer/create_trait_gene_enrichment.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/scatac/create_difference_gene.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/scatac/create_difference_tf.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/scatac/create_sample_gene_enrichment.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/scatac/create_sample_gene.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/scatac/create_sample_tf.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/magma_homer/create_homer.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/gene/annotation/dbSNP/dbsnp_common_snp.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/gene/annotation/GTEx/gtex_eqtl_sql.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/gene/annotation/SEdb/sedb_enhancer_sql.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/trait_sample/create_sample_enrich.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/trait_sample/create_trait_enrich.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/trait_gene/create_trait_gene.sql
-- mysql --local-infile=1 -u root -p${mysql_password} </root/trait_tf/create_trait_tf.sql
-- mysql --local-infile=1 -u root -p123456 </root/cicero/create_cicero.sql
-- mysql --local-infile=1 -u root -p123456 </root/cicero/create_cicero_sample_gene.sql
-- mysql --local-infile=1 -u root -p123456 </root/cicero/create_cicero_trait_gene.sql
-- mysql --local-infile=1 -u root -p123456 </root/gimme/create_gimme.sql
-- mysql --local-infile=1 -u root -p123456 </root/gimme/create_gimme_sample_tf.sql
-- mysql --local-infile=1 -u root -p123456 </root/gimme/create_gimme_trait_tf.sql
set global local_infile = 'ON';
truncate `scvdb`.`t_trait_chr_count`;
LOAD DATA LOCAL INFILE "/root/variant/t_trait_chr_count.txt" INTO TABLE `scvdb`.`t_trait_chr_count` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';

truncate `scvdb`.`t_trait_sample_scavenge`;
LOAD DATA LOCAL INFILE "/root/trait_sample/scavenge_sample_enrichment.txt" INTO TABLE `scvdb`.`t_trait_sample_scavenge` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';
truncate `scvdb`.`t_trait_sample_gchromvar`;
LOAD DATA LOCAL INFILE "/root/trait_sample/gchromvar_sample_enrichment.txt" INTO TABLE `scvdb`.`t_trait_sample_gchromvar` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';

truncate `scvdb`.`t_trait_gene_hg19`;
LOAD DATA LOCAL INFILE "/root/magma_homer/magma/t_magma_hg19.txt" INTO TABLE `scvdb`.`t_trait_gene_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';

truncate `scvdb`.`t_trait_gene_hg38`;
LOAD DATA LOCAL INFILE "/root/magma_homer/magma/t_magma_hg38.txt" INTO TABLE `scvdb`.`t_trait_gene_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';

truncate `scvdb`.`t_trait_tf_hg19`;
LOAD DATA LOCAL INFILE "/root/magma_homer/homer/t_homer_trait_tf_hg19.txt" INTO TABLE `scvdb`.`t_trait_tf_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';

truncate `scvdb`.`t_trait_tf_hg38`;
LOAD DATA LOCAL INFILE "/root/magma_homer/homer/t_homer_trait_tf_hg38.txt" INTO TABLE `scvdb`.`t_trait_tf_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n';

truncate `scvdb`.`t_tf_trait_count`;
LOAD DATA LOCAL INFILE "/root/magma_homer/homer/t_homer_tf_trait_count.txt" INTO TABLE `scvdb`.`t_tf_trait_count` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;

truncate `scvdb`.`t_risk_snp_atlas_hg19`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/gwasATLAS/gwasatlas_v20191115_risk_snp_hg19.txt" INTO TABLE `scvdb`.`t_risk_snp_atlas_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;
truncate `scvdb`.`t_risk_snp_atlas_hg38`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/gwasATLAS/gwasatlas_v20191115_risk_snp_hg38.txt" INTO TABLE `scvdb`.`t_risk_snp_atlas_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;

truncate `scvdb`.`t_super_enhancer_dbsuper_hg19`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/dbSUPER/dbsuper_super_enhancer_hg19.txt" INTO TABLE `scvdb`.`t_super_enhancer_dbsuper_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;
truncate `scvdb`.`t_super_enhancer_dbsuper_hg38`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/dbSUPER/dbsuper_super_enhancer_hg38.txt" INTO TABLE `scvdb`.`t_super_enhancer_dbsuper_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;

truncate `scvdb`.`t_super_enhancer_sea_hg19`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/SEA/sea_v3_super_enhancer_hg19.txt" INTO TABLE `scvdb`.`t_super_enhancer_sea_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;
truncate `scvdb`.`t_super_enhancer_sea_hg38`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/SEA/sea_v3_super_enhancer_hg38.txt" INTO TABLE `scvdb`.`t_super_enhancer_sea_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;

truncate `scvdb`.`t_enhancer_sea_hg19`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/SEA/sea_v3_enhancer_hg19.txt" INTO TABLE `scvdb`.`t_enhancer_sea_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;
truncate `scvdb`.`t_enhancer_sea_hg38`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/SEA/sea_v3_enhancer_hg38.txt" INTO TABLE `scvdb`.`t_enhancer_sea_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;

truncate `scvdb`.`t_super_enhancer_sedb_hg19`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/SEdb/sedb_v2_super_enhancer_hg19.txt" INTO TABLE `scvdb`.`t_super_enhancer_sedb_hg19` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;
truncate `scvdb`.`t_super_enhancer_sedb_hg38`;
LOAD DATA LOCAL INFILE "/root/gene/annotation/SEdb/sedb_v2_super_enhancer_hg38.txt" INTO TABLE `scvdb`.`t_super_enhancer_sedb_hg38` fields terminated by '\t' optionally enclosed by '"' lines terminated by '\n' IGNORE 1 LINES;
