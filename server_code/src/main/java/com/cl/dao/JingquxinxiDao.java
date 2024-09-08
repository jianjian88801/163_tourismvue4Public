package com.cl.dao;

import com.cl.entity.JingquxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JingquxinxiView;


/**
 * 景区信息
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface JingquxinxiDao extends BaseMapper<JingquxinxiEntity> {
	
	List<JingquxinxiView> selectListView(@Param("ew") Wrapper<JingquxinxiEntity> wrapper);

	List<JingquxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<JingquxinxiEntity> wrapper);
	
	JingquxinxiView selectView(@Param("ew") Wrapper<JingquxinxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JingquxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JingquxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JingquxinxiEntity> wrapper);



}
