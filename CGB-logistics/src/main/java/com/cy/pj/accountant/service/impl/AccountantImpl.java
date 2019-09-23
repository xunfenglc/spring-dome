package com.cy.pj.accountant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.accountant.dao.AccountantDao;
import com.cy.pj.accountant.entity.GoodsOrders;
import com.cy.pj.accountant.service.AccountantService;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;

@Service
public class AccountantImpl implements AccountantService {
	@Autowired
	private AccountantDao accDao;

	@Override
	public int updateObject(GoodsOrders entity) {
		//1.合法验证
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getProfits()))
			throw new ServiceException("利润不能为空");
		int rows;
		//2.更新数据
		try{
			rows=accDao.updateObject(entity);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("更新失败");
		}
		//3.返回数据
		return rows;
	}

	@Override
	public int saveObject(GoodsOrders entity) {
		if(entity==null) 
			throw new IllegalArgumentException("记录不能为空");
		if(StringUtils.isEmpty(entity))
			throw new IllegalArgumentException("利润不能为空");
		//2.保存数据
		//2.1保存角色自身信息
		int rows=accDao.insertObject(entity);
		//3.返回结果
		return rows;
	}
	
	@Override
	public List<GoodsOrders> findObjectById(Integer id) {
		if(id==null)
			throw new ServiceException("请选择一条记录");
		List<GoodsOrders> obj=accDao.findObjectById(id);
		return obj;
	}
	

	@Override
	public PageObject<GoodsOrders> findPageObjects(String username, Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=accDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=5;
		int startIndex=(pageCurrent-1)*pageSize;
		List<GoodsOrders> records=accDao.findPageObjects(username,startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}


}
