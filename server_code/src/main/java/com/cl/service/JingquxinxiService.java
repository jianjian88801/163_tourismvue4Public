package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JingquxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JingquxinxiView;


/**
 * 景区信息
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface JingquxinxiService extends IService<JingquxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JingquxinxiView> selectListView(Wrapper<JingquxinxiEntity> wrapper);
   	
   	JingquxinxiView selectView(@Param("ew") Wrapper<JingquxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JingquxinxiEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<JingquxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<JingquxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<JingquxinxiEntity> wrapper);



}

