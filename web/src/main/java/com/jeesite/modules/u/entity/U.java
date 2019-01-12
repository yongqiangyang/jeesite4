/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.u.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * uEntity
 * @author 杨永强
 * @version 2019-01-12
 */
@Table(name="u", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="mtbf", attrName="mtbf", label="mtbf"),
	}, orderBy="a.id DESC"
)
public class U extends DataEntity<U> {
	
	private static final long serialVersionUID = 1L;
	private Long mtbf;		// mtbf
	
	public U() {
		this(null);
	}

	public U(String id){
		super(id);
	}
	
	public Long getMtbf() {
		return mtbf;
	}

	public void setMtbf(Long mtbf) {
		this.mtbf = mtbf;
	}
	
}