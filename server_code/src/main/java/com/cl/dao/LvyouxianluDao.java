package com.cl.dao;

import com.cl.entity.LvyouxianluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.LvyouxianluView;


/**
 * 旅游线路
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface LvyouxianluDao extends BaseMapper<LvyouxianluEntity> {
	
	List<LvyouxianluView> selectListView(@Param("ew") Wrapper<LvyouxianluEntity> wrapper);

	List<LvyouxianluView> selectListView(Pagination page,@Param("ew") Wrapper<LvyouxianluEntity> wrapper);
	
	LvyouxianluView selectView(@Param("ew") Wrapper<LvyouxianluEntity> wrapper);
	

}
