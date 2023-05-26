package com.mxnet.service;

import com.mxnet.mapper.DataSampleMapper;
import com.mxnet.pojo.DataSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface DataSampleService {

    int insertSample(DataSample dataSample);


}
