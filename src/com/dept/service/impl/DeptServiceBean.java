package com.dept.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.dao.DaoSupport;
import com.dept.service.DeptService;
//������ҵ��bean�ӿڵľ���ʵ���ࡿ
@Service
@Transactional	//���൱�������ע�⣬����Ҳ����
public class DeptServiceBean extends DaoSupport implements DeptService{
	
}
