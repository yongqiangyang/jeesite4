/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.failuredata.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.QueryService;
import com.jeesite.modules.failuredata.entity.Failuredata;
import com.jeesite.modules.failuredata.dao.FailuredataDao;

/**
 * failuredataService
 * @author 杨永强
 * @version 2019-01-11
 */
@Service
public class FailuredataService extends QueryService<FailuredataDao, Failuredata> {
	
	/**
	 * 获取单条数据
	 * @param failuredata
	 * @return
	 */
	@Override
	public Failuredata get(Failuredata failuredata) {
		return super.get(failuredata);
	}
	
	/**
	 * 查询分页数据
	 * @param failuredata 查询条件
	 * @param failuredata.page 分页对象
	 * @return
	 */
	@Override
	public Page<Failuredata> findPage(Failuredata failuredata) {
		return super.findPage(failuredata);
	}
	
}