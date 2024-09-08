package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.MeishituijianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MeishituijianView;


/**
 * 美食推荐
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:00
 */
public interface MeishituijianService extends IService<MeishituijianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MeishituijianView> selectListView(Wrapper<MeishituijianEntity> wrapper);
   	
   	MeishituijianView selectView(@Param("ew") Wrapper<MeishituijianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MeishituijianEntity> wrapper);
   	

}

