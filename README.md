# Welcome to scVMAP Platform Development Back-end!

> scVMAP: https://bio.liclab.net/scvmap/

> scVMAP tutorial: https://scvmap.readthedocs.io/en/latest/

> scVMAP front-end: https://github.com/YuZhengM/scvmap_web

> scVMAP API: https://bio.liclab.net/scvmap_service/swagger-ui/index.html

A comprehensive platform for **integrating** `single-cell chromatin accessibility regions` with `causal variants`.

![overview.png](src/main/resources/storage/img/overview.png)

## Introduction

scVMAP is a user-centric database that offers intuitive workflows, customizable parameters, and comprehensive data accessibility, featuring:

1. Integrates causal variants and chromatin accessibility regions at single-cell resolution.
2. Accommodates 183 scATAC-seq samples and 15,805 fine-mapping datasets.
3. Records over 1,923,500 effective trait-sample pairs, over 13,060,000 effective trait-cell type pairs, and over 15,950,000,000 effective trait-cell pairs.
4. Provides enrichment for traits and cells or cell types.
5. Identifies relevant genes and transcription factors (TFs), as well as pathway enrichment functions of genes for the samples (including single-cell samples and traits).
6. Constructs the gene and TF hub regulatory network from traits to samples.
7. Provides epigenomic regulatory annotations related to genes, as well as a gene-relevant SNP regulatory network for specific traits.
8. Establishes an SNP-associated regulatory network.
9. Offers comprehensive download resources.
10. Publishes full-stack development code.
11. Provides API interfaces for data access.

## Implementation

> Bioinformatics website: scVMAP

> Framework: SpringBoot (3.0.5)

> Database: MySQL (8.0.32)

> Cache: Redis (6.2.11)

> Deploy: Docker (19.03.5)

## Deploy

[README.md](src/main/resources/storage/deploy/README.md)
