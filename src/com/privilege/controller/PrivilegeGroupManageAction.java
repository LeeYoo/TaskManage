package com.privilege.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.entity.SystemPrivilegePK;
import com.privilege.service.PrivilegeGroupService;
import com.privilege.service.SystemPrivilegeService;
//【权限组管理】——增删改查
@Controller		//默认bean的名称是：privilegeGroupManageAction
@Scope("prototype")
public class PrivilegeGroupManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String name;			//【权限组名称】
	private String message;
	private String urladdress;
	private String groupId;
	/*【权限值】
	 * 复选框可以勾选多个权限，那么就要采用数组方式来接受选中的值，那就要在action中对其进行拆分处理（比较麻烦）
	 * 我可以直接得到权限ID类型的数组——可以定义一个类型转换器SystemPrivilegePKConventer即可
	 */
	private String[] privileges;		
	
	public PrivilegeGroupManageAction() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String[] getPrivileges() {
		return privileges;
	}
	public void setPrivileges(String[] privileges) {
		this.privileges = privileges;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Resource SystemPrivilegeService systemPrivilegeService;
	@Resource PrivilegeGroupService privilegeGroupService;
	
	//【添加权限组的UI】
	public String addUI()  {
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();	
		//得到所有的权限,放在request当中
		request.setAttribute("privileges", systemPrivilegeService.getScrollData_3(SystemPrivilege.class).getResultlist());
		return "addUI";
	}
	
	//【添加权限组】
	public String add() throws Exception{
		PrivilegeGroup group = new PrivilegeGroup();
		group.setName(name);
		//个人觉得这样代码入侵也小, 你做成Filter 或者inteceptor 不是多一层过滤么. 没必要的地方也都进行过滤了.详情SystemPrivilegePKConventer
		for (String privilege : privileges) {
            String[] priKV= privilege.split(",");
            if (priKV.length == 2) {
                SystemPrivilegePK pk = new SystemPrivilegePK(priKV[0], priKV[1]);
                group.getPrivileges().add(new SystemPrivilege(pk));
            }
        }
		/*
		if(privileges != null && privileges.length>0){
			for(SystemPrivilegePK id : privileges){
				group.getPrivileges().add(new SystemPrivilege(id));
			}
		}*/
		privilegeGroupService.save(group);
		message = "添加权限组成功！";
		urladdress = SiteUrl.readUrl("manager.privilegeGroup");
		return "message";
	}
	
	//【编辑权限组的UI】
	public String editUI()  {
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		PrivilegeGroup group = privilegeGroupService.find(PrivilegeGroup.class, groupId);
		this.setName(group.getName());
		//存放当前权限组中的所有权限
		request.setAttribute("selectprivileges", group.getPrivileges());
		//迭代所有的权限组
		request.setAttribute("privileges", systemPrivilegeService.getScrollData_3(SystemPrivilege.class).getResultlist());
		return "editUI";
	}
	
	//【修改权限组】
	public String edit() throws Exception{
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		PrivilegeGroup group = privilegeGroupService.find(PrivilegeGroup.class, groupId);
		group.setName(name);
		group.getPrivileges().clear();//清楚之前的所有选择，再重新添加
		
		//个人觉得这样代码入侵也小, 你做成Filter 或者inteceptor 不是多一层过滤么. 没必要的地方也都进行过滤了.详情SystemPrivilegePKConventer
		for (String privilege : privileges) {
            String[] priKV= privilege.split(",");
            if (priKV.length == 2) {
                SystemPrivilegePK pk = new SystemPrivilegePK(priKV[0], priKV[1]);
                group.getPrivileges().add(new SystemPrivilege(pk));
            }
        }
		privilegeGroupService.update(group);
		message = "修改权限组成功！";
		urladdress = SiteUrl.readUrl("manager.privilegeGroup");
		return "message";
	}
	
	//【删除权限组】——权限组如果作为关联关系的维护端，就可以直接删除；如果作为被维护端，则不可以直接删除，需要覆写delete方法——内部首先解除关联
	public String delete() throws Exception{
		privilegeGroupService.delete(PrivilegeGroup.class, groupId);
		message = "删除权限组成功！";
		urladdress = SiteUrl.readUrl("manager.privilegeGroup");
		return "message";
	}
}