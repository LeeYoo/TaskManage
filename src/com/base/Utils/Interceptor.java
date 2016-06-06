package com.base.Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/*	—————————————【使用AOP技术】———————————————
	第一步：将该类声明为一个切面【切面】，即就是@Aspect注解。
	第二步：在切面里面要【定义切入点】——对类中的方法进行拦截
		【切入点定义里面的AOP的表达式语言语法】
		【例子】@Pointcut("execution(* cn.itcast.Service..*.*(..))")
			1.execution:代表执行的意思，即就是在执行到业务方法的时候，我要进行拦截。
			2.第一个*：代表返回值的类型，*代表任意类型。
			3.cn.itcast.Service..：代表包名(即就是要对哪个包底下的类进行拦截，后面的".."代表对其子包下的类也要实施拦截)
			4.后面的*：代表要被拦截的那个类，*代表所有类。
			5.最后一个*：代表要被拦截的方法，*代表所有的方法。
			6.后面的(..)：代表方法参数可任意(可有可无，可多可少)
		
		【实例】——表达式语言特殊需求
			1.要求返回值类型是String类型：execution(java.lang.String cn.itcast.Service..*.*(..))
			2.要求拦截所有的非void类型的方法：execution(!void cn.itcast.Service..*.*(..))
			3.要求输入参数的第一个类型是String的，后面不管有没有参数：execution(* cn.itcast.Service..*.*(java.lang.String,..))
			4.要求要对Service包底下的所有类，包括子包底下的所有类都进行拦截：execution(* cn.itcast.Service..*.*(..))
		
		【另外】
		我们定义完了切入点的表达式之后，它就会为满足这个表达式里面的所有对象都创建代理对象。
		如果Spring发现被拦截的类实现了接口，那么它就会采用JDK提供的Proxy动态代理技术来创建代理对象;
		如果Spring发现被拦截的类未实现了接口，那么它就会采用CGlib这种方式来创建代理对象。
	
	第三步：【定义通知】——即就是拦截到方法之后所要进行的处理工作！
		特点：定义通知的时候，通知的注解里面要写入切入点的名称！
	【定义前置通知】——在执行拦截到的方法之前执行，前置通知执行完成之后，还会继续执行被拦截到的方法。
	【定义后置通知】——在执行拦截到的方法之后执行。
	【定义最终通知】——无论如何都会执行到的。
	【定义异常通知】
	【定义环绕通知】===struts2拦截器就是环绕通知
		——环绕通知的定义格式固定的;如果使用环绕通知的话，那么就必须确保环绕通知内部必须执行proceed()方法,
		如果环绕通知内部没有执行该方法，那么业务bean中被拦截到这个方法是不会执行的。
		在执行该方法的时候，如果后面还有切面，先执行后面的切面，如果后面没有切面了，再执行目标对象的业务方法】

	【注意】	切面定义完了之后，必须要将其交给Spring管理，否则不会有效果。
				做法：1.使用注解；2.在XML配置文件中采用bean方式进行配置。
 */
//@Aspect		将该类声明为切面
//@Component	交给Spring来管理（@Component代表该类不好识别划分，泛指主键标识即可）
public class Interceptor {
	
	//【定义切入点】
	@Pointcut("execution(java.lang.String com..*.*(..)")
	private void actionMethod(){}	//【定义切入点的名称(用方法来定义)】
		
	//【定义环绕通知】——被拦截到的方法是否执行，是需要进行判断的。所以根据业务，只能使用环绕通知来完成权限方法拦截。
	@Around("actionMethod()")
	public Object interceptor(ProceedingJoinPoint pjp) throws Throwable{
		//if(){}  这块比较适合做权限判断
		System.out.println("拦截到了："+pjp.getSignature().getName()+"方法");
		Object result = pjp.proceed();//【必须要执行该方法】
		return result;
	}
}
