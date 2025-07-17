#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import sys
import numpy as np

import anndata as ad


def get_info(trs_file: str, sample_id: str) -> None:
    data = ad.read(trs_file)
    info = np.array(data[sample_id, :].X)
    info_sum = info.sum()
    trait_list = list(data[:, info == 1].var.index)
    print(trait_list)
    print(info_sum)
    print(data.shape[1] - info_sum)


if __name__ == '__main__':
    get_info(sys.argv[1], sys.argv[2])
