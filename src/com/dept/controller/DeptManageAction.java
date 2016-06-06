package com.dept.controller;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.dept.entity.Dept;
import com.dept.service.DeptService;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.annotation.Permission;
//【部门管理】——增删改查
@Controller		//默认bean的名称是：deptManageAction
@Scope("prototype")
public class DeptManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer deptID;
	private String deptname;			//【部门名称】
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
	
	//【添加部门的UI】
	@Permission(module="dept",privilege="add")	//【权限拦截】在执行方法之前需要注解判断。
	public String addUI()  {
		return "addUI";
	}
	
	//【添加部门】
	@Permission(module="dept",privilege="add")	//【权限拦截】在执行方法之前需要注解判断。
	public String add() throws Exception{
		Dept dept = new Dept(deptname);
		if(deptname != null && deptname != ""){
			deptService.save(dept);
		}
		message = "添加部门成功！";
		urladdress = SiteUrl.readUrl("manager.dept");
		return "message";
	}
	
	//【编辑部门的UI】
	@Permission(module="dept",privilege="edit")	//【权限拦截】在执行方法之前需要注解判断。
	public String editUI()  {
		Dept dept = deptService.find(Dept.class, deptID);
		this.setDeptname(dept.getDeptname());
		return "editUI";
	}
	
	//【修改部门】
	@Permission(module="dept",privilege="edit")	//【权限拦截】在执行方法之前需要注解判断。
	public String edit() throws Exception{
		Dept dept = deptService.find(Dept.class, deptID);
		dept.setDeptname(deptname);
		
		if(deptname != null && deptname != ""){
			deptService.update(dept);
		}
		message = "修改部门成功！";
		urladdress = SiteUrl.readUrl("manager.dept");
		return "message";
	}
	
	//【删除部门】
	@Permission(module="dept",privilege="delete")	//【权限拦截】在执行方法之前需要注解判断。
	public String delete() throws Exception{
		deptService.delete(Dept.class, deptID);
		message = "删除部门成功！";
		urladdress = SiteUrl.readUrl("manager.dept");
		return "message";
	}
}