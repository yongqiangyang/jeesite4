/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.jm.entity.Jm;
import com.jeesite.modules.jm.dao.JmDao;

/**
 * jmService
 * @author 杨永强
 * @version 2019-01-11
 */
@Service
@Transactional(readOnly=true)
public class JmService extends CrudService<JmDao, Jm> {
	
	/**
	 * 获取单条数据
	 * @param jm
	 * @return
	 */
	@Override
	public Jm get(Jm jm) {
		return super.get(jm);
	}
	
	/**
	 * 查询分页数据
	 * @param jm 查询条件
	 * @param jm.page 分页对象
	 * @return
	 */
	@Override
	public Page<Jm> findPage(Jm jm) {
		return super.findPage(jm);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param jm
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Jm jm) {
		super.save(jm);
	}
	
	/**
	 * 更新状态
	 * @param jm
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Jm jm) {
		super.updateStatus(jm);
	}
	
	/**
	 * 删除数据
	 * @param jm
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Jm jm) {
		super.delete(jm);
	}
	
}