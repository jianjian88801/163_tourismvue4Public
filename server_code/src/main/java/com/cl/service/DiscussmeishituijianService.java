package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussmeishituijianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussmeishituijianView;


/**
 * 美食推荐评论表
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface DiscussmeishituijianService extends IService<DiscussmeishituijianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussmeishituijianView> selectListView(Wrapper<DiscussmeishituijianEntity> wrapper);
   	
   	DiscussmeishituijianView selectView(@Param("ew") Wrapper<DiscussmeishituijianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussmeishituijianEntity> wrapper);
   	

}

