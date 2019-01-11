/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.failuredata.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.failuredata.entity.Failuredata;
import com.jeesite.modules.failuredata.service.FailuredataService;

/**
 * failuredataController
 * @author 杨永强
 * @version 2019-01-11
 */
@Controller
@RequestMapping(value = "${adminPath}/failuredata/failuredata")
public class FailuredataController extends BaseController {

	@Autowired
	private FailuredataService failuredataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Failuredata get(String id, boolean isNewRecord) {
		return failuredataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("failuredata:failuredata:view")
	@RequestMapping(value = {"list", ""})
	public String list(Failuredata failuredata, Model model) {
		model.addAttribute("failuredata", failuredata);
		return "modules/failuredata/failuredataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("failuredata:failuredata:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Failuredata> listData(Failuredata failuredata, HttpServletRequest request, HttpServletResponse response) {
		failuredata.setPage(new Page<>(request, response));
		Page<Failuredata> page = failuredataService.findPage(failuredata);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("failuredata:failuredata:view")
	@RequestMapping(value = "form")
	public String form(Failuredata failuredata, Model model) {
		model.addAttribute("failuredata", failuredata);
		return "modules/failuredata/failuredataForm";
	}
	
}