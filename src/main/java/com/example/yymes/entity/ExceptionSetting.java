package com.example.yymes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("ExceptionSetting")
public class ExceptionSetting {

    @TableId(type= IdType.AUTO) //数据库自增
    private int Id;
    @TableField(value = "ExceptionType")
    private String ExceptionType;
    @TableField(value = "ProcessingDepartment")
    private String ProcessingDepartment;
    @TableField(value = "CreationTime")
    private String CreationTime;
    @TableField(value = "CreationBy")
    private String CreationBy;

}
