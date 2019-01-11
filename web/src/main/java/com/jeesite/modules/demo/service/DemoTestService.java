/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.demo.entity.DemoTest;
import com.jeesite.modules.demo.dao.DemoTestDao;

/**
 * demo_testService
 * @author thinkgem
 * @version 2019-01-10
 */
@Service
@Transactional(readOnly=true)
public class DemoTestService extends CrudService<DemoTestDao, DemoTest> {
	
	/**
	 * 获取单条数据
	 * @param demoTest
	 * @return
	 */
	@Override
	public DemoTest get(DemoTest demoTest) {
		return super.get(demoTest);
	}
	
	/**
	 * 查询分页数据
	 * @param demoTest 查询条件
	 * @param demoTest.page 分页对象
	 * @return
	 */
	@Override
	public Page<DemoTest> findPage(DemoTest demoTest) {
		return super.findPage(demoTest);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param demoTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DemoTest demoTest) {
		super.save(demoTest);
	}
	
	/**
	 * 更新状态
	 * @param demoTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DemoTest demoTest) {
		super.updateStatus(demoTest);
	}
	
	/**
	 * 删除数据
	 * @param demoTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DemoTest demoTest) {
		super.delete(demoTest);
	}
	
}