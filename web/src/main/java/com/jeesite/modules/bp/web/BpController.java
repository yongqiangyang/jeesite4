/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.bp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.bp.edu.packt.neuralnet.NeuralNet;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.IActivationFunction;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.Linear;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.RandomNumberGenerator;
import com.jeesite.modules.bp.edu.packt.neuralnet.math.Sigmoid;
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
import com.jeesite.modules.bp.entity.Bp;
import com.jeesite.modules.bp.service.BpService;

/**
 * bpController
 * @author 杨永强
 * @version 2019-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bp/bp")
public class BpController extends BaseController {

	@Autowired
	private BpService bpService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Bp get(String id, boolean isNewRecord) {
		return bpService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("bp:bp:view")
	@RequestMapping(value = {"list", ""})
	public String list(Bp bp, Model model) {
		model.addAttribute("bp", bp);
		return "modules/bp/bpList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("bp:bp:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Bp> listData(Bp bp, HttpServletRequest request, HttpServletResponse response) {
		bp.setPage(new Page<>(request, response));
		Page<Bp> page = bpService.findPage(bp);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("bp:bp:view")
	@RequestMapping(value = "form")
	public String form(Bp bp, Model model) {
		model.addAttribute("bp", bp);
		return "modules/bp/bpForm";
	}

	@RequiresPermissions("bp:bp:view")
	@RequestMapping(value = "BPrun",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public result BPrun(int numberOfInputs,int numberOfOutputs,int[] numberOfHiddenNeurons,String input) {
		RandomNumberGenerator.seed=0;

		IActivationFunction[] hiddenAcFnc = { new Sigmoid(1.0) } ;
		Linear outputAcFnc = new Linear(1.0);
		NeuralNet nn = new NeuralNet(numberOfInputs,numberOfOutputs,
				numberOfHiddenNeurons,hiddenAcFnc,outputAcFnc);
		nn.print();


		String[] temp = input.split(",");
		double []  neuralInput=new double[numberOfInputs];
		for (int i = 0; i < numberOfInputs; i++) {
			neuralInput[i]=Double.valueOf(temp[i]);
			System.out.println(temp[i]);
			System.out.println(neuralInput[i]);
		}

		nn.setInputs(neuralInput);
		nn.calc();


		result result =new result();
		double[] temp1=nn.getOutputs();
		for(int i=0;i<numberOfOutputs;i++){
			result.neuralOutput[i]=temp1[i];
		}
		result.numberOfOutputs=numberOfOutputs;
		return result;
	}
}