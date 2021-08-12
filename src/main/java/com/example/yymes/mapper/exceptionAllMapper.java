package com.example.yymes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yymes.entity.ExceptionAll;
import com.example.yymes.entity.ExceptionSetting;

import java.util.List;
import java.util.Map;


public interface exceptionAllMapper extends BaseMapper<ExceptionAll> {

    int getExceptionAllCount();
    List<ExceptionAll> getExceptionAll(Map<String,Object> m);
}
