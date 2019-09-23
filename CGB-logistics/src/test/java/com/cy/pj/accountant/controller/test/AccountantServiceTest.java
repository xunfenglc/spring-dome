package com.cy.pj.accountant.controller.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.accountant.entity.GoodsOrders;
import com.cy.pj.accountant.service.AccountantService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountantServiceTest {
	@Autowired
	private AccountantService accService;
	@Test
	public void findObjece() {
		List<GoodsOrders> list= (List<GoodsOrders>) accService.findPageObjects("武汉",1);
		//System.out.println(list);
		for (GoodsOrders goods : list) {
			System.out.println(list);
		}
	}
}
