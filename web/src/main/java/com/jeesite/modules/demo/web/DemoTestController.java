/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.demo.entity.DemoTest;
import com.jeesite.modules.demo.service.DemoTestService;

/**
 * demo_testController
 * @author thinkgem
 * @version 2019-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/demoTest")
public class DemoTestController extends BaseController {

	@Autowired
	private DemoTestService demoTestService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DemoTest get(Integer id, boolean isNewRecord) {
		return demoTestService.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("demo:demoTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(DemoTest demoTest, Model model) {
		model.addAttribute("demoTest", demoTest);
		return "modules/demo/demoTestList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("demo:demoTest:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DemoTest> listData(DemoTest demoTest, HttpServletRequest request, HttpServletResponse response) {
		demoTest.setPage(new Page<>(request, response));
		Page<DemoTest> page = demoTestService.findPage(demoTest);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("demo:demoTest:view")
	@RequestMapping(value = "form")
	public String form(DemoTest demoTest, Model model) {
		model.addAttribute("demoTest", demoTest);
		return "modules/demo/demoTestForm";
	}

	/**
	 * 保存demo_test
	 */
	@RequiresPermissions("demo:demoTest:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DemoTest demoTest) {
		demoTestService.save(demoTest);
		return renderResult(Global.TRUE, text("保存demo_test成功！"));
	}
	
	/**
	 * 删除demo_test
	 */
	@RequiresPermissions("demo:demoTest:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DemoTest demoTest) {
		demoTestService.delete(demoTest);
		return renderResult(Global.TRUE, text("删除demo_test成功！"));
	}
	
}