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
//【过滤器的使用】
/*过滤器是一个标准的java类，它的使用需要实现Filter接口，那就必须要实现
* 该接口中的所有方法（包括三个方法）有两个是生命周期的回调方法！
* 过滤器也必须在配置文件xml中里配置才可以使用。*/
public class PermissionFilter implements Filter {

	//过滤器被销毁的方法
	public void destroy() {
		System.out.println("PermissionFilter被销毁了....");
	}

	//【执行过滤的方法】很重要参数（Servlet的请求，响应，整个过滤器链）
	//该方法是每当过滤器拦截到一次请求的时候，该方法就要被执行一次。
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		/*方法参数定义的request和response是ServletRequest类型。
		 * 实际上就是HttpServlet类型的,需要转化过来,否则很多方法用不了！*/
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		//获取用户的请求路径
		String path = request.getServletPath();
		System.out.println("请求路径是：" + path);//打印路径
		
		//【开始判断到底让不让用户过去？】
		if(hasLogin(request)){
			/*	过滤器链chain的doFilter方法：将当前的请求和响应传进去，
			把请求转给下一个过滤器处理，如果没有下一个过滤器处理，就直
			接转给目标组件了————————————【放行】*/
			chain.doFilter(request, response);//放行
		}else{
			request.getRequestDispatcher("/WEB-INF/jsp/base/noLogin.jsp").forward(request, response);
		}
	}
	
	//判断用户是否登陆
	private boolean hasLogin(HttpServletRequest request) {
		//很简单，只需要判断session中有没有该用户名即可
		//先拿到session对象
		HttpSession session = request.getSession();
		//再从session中取出用户名
		String username = (String)session.getAttribute("username");
		
		return username != null;
	}

	//过滤器被初始化的方法
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("PermissionFilter被初始化了....");
		//注册转换器——注册上去了，可是拦截请求参数有问题！
		//ConvertUtils.register(new SystemPrivilegePKConventer(), SystemPrivilegePK.class);
	}
}