package com.mxnet.mapper;

import com.mxnet.pojo.DataSample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DataSampleMapper {
    //插入数据集
    int insertSample(DataSample dataSample);

    //limit分页查询 ? 条数据
    List<DataSample> queryFunctionByLimit(Map<String, Integer> map);

}
