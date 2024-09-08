package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussjingquxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussjingquxinxiView;


/**
 * 景区信息评论表
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface DiscussjingquxinxiService extends IService<DiscussjingquxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussjingquxinxiView> selectListView(Wrapper<DiscussjingquxinxiEntity> wrapper);
   	
   	DiscussjingquxinxiView selectView(@Param("ew") Wrapper<DiscussjingquxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussjingquxinxiEntity> wrapper);
   	

}

