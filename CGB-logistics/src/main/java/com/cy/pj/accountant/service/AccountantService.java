package com.cy.pj.accountant.service;


import java.util.List;

import com.cy.pj.accountant.entity.GoodsOrders;
import com.cy.pj.common.vo.PageObject;

public interface AccountantService {
	
	int updateObject(GoodsOrders entity);
	
	
	int saveObject(GoodsOrders entity);
	
	List<GoodsOrders> findObjectById(Integer id);
	
	PageObject<GoodsOrders> findPageObjects(
			 String username,
			 Integer pageCurrent);
}
