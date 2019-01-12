/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.svm.dao;

import com.jeesite.common.dao.QueryDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.svm.entity.Svm;

/**
 * svmDAO接口
 * @author 杨永强
 * @version 2019-01-12
 */
@MyBatisDao
public interface SvmDao extends QueryDao<Svm> {
	
}