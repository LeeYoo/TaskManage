package com.base.Utils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//����������ʹ�á�
/*��������һ����׼��java�࣬����ʹ����Ҫʵ��Filter�ӿڣ��Ǿͱ���Ҫʵ��
* �ýӿ��е����з����������������������������������ڵĻص�������
* ������Ҳ�����������ļ�xml�������òſ���ʹ�á�*/
public class PermissionFilter implements Filter {

	//�����������ٵķ���
	public void destroy() {
		System.out.println("PermissionFilter��������....");
	}

	//��ִ�й��˵ķ���������Ҫ������Servlet��������Ӧ����������������
	//�÷�����ÿ�����������ص�һ�������ʱ�򣬸÷�����Ҫ��ִ��һ�Ρ�
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		/*�������������request��response��ServletRequest���͡�
		 * ʵ���Ͼ���HttpServlet���͵�,��Ҫת������,����ܶ෽���ò��ˣ�*/
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//��ȡ�û�������·��
		String path = request.getServletPath();
		System.out.println("����·���ǣ�" + path);//��ӡ·��
		
		//����ʼ�жϵ����ò����û���ȥ����
		if(hasLogin(request)){
			/*	��������chain��doFilter����������ǰ���������Ӧ����ȥ��
			������ת����һ���������������û����һ��������������ֱ
			��ת��Ŀ������ˡ����������������������������С�*/
			chain.doFilter(request, response);//����
		}else{
			request.getRequestDispatcher("/WEB-INF/jsp/base/noLogin.jsp").forward(request, response);
		}
	}
	
	//�ж��û��Ƿ��½
	private boolean hasLogin(HttpServletRequest request) {
		//�ܼ򵥣�ֻ��Ҫ�ж�session����û�и��û�������
		//���õ�session����
		HttpSession session = request.getSession();
		//�ٴ�session��ȡ���û���
		String username = (String)session.getAttribute("username");
		
		return username != null;
	}

	//����������ʼ���ķ���
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("PermissionFilter����ʼ����....");
		//ע��ת��������ע����ȥ�ˣ���������������������⣡
		//ConvertUtils.register(new SystemPrivilegePKConventer(), SystemPrivilegePK.class);
	}
}