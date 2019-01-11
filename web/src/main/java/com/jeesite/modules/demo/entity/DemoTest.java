/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * demo_testEntity
 * @author thinkgem
 * @version 2019-01-10
 */
@Table(name="demo_test", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="name", queryType=QueryType.LIKE),
		@Column(name="age", attrName="age", label="age"),
	}, orderBy="a.id DESC"
)
public class DemoTest extends DataEntity<DemoTest> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String age;		// age
	
	public DemoTest() {
		this(null);
	}

	public DemoTest(String id){
		super(id);
	}
	
	@Length(min=0, max=16, message="name长度不能超过 16 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=16, message="age长度不能超过 16 个字符")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}