package com.base.Utils;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts2.ServletActionContext;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.entity.SystemPrivilege;
import com.privilege.entity.SystemPrivilegePK;
import com.user.entity.User;
/*���Զ���Ȩ�ޱ�ǩ�ࡿ
 * 		����>����	�����û�û��Ȩ��ִ�еĹ���ģ�飬������������ִ�У����һ�Ҫ����"��ť"���أ�����©���û���
 * 		����>���������ͨ���Զ����ǩ�����
 * �����衿��
 * 		1.���ϱ�ǩ��jspҳ�����λ�ü����Զ����ǩ���Լ����øñ�ǩ��Ȩ��
 * 		2.�Զ����ǩ�����ࡪ���������̳�TagSupport������д��doStartTag�����������ڲ�ʵ��Ȩ��У�飩
 * 		3.���ñ�ǩ�⡪�������ļ���tld�ļ�
 * 		4.��ʹ�õ��Զ����ǩ��ҳ���ļ������Զ����tld�ļ���
 * 		�����ǣ�<%@ taglib uri="http://www.liyao.com/taskManage" prefix="privilege"%>
 * ��һ��ע�⡿��ʱ��tld�ļ����Ҳ���������һ��Ҫ����WEB-INF�ĸ�Ŀ¼�¡�
 *
 * 
 */
public class PermissionTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String module;	//	Ȩ��ģ�顪����ҳ���ǩ����һ�¼��ɡ�
	private String privilege;	//Ȩ��ֵ
	
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
	
	/*����дdoStartTag��������������ֵ��int
	 * �Զ����ǩ<privilege:permission module="dept" privilege="update">��������</privilege:permission>
	 * ��ִ�е��Զ����ǩ��ʱ�򣬾ͻᴥ�����doStartTagУ�鷽��������ֵ1��ִ�У�0���Թ���
	 */
	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		//�õ����Զ����ǩ�ϵ�Ȩ�ޡ�
        SystemPrivilege methodPrivilege =new SystemPrivilege(new SystemPrivilegePK(this.module,this.privilege));
        //��session��ȡ��ǰ�û�user����
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        
		//������ǩ�϶����Ȩ�޺͵�ǰuser�����е�Ȩ�޽��бȶԡ�
		for(PrivilegeGroup group : user.getGroups()){
			//�����Ȩ�����°���Ҫ���ʸñ�ǩ����Ҫ��Ȩ�ޣ��ͷ���  
			if(group.getPrivileges().contains(methodPrivilege)){
				//������Ȩ�ޡ�
				result = true;
				break;
			}
		}
		//EVAL_BODY_INCLUDE��������>����1��ִ�б�ǩ�������
		//SKIP_BODY��������>����0���Թ���ǩ�������
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}
