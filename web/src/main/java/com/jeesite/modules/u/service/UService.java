/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.u.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.QueryService;
import com.jeesite.modules.u.entity.U;
import com.jeesite.modules.u.dao.UDao;

/**
 * uService
 * @author 杨永强
 * @version 2019-01-12
 */
@Service
public class UService extends QueryService<UDao, U> {
	
	/**
	 * 获取单条数据
	 * @param u
	 * @return
	 */
	@Override
	public U get(U u) {
		return super.get(u);
	}
	
	/**
	 * 查询分页数据
	 * @param u 查询条件
	 * @param u.page 分页对象
	 * @return
	 */
	@Override
	public Page<U> findPage(U u) {
		return super.findPage(u);
	}
	
}