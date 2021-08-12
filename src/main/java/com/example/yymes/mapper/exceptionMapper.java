package com.example.yymes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yymes.entity.ExceptionSetting;


import java.util.List;
import java.util.Map;

public interface exceptionMapper extends BaseMapper<ExceptionSetting> {

    List<ExceptionSetting> getExceptionAll(Map<String,Object> m);
    int getExceptionCount();
    int DeleteId(ExceptionSetting u);
    List<ExceptionSetting> selectType();
}
