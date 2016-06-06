package com.dept.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.dao.DaoSupport;
import com.dept.service.DeptService;
//【部门业务bean接口的具体实现类】
@Service
@Transactional	//父类当中有这个注解，不加也可以
public class DeptServiceBean extends DaoSupport implements DeptService{
	
}
