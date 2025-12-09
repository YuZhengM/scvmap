# Welcome to scVMAP Platform Development Back-end!

> scVMAP: https://bio.liclab.net/scvmap/

> scVMAP reproducibility: https://github.com/YuZhengM/scvmap_reproducibility

> scVMAP tutorial: https://scvmap.readthedocs.io/en/latest/

> scVMAP front-end: https://github.com/YuZhengM/scvmap_web

> scVMAP API: https://bio.liclab.net/scvmap_service/scvmap.html

A comprehensive platform for **integrating** `single-cell chromatin accessibility regions` with `causal variants`.

![overview.png](src/main/resources/storage/img/overview.png)

## Introduction

scVMAP is a user-centric database that offers intuitive workflows, customizable parameters, and comprehensive data accessibility, featuring:

1. Integrates causal variants with chromatin accessibility regions at single-cell resolution.
2. Includes data from 183 scATAC-seq samples and 15,884 fine-mapping datasets.
3. Encompasses over 3,876,800 effective trait-sample pairs, 26.3 million trait-cell type pairs, and 32.1 billion trait-cell pairs.
4. Performs enrichment analysis for traits across specific cells or cell types.
5. Identifies relevant genes and transcription factors (TFs), and performs pathway enrichment analysis for these genes in the context of specific samples and traits.
6. Constructs an integrated regulatory network (variant-TF-gene) linking traits to samples.
7. Offers comprehensive resources for data download.
8. Makes the code for reproducing the database workflow publicly available.
9. Publishes the full-stack development code for the web application.
10. Provides API interfaces for programmatic data access.

## Implementation

> Java: 17.0.1

> Framework: SpringBoot (3.0.5)

> Database: MySQL (8.0.32)

> Cache: Redis (6.2.11)

> Deploy: Docker (19.03.5)

## Deploy

[README.md](src/main/resources/storage/deploy/README.md)

## Cite

Yu ZM, Qian FC, Fang QL, Meng XY, Li YY, Feng CC, Li LD, Li BL, Feng YR, Jiang H, Wang QY, Fan X, Guo JC, Li CQ. scVMAP: a comprehensive platform for integrating single-cell chromatin accessibility regions with causal variants. _Nucleic Acids Res_. 2025 Oct 31:gkaf1112. doi: [10.1093/nar/gkaf1112](http://doi.org/10.1093/nar/gkaf1112). Epub ahead of print. PMID: [41171141](https://pubmed.ncbi.nlm.nih.gov/41171141/).
