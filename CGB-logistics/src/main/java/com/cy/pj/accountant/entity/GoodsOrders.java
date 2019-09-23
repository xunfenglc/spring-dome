package com.cy.pj.accountant.entity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class GoodsOrders implements Serializable{
	private static final long serialVersionUID = -879207488848125977L;
	private Integer id;//订单id
	private String type;//订单类型
	private String customerName;//客户名称
	private String orderClerk;//接单员
	private Date createdTime;//创建订单时间,通过数据库获取
	private Date finishTime;//完成订单时间,通过数据库获取
	private double orderPrice;//订单金额
	private double cost;//送货费用,有多个费用是需通过计算获取
	private double income;//已收费用
	private double arrears;//未收款
	private double profits=orderPrice-cost;//订单利润,通过计算获得
	
	
}
