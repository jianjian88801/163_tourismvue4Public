package com.cl.dao;

import com.cl.entity.MeishigoumaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MeishigoumaiView;


/**
 * 美食购买
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface MeishigoumaiDao extends BaseMapper<MeishigoumaiEntity> {
	
	List<MeishigoumaiView> selectListView(@Param("ew") Wrapper<MeishigoumaiEntity> wrapper);

	List<MeishigoumaiView> selectListView(Pagination page,@Param("ew") Wrapper<MeishigoumaiEntity> wrapper);
	
	MeishigoumaiView selectView(@Param("ew") Wrapper<MeishigoumaiEntity> wrapper);
	

}
