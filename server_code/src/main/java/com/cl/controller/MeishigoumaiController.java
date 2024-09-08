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

import com.cl.entity.MeishigoumaiEntity;
import com.cl.entity.view.MeishigoumaiView;

import com.cl.service.MeishigoumaiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 美食购买
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
@RestController
@RequestMapping("/meishigoumai")
public class MeishigoumaiController {
    @Autowired
    private MeishigoumaiService meishigoumaiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MeishigoumaiEntity meishigoumai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			meishigoumai.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<MeishigoumaiEntity> ew = new EntityWrapper<MeishigoumaiEntity>();

		PageUtils page = meishigoumaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, meishigoumai), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MeishigoumaiEntity meishigoumai, 
		HttpServletRequest request){
        EntityWrapper<MeishigoumaiEntity> ew = new EntityWrapper<MeishigoumaiEntity>();

		PageUtils page = meishigoumaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, meishigoumai), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MeishigoumaiEntity meishigoumai){
       	EntityWrapper<MeishigoumaiEntity> ew = new EntityWrapper<MeishigoumaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( meishigoumai, "meishigoumai")); 
        return R.ok().put("data", meishigoumaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MeishigoumaiEntity meishigoumai){
        EntityWrapper< MeishigoumaiEntity> ew = new EntityWrapper< MeishigoumaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( meishigoumai, "meishigoumai")); 
		MeishigoumaiView meishigoumaiView =  meishigoumaiService.selectView(ew);
		return R.ok("查询美食购买成功").put("data", meishigoumaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MeishigoumaiEntity meishigoumai = meishigoumaiService.selectById(id);
		meishigoumai = meishigoumaiService.selectView(new EntityWrapper<MeishigoumaiEntity>().eq("id", id));
        return R.ok().put("data", meishigoumai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MeishigoumaiEntity meishigoumai = meishigoumaiService.selectById(id);
		meishigoumai = meishigoumaiService.selectView(new EntityWrapper<MeishigoumaiEntity>().eq("id", id));
        return R.ok().put("data", meishigoumai);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MeishigoumaiEntity meishigoumai, HttpServletRequest request){
    	meishigoumai.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(meishigoumai);
        meishigoumaiService.insert(meishigoumai);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MeishigoumaiEntity meishigoumai, HttpServletRequest request){
    	meishigoumai.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(meishigoumai);
        meishigoumaiService.insert(meishigoumai);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MeishigoumaiEntity meishigoumai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(meishigoumai);
        meishigoumaiService.updateById(meishigoumai);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        meishigoumaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
