package com.mxnet.service;

import com.mxnet.pojo.DataCode;

import java.util.List;

public interface DataPreprocessService {
    int insertFunExtractedFromFile(DataCode dataCode);

    List<DataCode> selectAllDataCode();
}
