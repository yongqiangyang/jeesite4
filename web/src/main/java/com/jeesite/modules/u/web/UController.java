/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.u.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.jm.reliability.base.model;
import com.jeesite.modules.jm.reliability.compareModel.U_Diragram;
import com.jeesite.modules.jm.reliability.models.Jelinski_Moranda;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.u.entity.U;
import com.jeesite.modules.u.service.UService;

/**
 * uController
 * @author 杨永强
 * @version 2019-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/u/u")
public class UController extends BaseController {

	@Autowired
	private UService uService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public U get(Long id, boolean isNewRecord) {
		String s=id+"";
		return uService.get(s, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("u:u:view")
	@RequestMapping(value = {"list", ""})
	public String list(U u, Model model) {
		model.addAttribute("u", u);
		return "modules/u/uList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("u:u:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<U> listData(U u, HttpServletRequest request, HttpServletResponse response) {
		u.setPage(new Page<>(request, response));
		Page<U> page = uService.findPage(u);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("u:u:view")
	@RequestMapping(value = "form")
	public String form(U u, Model model) {
		model.addAttribute("u", u);
		return "modules/u/uForm";
	}

	//传数据点给前端
	@RequiresPermissions("u:u:view")
	@RequestMapping(value="URun", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public U_result URun(){
		model jm=new Jelinski_Moranda();
		U_Diragram U=new U_Diragram(jm);
		U.run();
		int length=U.getResultNum();
		U_result result=new U_result();
		for(int i=0;i<length;i++){
			result.X[i]=U.getX(i);
			result.Y[i]=U.getY(i);
		}
		result.dataNum=U.getResultNum();
		return result;
	}
}