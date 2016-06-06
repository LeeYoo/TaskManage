package com.user.service;
import com.base.dao.DAO;
//【员工业务bean的接口】
public interface UserService extends DAO{
	//【注册验证用户名和UM号】验证用户名和UM号是否已经存在————只是验证用户名和UM号
	public boolean exsit1(String username);
	public boolean exsit2(String userUM);
	
	//【登陆校验用户名和密码】校验用户是否已经存在————校验用户名和密码
	public boolean validate(String username,String password,Integer deptno,Integer identity);
	public boolean validate1(String username,String password,Integer deptno);
	public boolean validate2(String username,String password,Integer identity);
	public boolean validate3(String username,String password);
}
