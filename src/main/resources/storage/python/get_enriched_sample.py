#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import sys
import numpy as np

import anndata as ad


def get_info(trs_file: str, trait_id: str) -> None:
    data = ad.read(trs_file)
    info = np.array(data[:, trait_id].X).flatten()
    info_sum = info.sum()
    sample_list = list(data[info == 1, :].obs.index)
    print(sample_list)
    print(info_sum)
    print(data.shape[0] - info_sum)


if __name__ == '__main__':
    get_info(sys.argv[1], sys.argv[2])
