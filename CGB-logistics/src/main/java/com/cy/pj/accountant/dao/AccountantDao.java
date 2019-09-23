package com.cy.pj.accountant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.accountant.entity.GoodsOrders;

@Mapper
public interface AccountantDao {
	/**
	 * 更新角色自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(GoodsOrders entity);
	/**
	 * 保存角色自身信息
	 * @param entity
	 * @return
	 */
	int insertObject(GoodsOrders entity);
	
	
	@Delete("delete from goods_orders where id=#{id}")
	int deleteObject(Integer id);
	@Select("select * from goods_orders where id=#{id}")
	List<GoodsOrders> findObjectById(Integer id);
	
	 /**
	  * 依据条件查询日志记录总数
	  * @param username 查询条件
	  * @return 查询到记录总数
	  */
	 int getRowCount(@Param("username")String username);
	 
	 /**
	  * 分页查询当前页日志信息
	  * @param username 查询条件
	  * @param startIndex 当前页起始位置
	  * @param pageSize 当前页面大小
	  * @return 当前页查询到记录
	  */
	 List<GoodsOrders> findPageObjects(
			      @Param("username")String username,
			      @Param("startIndex")Integer startIndex,
			      @Param("pageSize")Integer pageSize);

}
