/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.svm.web;

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
import com.jeesite.modules.svm.entity.Svm;
import com.jeesite.modules.svm.service.SvmService;

/**
 * svmController
 * @author 杨永强
 * @version 2019-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/svm/svm")
public class SvmController extends BaseController {

	@Autowired
	private SvmService svmService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Svm get(String id, boolean isNewRecord) {
		return svmService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("svm:svm:view")
	@RequestMapping(value = {"list", ""})
	public String list(Svm svm, Model model) {
		model.addAttribute("svm", svm);
		return "modules/svm/svmList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("svm:svm:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Svm> listData(Svm svm, HttpServletRequest request, HttpServletResponse response) {
		svm.setPage(new Page<>(request, response));
		Page<Svm> page = svmService.findPage(svm);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("svm:svm:view")
	@RequestMapping(value = "form")
	public String form(Svm svm, Model model) {
		model.addAttribute("svm", svm);
		return "modules/svm/svmForm";
	}
	
}