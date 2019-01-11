/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jm.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * jmEntity
 * @author 杨永强
 * @version 2019-01-11
 */
@Table(name="jm", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="mtbf", attrName="mtbf", label="mtbf"),
	}, orderBy="a.id DESC"
)
public class Jm extends DataEntity<Jm> {
	
	private static final long serialVersionUID = 1L;
	private Long mtbf;		// mtbf
	
	public Jm() {
		this(null);
		this.mtbf=Long.getLong("0") ;
	}

	public Jm(String id){
		super(id);
	}
	
	public Long getMtbf() {
		return mtbf;
	}

	public void setMtbf(Long mtbf) {
		this.mtbf = mtbf;
	}
	
}