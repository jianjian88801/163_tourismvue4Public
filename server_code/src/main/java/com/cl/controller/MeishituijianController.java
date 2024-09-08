package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.MeishituijianEntity;
import com.cl.entity.view.MeishituijianView;

import com.cl.service.MeishituijianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 美食推荐
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
@RestController
@RequestMapping("/meishituijian")
public class MeishituijianController {
    @Autowired
    private MeishituijianService meishituijianService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MeishituijianEntity meishituijian,
		HttpServletRequest request){
        EntityWrapper<MeishituijianEntity> ew = new EntityWrapper<MeishituijianEntity>();

		PageUtils page = meishituijianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, meishituijian), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MeishituijianEntity meishituijian, 
		HttpServletRequest request){
        EntityWrapper<MeishituijianEntity> ew = new EntityWrapper<MeishituijianEntity>();

		PageUtils page = meishituijianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, meishituijian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MeishituijianEntity meishituijian){
       	EntityWrapper<MeishituijianEntity> ew = new EntityWrapper<MeishituijianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( meishituijian, "meishituijian")); 
        return R.ok().put("data", meishituijianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MeishituijianEntity meishituijian){
        EntityWrapper< MeishituijianEntity> ew = new EntityWrapper< MeishituijianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( meishituijian, "meishituijian")); 
		MeishituijianView meishituijianView =  meishituijianService.selectView(ew);
		return R.ok("查询美食推荐成功").put("data", meishituijianView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MeishituijianEntity meishituijian = meishituijianService.selectById(id);
		meishituijian = meishituijianService.selectView(new EntityWrapper<MeishituijianEntity>().eq("id", id));
        return R.ok().put("data", meishituijian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MeishituijianEntity meishituijian = meishituijianService.selectById(id);
		meishituijian = meishituijianService.selectView(new EntityWrapper<MeishituijianEntity>().eq("id", id));
        return R.ok().put("data", meishituijian);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MeishituijianEntity meishituijian, HttpServletRequest request){
    	meishituijian.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(meishituijian);
        meishituijianService.insert(meishituijian);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MeishituijianEntity meishituijian, HttpServletRequest request){
    	meishituijian.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(meishituijian);
        meishituijianService.insert(meishituijian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MeishituijianEntity meishituijian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(meishituijian);
        meishituijianService.updateById(meishituijian);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        meishituijianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
