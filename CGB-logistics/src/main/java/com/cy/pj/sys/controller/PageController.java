package com.cy.pj.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")//在此路径开始找
public class PageController {
	
	//开始页面地址
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	//分页页面
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
//	@RequestMapping("doFindObject")
//	public String doFindObject(){
//		return "accountant/acc_list";
//	}
	

//	@RequestMapping("{module}/{moduleUI}")
//	public String doModuleUI(
//			@PathVariable String moduleUI) {
//		return "sys/"+ moduleUI;
//	}
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(
			@PathVariable String module,
			@PathVariable String moduleUI) {
		return module+"/" +moduleUI;
	}
	
}
