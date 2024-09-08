package com.cl.dao;

import com.cl.entity.DiscussjingquxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussjingquxinxiView;


/**
 * 景区信息评论表
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface DiscussjingquxinxiDao extends BaseMapper<DiscussjingquxinxiEntity> {
	
	List<DiscussjingquxinxiView> selectListView(@Param("ew") Wrapper<DiscussjingquxinxiEntity> wrapper);

	List<DiscussjingquxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussjingquxinxiEntity> wrapper);
	
	DiscussjingquxinxiView selectView(@Param("ew") Wrapper<DiscussjingquxinxiEntity> wrapper);
	

}
