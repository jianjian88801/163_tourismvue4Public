package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.LvyouxianluEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.LvyouxianluView;


/**
 * 旅游线路
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface LvyouxianluService extends IService<LvyouxianluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<LvyouxianluView> selectListView(Wrapper<LvyouxianluEntity> wrapper);
   	
   	LvyouxianluView selectView(@Param("ew") Wrapper<LvyouxianluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<LvyouxianluEntity> wrapper);
   	

}

