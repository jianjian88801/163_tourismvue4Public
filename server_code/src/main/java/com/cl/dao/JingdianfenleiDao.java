package com.cl.dao;

import com.cl.entity.JingdianfenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JingdianfenleiView;


/**
 * 景点分类
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface JingdianfenleiDao extends BaseMapper<JingdianfenleiEntity> {
	
	List<JingdianfenleiView> selectListView(@Param("ew") Wrapper<JingdianfenleiEntity> wrapper);

	List<JingdianfenleiView> selectListView(Pagination page,@Param("ew") Wrapper<JingdianfenleiEntity> wrapper);
	
	JingdianfenleiView selectView(@Param("ew") Wrapper<JingdianfenleiEntity> wrapper);
	

}
