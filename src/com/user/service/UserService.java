package com.user.service;
import com.base.dao.DAO;
//��Ա��ҵ��bean�Ľӿڡ�
public interface UserService extends DAO{
	//��ע����֤�û�����UM�š���֤�û�����UM���Ƿ��Ѿ����ڡ�������ֻ����֤�û�����UM��
	public boolean exsit1(String username);
	public boolean exsit2(String userUM);
	
	//����½У���û��������롿У���û��Ƿ��Ѿ����ڡ�������У���û���������
	public boolean validate(String username,String password,Integer deptno,Integer identity);
	public boolean validate1(String username,String password,Integer deptno);
	public boolean validate2(String username,String password,Integer identity);
	public boolean validate3(String username,String password);
}
