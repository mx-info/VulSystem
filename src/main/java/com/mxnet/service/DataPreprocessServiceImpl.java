package com.mxnet.service;

import com.mxnet.mapper.DataPreprocessMapper;
import com.mxnet.pojo.DataCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataPreprocessServiceImpl implements DataPreprocessService{
    @Autowired
    DataPreprocessMapper dataPreprocessMapper;
    @Override
    public int insertFunExtractedFromFile(DataCode dataCode) {
        return dataPreprocessMapper.insertFunExtractedFromFile(dataCode);
    }

    @Override
    public List<DataCode> selectAllDataCode() {
        return dataPreprocessMapper.selectAllDataCode();
    }
}
