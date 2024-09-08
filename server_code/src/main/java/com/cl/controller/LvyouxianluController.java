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

import com.cl.entity.LvyouxianluEntity;
import com.cl.entity.view.LvyouxianluView;

import com.cl.service.LvyouxianluService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 旅游线路
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
@RestController
@RequestMapping("/lvyouxianlu")
public class LvyouxianluController {
    @Autowired
    private LvyouxianluService lvyouxianluService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LvyouxianluEntity lvyouxianlu,
		HttpServletRequest request){
        EntityWrapper<LvyouxianluEntity> ew = new EntityWrapper<LvyouxianluEntity>();

		PageUtils page = lvyouxianluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lvyouxianlu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LvyouxianluEntity lvyouxianlu, 
		HttpServletRequest request){
        EntityWrapper<LvyouxianluEntity> ew = new EntityWrapper<LvyouxianluEntity>();

		PageUtils page = lvyouxianluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lvyouxianlu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LvyouxianluEntity lvyouxianlu){
       	EntityWrapper<LvyouxianluEntity> ew = new EntityWrapper<LvyouxianluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( lvyouxianlu, "lvyouxianlu")); 
        return R.ok().put("data", lvyouxianluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LvyouxianluEntity lvyouxianlu){
        EntityWrapper< LvyouxianluEntity> ew = new EntityWrapper< LvyouxianluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( lvyouxianlu, "lvyouxianlu")); 
		LvyouxianluView lvyouxianluView =  lvyouxianluService.selectView(ew);
		return R.ok("查询旅游线路成功").put("data", lvyouxianluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LvyouxianluEntity lvyouxianlu = lvyouxianluService.selectById(id);
		lvyouxianlu = lvyouxianluService.selectView(new EntityWrapper<LvyouxianluEntity>().eq("id", id));
        return R.ok().put("data", lvyouxianlu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LvyouxianluEntity lvyouxianlu = lvyouxianluService.selectById(id);
		lvyouxianlu = lvyouxianluService.selectView(new EntityWrapper<LvyouxianluEntity>().eq("id", id));
        return R.ok().put("data", lvyouxianlu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LvyouxianluEntity lvyouxianlu, HttpServletRequest request){
    	lvyouxianlu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(lvyouxianlu);
        lvyouxianluService.insert(lvyouxianlu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LvyouxianluEntity lvyouxianlu, HttpServletRequest request){
    	lvyouxianlu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(lvyouxianlu);
        lvyouxianluService.insert(lvyouxianlu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LvyouxianluEntity lvyouxianlu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lvyouxianlu);
        lvyouxianluService.updateById(lvyouxianlu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        lvyouxianluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
