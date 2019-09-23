package com.cy.pj.accountant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.accountant.entity.GoodsOrders;
import com.cy.pj.accountant.service.AccountantService;
import com.cy.pj.common.vo.JsonResult;
@RestController
@RequestMapping("/accountant")
public class AccountantController{
	@Autowired
	private AccountantService accService; 

	public JsonResult updateObject(GoodsOrders entity) {
		accService.updateObject(entity);
		return new JsonResult("update Ok");
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(GoodsOrders entity) {
		accService.saveObject(entity);
		return new JsonResult("save Ok");
	}
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		List<GoodsOrders> obj=accService.findObjectById(id);
		for (GoodsOrders goodsOrders : obj) {
			System.out.println(goodsOrders);
		}
		System.out.println("obj="+obj);
		return new JsonResult(obj);
	}
	
	
	@GetMapping("doFindObject")
	public JsonResult doFindObject(String username,
			@RequestParam Integer pageCurrent) {
		return new JsonResult(accService.findPageObjects(username, pageCurrent));
	}
}