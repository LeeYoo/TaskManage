package com.privilege.service;
import java.util.List;

import com.base.dao.DAO;
import com.privilege.entity.SystemPrivilege;
//��Ȩ��ҵ��bean�Ľӿڡ�
public interface SystemPrivilegeService extends DAO{
	
	//����������Ȩ�޵ķ�����
	public void saves(List<SystemPrivilege> privileges);
	
	
}
