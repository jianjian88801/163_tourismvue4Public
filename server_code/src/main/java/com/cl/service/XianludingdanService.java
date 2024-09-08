package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XianludingdanEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XianludingdanView;


/**
 * 线路订单
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface XianludingdanService extends IService<XianludingdanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XianludingdanView> selectListView(Wrapper<XianludingdanEntity> wrapper);
   	
   	XianludingdanView selectView(@Param("ew") Wrapper<XianludingdanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XianludingdanEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<XianludingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<XianludingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<XianludingdanEntity> wrapper);



}

