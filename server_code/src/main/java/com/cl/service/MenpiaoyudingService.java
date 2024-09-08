package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.MenpiaoyudingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MenpiaoyudingView;


/**
 * 门票预订
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface MenpiaoyudingService extends IService<MenpiaoyudingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MenpiaoyudingView> selectListView(Wrapper<MenpiaoyudingEntity> wrapper);
   	
   	MenpiaoyudingView selectView(@Param("ew") Wrapper<MenpiaoyudingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MenpiaoyudingEntity> wrapper);
   	

}

