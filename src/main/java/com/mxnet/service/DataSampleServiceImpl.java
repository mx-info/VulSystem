package com.mxnet.service;

import com.mxnet.mapper.DataSampleMapper;
import com.mxnet.pojo.DataSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

@Service
public class DataSampleServiceImpl implements DataSampleService{

    @Autowired
    DataSampleMapper dataSampleMapper;
    @Override
    public int insertSample(DataSample dataSample) {

        return dataSampleMapper.insertSample(dataSample);
    }

    public List<DataSample> queryFunctionByLimit(Map<String, Integer> map){
        return dataSampleMapper.queryFunctionByLimit(map);
    }
}
