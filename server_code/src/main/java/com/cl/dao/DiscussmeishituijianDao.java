package com.cl.dao;

import com.cl.entity.DiscussmeishituijianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussmeishituijianView;


/**
 * 美食推荐评论表
 * 
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface DiscussmeishituijianDao extends BaseMapper<DiscussmeishituijianEntity> {
	
	List<DiscussmeishituijianView> selectListView(@Param("ew") Wrapper<DiscussmeishituijianEntity> wrapper);

	List<DiscussmeishituijianView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussmeishituijianEntity> wrapper);
	
	DiscussmeishituijianView selectView(@Param("ew") Wrapper<DiscussmeishituijianEntity> wrapper);
	

}
