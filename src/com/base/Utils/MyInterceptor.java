package com.base.Utils;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.entity.SystemPrivilegePK;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.privilege.annotation.Permission;
import com.user.entity.User;
//【自定义拦截器】——用于拦截请求判断是否拥有权限的拦截器
public class MyInterceptor implements Interceptor{
	private static final long serialVersionUID = 1L;
	
	private String message;	//提示消息
	private String urladdress;
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
	//拦截到action时就会执行该方法(核心)——>该方法实际上属于AOP编程里的环绕通知
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获得被拦截到的action的方法名
		String methodName=invocation.getProxy().getMethod();
		
        Method currentMethod=invocation.getAction().getClass().getMethod(methodName);
		
        if(currentMethod!=null && currentMethod.isAnnotationPresent(Permission.class)){
        	//获取方法上的注解
        	Permission permission=currentMethod.getAnnotation(Permission.class);
        	
            //新建一个【注解上的权限】SystemPrivilege对象
            SystemPrivilege methodPrivilege =new SystemPrivilege(new SystemPrivilegePK(permission.module(),permission.privilege()));
            
            //从session获取当前用户user对象
            User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
            
			//【将方法上注解的权限和当前user所具有的权限进行比对】
			for(PrivilegeGroup group : user.getGroups()){
				//如果该权限组下包含要访问该方法所需要的权限，就放行  
				if(group.getPrivileges().contains(methodPrivilege)){
					//【具有权限】
					return invocation.invoke();//【相当于放行】
				}
			}
			//【没有权限】
			message = "对不起，您没有操作该功能的权限！";
			urladdress = SiteUrl.readUrl("manager.welcome");
			//首先拿到request,response,session,context对象
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("message", message);
			request.setAttribute("urladdress", urladdress);
			return "message";
        }
        
        //【公开功能】——方法上没有限定权限的注解
        return invocation.invoke();//【相当于放行】
	}
	//销毁
	@Override
	public void destroy() {}
	//实例化
	@Override
	public void init() {}
}
