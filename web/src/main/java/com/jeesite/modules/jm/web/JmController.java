/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.jm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.jm.reliability.models.Jelinski_Moranda;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.jm.entity.Jm;
import com.jeesite.modules.jm.service.JmService;

/**
 * jmController
 * @author 杨永强
 * @version 2019-01-11
 */
@Controller
@RequestMapping(value = "${adminPath}/jm/jm")
public class JmController extends BaseController {

	@Autowired
	private JmService jmService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Jm get(Long id, boolean isNewRecord) {
		String s=id+"";
		return jmService.get(s, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("jm:jm:view")
	@RequestMapping(value = {"list", ""})
	public String list(Jm jm, Model model) {
		jm.setMtbf(Long.valueOf("10"));
		jm.setMtbf(Long.valueOf(jm.getMtbf().toString()+"0"));
		model.addAttribute("jm", jm);
		return "modules/jm/jmList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("jm:jm:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Jm> listData(Jm jm, HttpServletRequest request, HttpServletResponse response) {
		jm.setPage(new Page<>(request, response));
		Page<Jm> page = jmService.findPage(jm);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("jm:jm:view")
	@RequestMapping(value = "form")
	public String form(Jm jm, Model model) {
		System.out.println("click!"+jm.getId());
		model.addAttribute("jm", jm);
		return "modules/jm/jmForm";
	}

	/**
	 * 保存jm
	 */
	@RequiresPermissions("jm:jm:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Jm jm) {
		jmService.save(jm);
		return renderResult(Global.TRUE, text("保存jm成功！"));
	}
	
	/**
	 * 删除jm
	 */
	@RequiresPermissions("jm:jm:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Jm jm) {
		jmService.delete(jm);
		return renderResult(Global.TRUE, text("删除jm成功！"));
	}

	@RequiresPermissions("jm:jm:edit")
	@RequestMapping(value="JMRun", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public result JMRun(){

		//运行JM模型
		Jelinski_Moranda jelinski_moranda=new Jelinski_Moranda();
		jelinski_moranda.run();

		//将后端数据写入一个公有类中，将其传递给前端
		result result=new result();
		result.InherentErrorNumber=jelinski_moranda.getInherentErrorNumber();
		result.EX=jelinski_moranda.getEX();
		result.EY=jelinski_moranda.getEY();
		result.constant=jelinski_moranda.getConstant();
		result.t=jelinski_moranda.getT();
		result.dataNum=jelinski_moranda.getDataNum();
		return result;
	}
	
}