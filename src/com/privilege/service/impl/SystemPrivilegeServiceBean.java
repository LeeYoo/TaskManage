package com.privilege.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.dao.DaoSupport;
import com.privilege.entity.SystemPrivilege;
import com.privilege.service.SystemPrivilegeService;
//��Ȩ��ҵ��ӿڵľ���ʵ���ࡿ
@Service
@Transactional		//���൱�������ע�⣬����Ҳ����
public class SystemPrivilegeServiceBean extends DaoSupport implements SystemPrivilegeService{
	
	//����������Ȩ�޵ķ�����
	public void saves(List<SystemPrivilege> privileges){
		//���������ɡ�
		for(SystemPrivilege p : privileges){
			super.save(p);
		}
	}
}
