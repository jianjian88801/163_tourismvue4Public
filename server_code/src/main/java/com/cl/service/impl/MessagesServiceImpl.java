package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.MessagesDao;
import com.cl.entity.MessagesEntity;
import com.cl.service.MessagesService;
import com.cl.entity.view.MessagesView;

@Service("messagesService")
public class MessagesServiceImpl extends ServiceImpl<MessagesDao, MessagesEntity> implements MessagesService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MessagesEntity> page = this.selectPage(
                new Query<MessagesEntity>(params).getPage(),
                new EntityWrapper<MessagesEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MessagesEntity> wrapper) {
		  Page<MessagesView> page =new Query<MessagesView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<MessagesView> selectListView(Wrapper<MessagesEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MessagesView selectView(Wrapper<MessagesEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
