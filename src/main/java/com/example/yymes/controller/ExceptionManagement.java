package com.example.yymes.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yymes.entity.ExceptionAll;
import com.example.yymes.entity.ExceptionSetting;


import com.example.yymes.mapper.exceptionAllMapper;
import com.example.yymes.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.yymes.mapper.exceptionMapper;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseResult
@RestController
@RequestMapping(value = "/exception")
public class ExceptionManagement {

    @Autowired(required = false)
    exceptionMapper exceptionmapper;
    @Autowired(required = false)
    exceptionAllMapper exceptionAllmapper;

    //异常设定查询
    @RequestMapping(value = "/setting")
    @ResponseBody
    public Map getExceptionSetting(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        int pageNum = Integer.parseInt(params.get("pageNum").toString());//当前页数
        int pageSize = Integer.parseInt(params.get("pageSize").toString());//每页条数
        Map<String, Object> m = new HashMap();
        m.put("pageNum", pageNum);
        m.put("pageSize", pageSize);
        //查询总条数
        int count = exceptionmapper.getExceptionCount();

        List<ExceptionSetting> i = exceptionmapper.getExceptionAll(m);
        Map map = new HashMap();
        map.put("data", i);
        map.put("count", count);
        return map;
    }

    //异常设定新增
    @RequestMapping(value = "/settingadd")
    public int getnameadd(@RequestBody Map<String, Object> params) {
        String depart = params.get("tips").toString();
        String type = params.get("debugType").toString();
        String name = params.get("dmId").toString();
        ExceptionSetting u = new ExceptionSetting();
        u.setExceptionType(type);
        u.setProcessingDepartment(depart);
        u.setCreationBy(name);
        int i = exceptionmapper.insert(u);

        return i;
    }

    //异常设定删除
    @RequestMapping(value = "/settingdelete")
    public String getnamedelete(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        String id = params.get("id").toString();
        ExceptionSetting u = new ExceptionSetting();
        u.setId(Integer.parseInt(id));
        exceptionmapper.DeleteId(u);

        return "删除成功";
    }

    //查询异常类型
    @RequestMapping(value = "/teSelectList")
    public List getTypr() {

        List<ExceptionSetting> i = exceptionmapper.selectType();
        return i;
    }

    //异常发起
    @RequestMapping(value = "/insertInit")
    public int setinsertInit(@RequestBody Map<String, Object> params) {
        String ExceptionType = String.valueOf(params.get("name"));
        String eqment = String.valueOf(params.get("region"));
        String desc = String.valueOf(params.get("textarea"));
        String InitPerson = String.valueOf(params.get("InitPerson"));
        String InitTime = String.valueOf(params.get("InitTime"));
        //处理部门
        String section = String.valueOf(params.get("section"));

        ExceptionAll u = new ExceptionAll();
        u.setExceptionType(ExceptionType);
        u.setEquipMent(eqment);
        u.setExceptionDecs(desc);
        u.setInitPerson(InitPerson);
        //默认状态未处理
        u.setState("未处理");
        u.setSection(section);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        u.setInitTime(dateNowStr);
        int i = exceptionAllmapper.insert(u);
        return i;
    }

    //异常发起查询
    @RequestMapping(value = "/InitSelsct")
    @ResponseBody
    public Map getExceptionAll(@RequestBody Map<String, Object> params) {


        //条件查询
        /**
         *1.部门查询
         *2.异常类型
         *3.发起日期
         **/
        String Section = String.valueOf(params.get("teId"));
        String ExceptionType = String.valueOf(params.get("emId"));
        String time = String.valueOf(params.get("value1"));
        //
        int pageNum = Integer.parseInt(params.get("pageNum").toString());//当前页数
        int pageSize = Integer.parseInt(params.get("pageSize").toString());//每页条数


        //current为当前页，limit为每页显示个数，voQuery为封装的查询条件
        //创建Page对象
        //参数一是当前页，参数二是每页个数
        IPage<ExceptionAll> Page = new Page<>(pageNum, pageSize);
        //设置条件
        QueryWrapper<ExceptionAll> wrapper = new QueryWrapper<ExceptionAll>();
        //eq是等于，ge是大于等于，gt是大于，le是小于等于，lt是小于，like是模糊查询
        if (!StringUtils.isEmpty(Section)) {
            wrapper.like("Section", Section);
        }
        if (!StringUtils.isEmpty(ExceptionType)) {
            wrapper.like("ExceptionType", ExceptionType);
        }
        if (!StringUtils.isEmpty(time)) {
            if (time != null) {
                wrapper.eq("InitTime", time);
            }
        }
        wrapper.eq("State", "未处理");
        //  List<Employee> result = employeeMapper.selectPage(new Page<>(1, 2),
        //          ew.between("id",1,20).eq("gender","F"));
//EntityWrapper<Employee> ew = new EntityWrapper<Employee>();
        //Page = exceptionAllmapper.selectPage(Page,null);
        //long count1 =Page.getTotal();
        IPage<ExceptionAll> rs = exceptionAllmapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        Long i2 = rs.getTotal();
        List<ExceptionAll> i3 = rs.getRecords();//结果
        Map<String, Object> m = new HashMap();
        m.put("pageNum", pageNum);
        m.put("pageSize", pageSize);
        //查询总条数
        //int count = exceptionAllmapper.getExceptionAllCount();
        // List<ExceptionAll> i = exceptionAllmapper.getExceptionAll(m);

        Map map = new HashMap();
        map.put("data", i3);
        map.put("count", i2);
        return map;
    }

