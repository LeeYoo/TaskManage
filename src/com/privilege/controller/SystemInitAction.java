package com.privilege.controller;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.service.PrivilegeGroupService;
import com.privilege.service.SystemPrivilegeService;
import com.user.entity.Gender;
import com.user.entity.User;
import com.user.service.UserService;
//【系统初始化的Action————初始化权限】
@Controller		//默认bean的名称是：systemInitAction
@Scope("prototype")
public class SystemInitAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String message;		//消息
	private String urladdress;
	
	public SystemInitAction() {}
	
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

	//既然把action交给了Spring来管理，那就可以使用Spring的依赖注入功能，把业务bean注入进来。
	@Resource SystemPrivilegeService privilegeService;	//【注入业务bean】，通过接口来获取
	@Resource PrivilegeGroupService groupService;	
	@Resource UserService userService;
	
	//覆盖这个类当中的execute方法(执行的意思)
	@Override
	public String execute() throws Exception {
		initPrivilege();			//【初始化权限】
		initPrivilegeGroup();	//【初始化权限组】
		initAdmin();				//【初始化管理员】
		message = "恭喜您，系统初始化成功！";
		urladdress = SiteUrl.readUrl("user.preLogin");
		return "message";
	}

	//【初始化管理员】
	private void initAdmin() {
		if(userService.getCount(User.class) == 0){
			User user = new User();
			user.setIdentity(1);
			user.setUserUM("liyao001");
			user.setUsername("liyao");
			user.setRealname("李耀");
			user.setPassword("liyao");
			user.setEmail("youth_liyao@qq.com");
			user.setGender(Gender.MAN);
			//【赋予系统权限组】
			user.getGroups().addAll(groupService.getScrollData(PrivilegeGroup.class).getResultlist());
			userService.save(user);
		}
	}

	//【初始化权限组】——该权限组具有所有的权限
	private void initPrivilegeGroup() {
		if(groupService.getCount(PrivilegeGroup.class) == 0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("系统权限组");
			group.getPrivileges().addAll(privilegeService.getScrollData_3(SystemPrivilege.class).getResultlist());
			groupService.save(group);
		}
	}

	//【初始化权限方法】把系统中【所有功能】的权限添加到数据库表中去
	private void initPrivilege() {
		//【判断】防止用户多次刷新该页面(多次执行这个初始化的action路径)，导致插入或删除修改等操作多次执行，所以需要判断下，以免出错！
		
		if(privilegeService.getCount(SystemPrivilege.class)==0){
			List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
			
			//【部门模块权限设置】
			privileges.add(new SystemPrivilege("dept", "add", "部门添加权限"));
			privileges.add(new SystemPrivilege("dept", "view", "部门查看权限"));
			privileges.add(new SystemPrivilege("dept", "edit", "部门修改权限"));
			privileges.add(new SystemPrivilege("dept", "delete", "部门删除权限"));
			
			//【用户模块权限设置】
			privileges.add(new SystemPrivilege("user", "view", "用户查看权限"));
			privileges.add(new SystemPrivilege("user", "delete", "用户删除权限"));
			privileges.add(new SystemPrivilege("user", "privilegeSet", "用户权限设置权限"));
	
			//【权限组模块权限设置】
			privileges.add(new SystemPrivilege("privilegeGroup", "view", "权限组列表查看权限"));
			privileges.add(new SystemPrivilege("privilegeGroup", "delete", "权限组列表删除权限"));
			privileges.add(new SystemPrivilege("privilegeGroup", "add", "权限组列表添加权限"));
			privileges.add(new SystemPrivilege("privilegeGroup", "edit", "权限组列表修改权限"));
			
			//【权限模块权限设置】
			privileges.add(new SystemPrivilege("privilege", "view", "权限列表查看权限"));
			
			//【报表模块权限设置】
			privileges.add(new SystemPrivilege("chart", "view", "任务进度查看权限"));
			
			//【评分模块权限设置】
			privileges.add(new SystemPrivilege("score", "add", "给予评分权限"));
			privileges.add(new SystemPrivilege("score", "view", "评分查看权限"));
			
			privilegeService.saves(privileges);//【执行保存】
		}
	}
}