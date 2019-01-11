/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jm.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.jm.entity.Jm;

/**
 * jmDAO接口
 * @author 杨永强
 * @version 2019-01-11
 */
@MyBatisDao
public interface JmDao extends CrudDao<Jm> {
	
}