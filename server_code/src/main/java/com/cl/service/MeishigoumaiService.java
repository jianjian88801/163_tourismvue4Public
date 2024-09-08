package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.MeishigoumaiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MeishigoumaiView;


/**
 * 美食购买
 *
 * @author 
 * @email 
 * @date 2024-04-23 14:27:01
 */
public interface MeishigoumaiService extends IService<MeishigoumaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MeishigoumaiView> selectListView(Wrapper<MeishigoumaiEntity> wrapper);
   	
   	MeishigoumaiView selectView(@Param("ew") Wrapper<MeishigoumaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MeishigoumaiEntity> wrapper);
   	

}

