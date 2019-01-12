/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.QueryService;
import com.jeesite.modules.bp.entity.Bp;
import com.jeesite.modules.bp.dao.BpDao;

/**
 * bpService
 * @author 杨永强
 * @version 2019-01-12
 */
@Service
public class BpService extends QueryService<BpDao, Bp> {
	
	/**
	 * 获取单条数据
	 * @param bp
	 * @return
	 */
	@Override
	public Bp get(Bp bp) {
		return super.get(bp);
	}
	
	/**
	 * 查询分页数据
	 * @param bp 查询条件
	 * @param bp.page 分页对象
	 * @return
	 */
	@Override
	public Page<Bp> findPage(Bp bp) {
		return super.findPage(bp);
	}
	
}