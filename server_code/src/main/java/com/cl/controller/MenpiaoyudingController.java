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

import com.cl.entity.MenpiaoyudingEntity;
import com.cl.entity.view.MenpiaoyudingView;

import com.cl.service.MenpiaoyudingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 门票预订
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
@RestController
@RequestMapping("/menpiaoyuding")
public class MenpiaoyudingController {
    @Autowired
    private MenpiaoyudingService menpiaoyudingService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MenpiaoyudingEntity menpiaoyuding,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			menpiaoyuding.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<MenpiaoyudingEntity> ew = new EntityWrapper<MenpiaoyudingEntity>();

		PageUtils page = menpiaoyudingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, menpiaoyuding), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MenpiaoyudingEntity menpiaoyuding, 
		HttpServletRequest request){
        EntityWrapper<MenpiaoyudingEntity> ew = new EntityWrapper<MenpiaoyudingEntity>();

		PageUtils page = menpiaoyudingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, menpiaoyuding), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MenpiaoyudingEntity menpiaoyuding){
       	EntityWrapper<MenpiaoyudingEntity> ew = new EntityWrapper<MenpiaoyudingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( menpiaoyuding, "menpiaoyuding")); 
        return R.ok().put("data", menpiaoyudingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MenpiaoyudingEntity menpiaoyuding){
        EntityWrapper< MenpiaoyudingEntity> ew = new EntityWrapper< MenpiaoyudingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( menpiaoyuding, "menpiaoyuding")); 
		MenpiaoyudingView menpiaoyudingView =  menpiaoyudingService.selectView(ew);
		return R.ok("查询门票预订成功").put("data", menpiaoyudingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MenpiaoyudingEntity menpiaoyuding = menpiaoyudingService.selectById(id);
		menpiaoyuding = menpiaoyudingService.selectView(new EntityWrapper<MenpiaoyudingEntity>().eq("id", id));
        return R.ok().put("data", menpiaoyuding);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MenpiaoyudingEntity menpiaoyuding = menpiaoyudingService.selectById(id);
		menpiaoyuding = menpiaoyudingService.selectView(new EntityWrapper<MenpiaoyudingEntity>().eq("id", id));
        return R.ok().put("data", menpiaoyuding);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MenpiaoyudingEntity menpiaoyuding, HttpServletRequest request){
    	menpiaoyuding.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(menpiaoyuding);
        menpiaoyudingService.insert(menpiaoyuding);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MenpiaoyudingEntity menpiaoyuding, HttpServletRequest request){
    	menpiaoyuding.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(menpiaoyuding);
        menpiaoyudingService.insert(menpiaoyuding);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MenpiaoyudingEntity menpiaoyuding, HttpServletRequest request){
        //ValidatorUtils.validateEntity(menpiaoyuding);
        menpiaoyudingService.updateById(menpiaoyuding);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        menpiaoyudingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
