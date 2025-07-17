#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import warnings
import sys

import anndata as ad
import numpy as np


def get_sample_heatmap(trs_file, strategy, trait_id_all):
    trait_id_list = trait_id_all.split("__")
    data = ad.read(trs_file)
    trait_data = data[:, trait_id_list]
    trait_data.layers["mean"] = trait_data.X

    cell_type_list = list(trait_data.obs.index)
    print(cell_type_list)
    print(list(trait_data.var.index))

    for i in range(len(cell_type_list)):
        print(list(np.array(trait_data.layers[strategy][i, :].todense()).flatten()))


if __name__ == '__main__':

    with warnings.catch_warnings():
        warnings.simplefilter("ignore")
        get_sample_heatmap(sys.argv[1], sys.argv[2], sys.argv[3])
