package com.privilege.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.dao.DaoSupport;
import com.privilege.entity.SystemPrivilege;
import com.privilege.service.SystemPrivilegeService;
//【权限业务接口的具体实现类】
@Service
@Transactional		//父类当中有这个注解，不加也可以
public class SystemPrivilegeServiceBean extends DaoSupport implements SystemPrivilegeService{
	
	//【批量保存权限的方法】
	public void saves(List<SystemPrivilege> privileges){
		//【迭代即可】
		for(SystemPrivilege p : privileges){
			super.save(p);
		}
	}
}
