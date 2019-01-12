/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.svm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.QueryService;
import com.jeesite.modules.svm.entity.Svm;
import com.jeesite.modules.svm.dao.SvmDao;

/**
 * svmService
 * @author 杨永强
 * @version 2019-01-12
 */
@Service
public class SvmService extends QueryService<SvmDao, Svm> {
	
	/**
	 * 获取单条数据
	 * @param svm
	 * @return
	 */
	@Override
	public Svm get(Svm svm) {
		return super.get(svm);
	}
	
	/**
	 * 查询分页数据
	 * @param svm 查询条件
	 * @param svm.page 分页对象
	 * @return
	 */
	@Override
	public Page<Svm> findPage(Svm svm) {
		return super.findPage(svm);
	}
	
}