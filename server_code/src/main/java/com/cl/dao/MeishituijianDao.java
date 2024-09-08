package com.cl.dao;

import com.cl.entity.MeishituijianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MeishituijianView;


/**
 * 美食推荐
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface MeishituijianDao extends BaseMapper<MeishituijianEntity> {
	
	List<MeishituijianView> selectListView(@Param("ew") Wrapper<MeishituijianEntity> wrapper);

	List<MeishituijianView> selectListView(Pagination page,@Param("ew") Wrapper<MeishituijianEntity> wrapper);
	
	MeishituijianView selectView(@Param("ew") Wrapper<MeishituijianEntity> wrapper);
	

}
