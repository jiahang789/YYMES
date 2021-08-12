package com.example.yymes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ExceptionAll")
public class ExceptionAll {

    @TableId(type= IdType.AUTO) //数据库自增
    private int Id;
    @TableField(value = "EquipMent")
    private String EquipMent;
    @TableField(value = "ExceptionType")
    private String ExceptionType;
    @TableField(value = "ExceptionDecs")
    private String ExceptionDecs;
    @TableField(value = "InitTime")
    private String InitTime;

    @TableField(value = "DealTime")
    private String DealTime;
    @TableField(value = "Opinion")
    private String Opinion;
    @TableField(value = "OpinitonPerson")
    private String OpinitonPerson;
    @TableField(value = "Section")
    private String Section;

    @TableField(value = "State")
    private String State;
    @TableField(value = "InitPerson")
    private String InitPerson;
}
