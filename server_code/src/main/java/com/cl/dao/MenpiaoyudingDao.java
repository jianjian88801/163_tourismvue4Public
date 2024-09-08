package com.cl.dao;

import com.cl.entity.MenpiaoyudingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MenpiaoyudingView;


/**
 * 门票预订
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface MenpiaoyudingDao extends BaseMapper<MenpiaoyudingEntity> {
	
	List<MenpiaoyudingView> selectListView(@Param("ew") Wrapper<MenpiaoyudingEntity> wrapper);

	List<MenpiaoyudingView> selectListView(Pagination page,@Param("ew") Wrapper<MenpiaoyudingEntity> wrapper);
	
	MenpiaoyudingView selectView(@Param("ew") Wrapper<MenpiaoyudingEntity> wrapper);
	

}
