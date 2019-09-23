package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
	private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    @Override
    public Map<String, Object> findObjectById(
    		Integer id) {
    	//1.参数校验
    	if(id==null||id<1)
    		throw new IllegalArgumentException("参数值无效");
    	//2.查询用户以及对应部门信息
    	SysUserDeptVo user=sysUserDao.findObjectById(id);
    	//3.查询用户对应的角色信息
    	List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(id);
    	//4.封装查询结果并返回
    	Map<String,Object> map=new HashMap<>();
    	map.put("user", user);
    	map.put("roleIds",roleIds);
    	return map;
    }
    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
    	long start=System.currentTimeMillis();
    	log.info("start:"+start);
    	//1.参数校验
    	if(entity==null)
    		throw new ServiceException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new ServiceException("用户名不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    		throw new ServiceException("密码不能为空");
    	if(roleIds==null || roleIds.length==0)
    		throw new ServiceException("至少要为用户分配角色");
    	//2.保存用户自身信息
        //2.1对密码进行加密
    	String source=entity.getPassword();
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//Shiro框架
    			"MD5",//algorithmName 算法
    			 source,//原密码
    			 salt, //盐值
    			 1);//hashIterations表示加密次数
    	entity.setSalt(salt);
    	entity.setPassword(sh.toHex());
    	int rows=sysUserDao.insertObject(entity);
    	//3.保存用户角色关系数据
    	sysUserRoleDao.insertObjects(entity.getId(), roleIds);
    	long end=System.currentTimeMillis();
    	log.info("end:"+end);
    	log.info("total time :"+(end-start));
    	//4.返回结果
    	return rows;
    }
    
    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
    	//1.参数校验
    	if(entity==null)
    		throw new ServiceException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new ServiceException("用户名不能为空");
    	if(roleIds==null || roleIds.length==0)
    		throw new ServiceException("至少要为用户分配角色");
    	//2.保存用户自身信息
    	int rows=sysUserDao.updateObject(entity);
    	//3.保存用户角色关系数据
    	sysUserRoleDao.deleteObjectByUserId(entity.getId());
    	sysUserRoleDao.insertObjects(entity.getId(), roleIds);
    	//4.返回结果
    	return rows;
    }
    
    @Override
    public int validById(Integer id, Integer valid, String modifiedUser) {
    	//1.参数校验
    	if(id==null||id<1)
    		throw new IllegalArgumentException("无效的记录id");
    	if(valid!=0&&valid!=1)
    		throw new IllegalArgumentException("状态值无效");
    	//2.更新用户状态
    	int rows=sysUserDao.validById(id, valid, modifiedUser);
    	if(rows==0)
    		throw new IllegalArgumentException("记录可能已经不存在");
    	//3.返回结果
    	return rows;
    }
    
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.参数校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("页码不正确");
		//2.查询总记录数并校验
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new IllegalArgumentException("没有对应记录");
		//3.查询当前页记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records=
		sysUserDao.findPageObjects(username, startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

}
