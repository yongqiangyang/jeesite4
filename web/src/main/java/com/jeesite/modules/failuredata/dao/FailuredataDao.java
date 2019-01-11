/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.failuredata.dao;

import com.jeesite.common.dao.QueryDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.failuredata.entity.Failuredata;

/**
 * failuredataDAO接口
 * @author 杨永强
 * @version 2019-01-11
 */
@MyBatisDao
public interface FailuredataDao extends QueryDao<Failuredata> {
	
}