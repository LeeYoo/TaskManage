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
//���Զ����������������������������ж��Ƿ�ӵ��Ȩ�޵�������
public class MyInterceptor implements Interceptor{
	private static final long serialVersionUID = 1L;
	
	private String message;	//��ʾ��Ϣ
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
	//���ص�actionʱ�ͻ�ִ�и÷���(����)����>�÷���ʵ��������AOP�����Ļ���֪ͨ
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//��ñ����ص���action�ķ�����
		String methodName=invocation.getProxy().getMethod();
		
        Method currentMethod=invocation.getAction().getClass().getMethod(methodName);
		
        if(currentMethod!=null && currentMethod.isAnnotationPresent(Permission.class)){
        	//��ȡ�����ϵ�ע��
        	Permission permission=currentMethod.getAnnotation(Permission.class);
        	
            //�½�һ����ע���ϵ�Ȩ�ޡ�SystemPrivilege����
            SystemPrivilege methodPrivilege =new SystemPrivilege(new SystemPrivilegePK(permission.module(),permission.privilege()));
            
            //��session��ȡ��ǰ�û�user����
            User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
            
			//����������ע���Ȩ�޺͵�ǰuser�����е�Ȩ�޽��бȶԡ�
			for(PrivilegeGroup group : user.getGroups()){
				//�����Ȩ�����°���Ҫ���ʸ÷�������Ҫ��Ȩ�ޣ��ͷ���  
				if(group.getPrivileges().contains(methodPrivilege)){
					//������Ȩ�ޡ�
					return invocation.invoke();//���൱�ڷ��С�
				}
			}
			//��û��Ȩ�ޡ�
			message = "�Բ�����û�в����ù��ܵ�Ȩ�ޣ�";
			urladdress = SiteUrl.readUrl("manager.welcome");
			//�����õ�request,response,session,context����
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("message", message);
			request.setAttribute("urladdress", urladdress);
			return "message";
        }
        
        //���������ܡ�����������û���޶�Ȩ�޵�ע��
        return invocation.invoke();//���൱�ڷ��С�
	}
	//����
	@Override
	public void destroy() {}
	//ʵ����
	@Override
	public void init() {}
}
