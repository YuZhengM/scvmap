#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import sys

import anndata as ad
import numpy as np
from scipy.sparse import csr_matrix


def get_trait_info(trs_file, trait_id, cell_rate):
    cell_rate = float(cell_rate)
    data = ad.read(trs_file)
    trait_data = data[:, trait_id]
    # get indexs
    data_cell_type_size = data.obs.groupby("f_cell_type").size()
    data_index_list = []

    for i in data_cell_type_size.index.values:
        cell_count = int(np.ceil(data_cell_type_size[i] * cell_rate))
        cell_index = list(data.obs[(data.obs["f_cell_type"] == i) & (data.obs["f_cell_type_index"] < cell_count)].index)
        data_index_list.extend(cell_index)

    trait_data = trait_data[data_index_list, :]
    values = np.array(csr_matrix(trait_data.X).todense()).flatten()
    values_index = np.argsort(values)
    trait_data = trait_data[values_index, :]
    print(trait_data.obs["f_barcodes"].tolist())
    print(trait_data.obs["f_cell_type"].tolist())
    print(trait_data.obs["f_umap_x"].tolist())
    print(trait_data.obs["f_umap_y"].tolist())
    print(list(np.array(csr_matrix(trait_data.X).todense()).flatten()))


if __name__ == '__main__':
    get_trait_info(sys.argv[1], sys.argv[2], sys.argv[3])
