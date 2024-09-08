package com.cl.dao;

import com.cl.entity.XianludingdanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XianludingdanView;


/**
 * 线路订单
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface XianludingdanDao extends BaseMapper<XianludingdanEntity> {
	
	List<XianludingdanView> selectListView(@Param("ew") Wrapper<XianludingdanEntity> wrapper);

	List<XianludingdanView> selectListView(Pagination page,@Param("ew") Wrapper<XianludingdanEntity> wrapper);
	
	XianludingdanView selectView(@Param("ew") Wrapper<XianludingdanEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XianludingdanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XianludingdanEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<XianludingdanEntity> wrapper);



}
