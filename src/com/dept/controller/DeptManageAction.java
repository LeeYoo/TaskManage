package com.dept.controller;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.dept.entity.Dept;
import com.dept.service.DeptService;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.annotation.Permission;
//�����Ź���������ɾ�Ĳ�
@Controller		//Ĭ��bean�������ǣ�deptManageAction
@Scope("prototype")
public class DeptManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer deptID;
	private String deptname;			//���������ơ�
	private String message;
	private String urladdress;
	
	public DeptManageAction() {}
	public Integer getDeptID() {
		return deptID;
	}
	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	@Resource DeptService deptService;
	
	//����Ӳ��ŵ�UI��
	@Permission(module="dept",privilege="add")	//��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	public String addUI()  {
		return "addUI";
	}
	
	//����Ӳ��š�
	@Permission(module="dept",privilege="add")	//��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	public String add() throws Exception{
		Dept dept = new Dept(deptname);
		if(deptname != null && deptname != ""){
			deptService.save(dept);
		}
		message = "��Ӳ��ųɹ���";
		urladdress = SiteUrl.readUrl("manager.dept");
		return "message";
	}
	
	//���༭���ŵ�UI��
	@Permission(module="dept",privilege="edit")	//��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	public String editUI()  {
		Dept dept = deptService.find(Dept.class, deptID);
		this.setDeptname(dept.getDeptname());
		return "editUI";
	}
	
	//���޸Ĳ��š�
	@Permission(module="dept",privilege="edit")	//��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	public String edit() throws Exception{
		Dept dept = deptService.find(Dept.class, deptID);
		dept.setDeptname(deptname);
		
		if(deptname != null && deptname != ""){
			deptService.update(dept);
		}
		message = "�޸Ĳ��ųɹ���";
		urladdress = SiteUrl.readUrl("manager.dept");
		return "message";
	}
	
	//��ɾ�����š�
	@Permission(module="dept",privilege="delete")	//��Ȩ�����ء���ִ�з���֮ǰ��Ҫע���жϡ�
	public String delete() throws Exception{
		deptService.delete(Dept.class, deptID);
		message = "ɾ�����ųɹ���";
		urladdress = SiteUrl.readUrl("manager.dept");
		return "message";
	}
}