    //异常发起删除
    @RequestMapping(value = "/settingAlldelete")
    public int settingdAllelete(@RequestBody Map<String, Object> params) {
        String id = String.valueOf(params.get("id"));
        int i = exceptionAllmapper.deleteById(id);
        return i;
    }

    //异常处理
    @RequestMapping(value = "/InitSelsctCL")
    @ResponseBody
    public Map getInitSelsctCL(@RequestBody Map<String, Object> params) {


        //条件查询
        /**
         *1.设备查询
         *2.异常类型
         *3.发起日期
         **/
        String Section = String.valueOf(params.get("emName"));
        String ExceptionType = String.valueOf(params.get("debugType"));
        String time = String.valueOf(params.get("value1"));
        //
        int pageNum = Integer.parseInt(params.get("pageNum").toString());//当前页数
        int pageSize = Integer.parseInt(params.get("pageSize").toString());//每页条数


        //current为当前页，limit为每页显示个数，voQuery为封装的查询条件
        //创建Page对象
        //参数一是当前页，参数二是每页个数
        IPage<ExceptionAll> Page = new Page<>(pageNum, pageSize);
        //设置条件
        QueryWrapper<ExceptionAll> wrapper = new QueryWrapper<ExceptionAll>();
        //eq是等于，ge是大于等于，gt是大于，le是小于等于，lt是小于，like是模糊查询
        if (!StringUtils.isEmpty(Section)) {
            wrapper.like("EquipMent", Section);
        }
        if (!StringUtils.isEmpty(ExceptionType)) {
            wrapper.like("ExceptionType", ExceptionType);
        }
        if (!StringUtils.isEmpty(time)) {
            if (time != null) {
                wrapper.eq("InitTime", time);
            }
        }
        wrapper.eq("State", "未处理");
        IPage<ExceptionAll> rs = exceptionAllmapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        Long i2 = rs.getTotal();
        List<ExceptionAll> i3 = rs.getRecords();//结果
        Map<String, Object> m = new HashMap();
        m.put("pageNum", pageNum);
        m.put("pageSize", pageSize);
        Map map = new HashMap();
        map.put("data", i3);
        map.put("count", i2);
        return map;
    }

    //异常处理提交
    @RequestMapping(value = "/settingCLadd")
    public int settingCLadd(@RequestBody Map<String, Object> params) {
        /**
         * 主键
         * 处理人
         * 处理意见
         * 处理日期
         * 修改状态
         * **/
        String id = String.valueOf(params.get("id"));
        String name = String.valueOf(params.get("name"));
        String op = String.valueOf(params.get("textarea"));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);

        ExceptionAll u = new ExceptionAll();
        u.setId(Integer.parseInt(id));
        u.setOpinitonPerson(name);
        u.setOpinion(op);
        u.setDealTime(dateNowStr);
        u.setState("已关闭");
        int i = exceptionAllmapper.updateById(u);
        return i;
    }

    //异常查询
    @RequestMapping(value = "/select")
    @ResponseBody
    public Map getselect(@RequestBody Map<String, Object> params) {


        //条件查询
        /**
         *1.异常类型
         *2.处理部门
         *3.发起日期至处理日期
         * 4.状态
         **/

        String ExceptionType = String.valueOf(params.get("debugType"));
        String Section = String.valueOf(params.get("emName"));
        String time = String.valueOf(params.get("value1"));
        String time2 = String.valueOf(params.get("value2"));
        String status = String.valueOf(params.get("status"));
        //
        int pageNum = Integer.parseInt(params.get("pageNum").toString());//当前页数
        int pageSize = Integer.parseInt(params.get("pageSize").toString());//每页条数


        //current为当前页，limit为每页显示个数，voQuery为封装的查询条件
        //创建Page对象
        //参数一是当前页，参数二是每页个数
        IPage<ExceptionAll> Page = new Page<>(pageNum, pageSize);
        //设置条件
        QueryWrapper<ExceptionAll> wrapper = new QueryWrapper<ExceptionAll>();
        //eq是等于，ge是大于等于，gt是大于，le是小于等于，lt是小于，like是模糊查询
        if (!StringUtils.isEmpty(Section)) {
            wrapper.like("Section", Section);
        }
        if (!StringUtils.isEmpty(ExceptionType)) {
            wrapper.like("ExceptionType", ExceptionType);
        }
        if (!StringUtils.isEmpty(time) && !StringUtils.isEmpty(time2)) {
            if (time != null && time2 != null) {
                wrapper.ge("InitTime", time).le("DealTime", time2);
            }
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.like("State", status);
        }
        IPage<ExceptionAll> rs = exceptionAllmapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        Long i2 = rs.getTotal();
        List<ExceptionAll> i3 = rs.getRecords();//结果
        Map<String, Object> m = new HashMap();
        m.put("pageNum", pageNum);
        m.put("pageSize", pageSize);
        Map map = new HashMap();
        map.put("data", i3);
        map.put("count", i2);
        return map;
    }
}
