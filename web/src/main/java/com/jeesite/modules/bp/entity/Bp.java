/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bp.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * bpEntity
 * @author 杨永强
 * @version 2019-01-12
 */
@Table(name="bp", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
	}, orderBy="a.id DESC"
)
public class Bp extends DataEntity<Bp> {
	
	private static final long serialVersionUID = 1L;
	
	public Bp() {
		this(null);
	}

	public Bp(String id){
		super(id);
	}
	
}