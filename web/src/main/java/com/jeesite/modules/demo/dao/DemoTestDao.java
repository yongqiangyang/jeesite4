/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.demo.entity.DemoTest;

/**
 * demo_testDAO接口
 * @author thinkgem
 * @version 2019-01-10
 */
@MyBatisDao
public interface DemoTestDao extends CrudDao<DemoTest> {
	
}