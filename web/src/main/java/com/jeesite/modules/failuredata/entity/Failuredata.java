/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.failuredata.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * failuredataEntity
 * @author 杨永强
 * @version 2019-01-11
 */
@Table(name="failuredata", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="mtbf", attrName="mtbf", label="MTBF"),
	}, orderBy="a.id DESC"
)
public class Failuredata extends DataEntity<Failuredata> {
	
	private static final long serialVersionUID = 1L;
	private String mtbf;		// MTBF
	
	public Failuredata() {
		this(null);
	}

	public Failuredata(String id){
		super(id);
	}
	
	@NotBlank(message="MTBF不能为空")
	@Length(min=0, max=200, message="MTBF长度不能超过 200 个字符")
	public String getMtbf() {
		return mtbf;
	}

	public void setMtbf(String mtbf) {
		this.mtbf = mtbf;
	}
	
}