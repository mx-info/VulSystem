package com.mxnet.mapper;


import com.mxnet.pojo.DataCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DataPreprocessMapper {
    int insertFunExtractedFromFile(DataCode dataCode);

    List<DataCode> selectAllDataCode();

}
