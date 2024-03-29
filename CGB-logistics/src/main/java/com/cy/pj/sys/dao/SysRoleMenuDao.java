package com.cy.pj.sys.dao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 用于操作角色和菜单关系数据的数据层对象
 */
@Mapper
public interface SysRoleMenuDao {
	/**
	 * 保存角色和菜单的关系数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObjects(@Param("roleId")Integer roleId,
			      @Param("menuIds")Integer[]menuIds);
	
	/**
	 * 基于角色id删除角色和菜单关系数据
	 * @param roleId
	 * @return
	 */
	@Delete("delete from sys_role_menus where role_id=#{roleId}")
	int deleteObjectsByRoleId(Integer roleId);
	 /**
	    * 基于菜单id删除关系数据
	  * @param menuId
	  * @return
	  */
	 @Delete("delete from sys_role_menus where menu_id=#{menuId}")
	 int deleteObjectsByMenuId(Integer menuId);
}
