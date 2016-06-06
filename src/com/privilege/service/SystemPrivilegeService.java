package com.privilege.service;
import java.util.List;

import com.base.dao.DAO;
import com.privilege.entity.SystemPrivilege;
//【权限业务bean的接口】
public interface SystemPrivilegeService extends DAO{
	
	//【批量保存权限的方法】
	public void saves(List<SystemPrivilege> privileges);
	
	
}
