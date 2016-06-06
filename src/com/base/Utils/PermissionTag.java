package com.base.Utils;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts2.ServletActionContext;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.entity.SystemPrivilegePK;
import com.user.entity.User;
/*【自定义权限标签类】
 * 		——>需求：	对于用户没有权限执行的功能模块，不仅不能让其执行，而且还要将其"按钮"隐藏，不暴漏给用户。
 * 		——>解决方案：通过自定义标签来完成
 * 【步骤】：
 * 		1.加上标签在jsp页面相关位置加上自定义标签，以及设置该标签的权限
 * 		2.自定义标签处理类——该类必须继承TagSupport，并复写其doStartTag方法（方法内部实现权限校验）
 * 		3.配置标签库——描述文件的tld文件
 * 		4.给使用到自定义标签的页面文件引入自定义的tld文件：
 * 		即就是：<%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
 * 【一定注意】有时候tld文件会找不到，将其一定要放在WEB-INF的根目录下。
 *
 * 
 */
public class PermissionTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String module;	//	权限模块——和页面标签属性一致即可。
	private String privilege;	//权鲜值
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	/*【重写doStartTag方法】——返回值是int
	 * 自定义标签<privilege:permission module="dept" privilege="update">…………</privilege:permission>
	 * 当执行到自定义标签的时候，就会触及这个doStartTag校验方法！返回值1则执行，0则略过。
	 */
	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		//得到【自定义标签上的权限】
        SystemPrivilege methodPrivilege =new SystemPrivilege(new SystemPrivilegePK(this.module,this.privilege));
        //从session获取当前用户user对象
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        
		//【将标签上定义的权限和当前user所具有的权限进行比对】
		for(PrivilegeGroup group : user.getGroups()){
			//如果该权限组下包含要访问该标签所需要的权限，就放行  
			if(group.getPrivileges().contains(methodPrivilege)){
				//【具有权限】
				result = true;
				break;
			}
		}
		//EVAL_BODY_INCLUDE————>常量1，执行标签体的内容
		//SKIP_BODY————>常量0，略过标签体的内容
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}
