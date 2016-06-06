package JUnitTest;
import java.util.LinkedHashMap;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.base.Utils.DateUtils;
import com.dept.entity.Dept;
import com.dept.service.DeptService;
import com.paging.entity.QueryResult;
import com.user.entity.Gender;
import com.user.entity.User;
import com.user.service.UserService;
import com.work.entity.GrandNum;
import com.work.entity.Work;
import com.work.service.WorkService;
//员工实体类的单元测试
public class Test_Task {

	//客户端通过接口来使用UserServiceBean的实现类以及其方法的。
	private static  UserService userservice;
	private static  DeptService deptservice;
	private static  WorkService workservice;
	
	@BeforeClass
	//立刻执行(适合做数据的初始化动作)
	public static void setUpBeforeClass() throws Exception {
		try {
			//实例化Spring容器后，其配置文件beans.xml中默认的所有单例bean也都会被创建。一旦entityManagerFactory对象被创建，就会引起表结构的生成。
			ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
			
			//从Spring容器中取得一个bean实例(类的简单名称)
			userservice = (UserService)act.getBean("userServiceBean");
			deptservice = (DeptService)act.getBean("deptServiceBean");
			workservice = (WorkService)act.getBean("workServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test		//保存用户
	public void save_user() {
		Random r = new Random();//【随机数对象】
		String[] firstNames = {"张","王","李","赵","唐","胡"};
		for (int i = 1; i < 51; i++) {
			
			//user.setWork(new Work("工作"+i, DateUtils.stringToDate("2015-10-19"), GrandNum.Simple));
			//user.setWork(new Work(1 + r.nextInt(6)));
			User user =new User();
			user.setUsername("user"+i);
			user.setPassword("user"+i);
			user.setIdentity(1+r.nextInt(3));
			user.setRealname(firstNames[r.nextInt(firstNames.length)] + (char)('A' + r.nextInt(26)) + (char)('A' + r.nextInt(26)));
			user.setGender(i%2==0 ? Gender.MAN : Gender.WOMEN);
			user.setUserUM("UM"+i);
			
			userservice.save(user);
		}
	}
	
	@Test
	public void dayin(){
		System.out.println("执行建表");
	}
	
	@Test		//保存部门
	public void save_dept(){
		// 添加6个部门(创建部门对象的时候利用Dept类中的带参方法)
		String[] deptnames = { "办公室", "财务企划部", "人力资源部", "风险条线", "运营管理部","综合金融部"};
		for (int i = 0; i < deptnames.length; i++) {
			Dept d = new Dept(deptnames[i]);
			deptservice.save(d);
		}
	}
	
	@Test		//保存任务
	public void save_work() {
		Random r = new Random();//【随机数对象】
		String[] jobNames = {"工作_1","工作_2","工作_3","工作_4","工作_5","工作_6"};
		for (int i = 0; i < jobNames.length; i++) {
			Work work = new Work(jobNames[i]);
			work.setPlaytime(DateUtils.stringToDate("2015-10-19"));
			work.setGrand(GrandNum.Simple);
			
			workservice.save(work);
		}
	}
	@Test		//更新用户
	public void update() {
		User user = userservice.find(User.class,1);
		user.setEmail("liyao@qq.com");
		
		userservice.update(user);
	}
	@Test		//删除用户
	public void delete() {
		userservice.delete(User.class,3);
	}
	@Test		//查找用户
	public void find() {
		User user = userservice.find(User.class,2);
		System.out.println(user.getUserUM());
	}
	/*@Test		//测试获取运行期的类 (这里是其子类userserviceBean类)
	public void getChildClass(){
		System.out.println(userservice.getChildClass());
	}
	@Test		//测试获取运行期的类的父类当中支持泛型的那个类的泛型中定义的具体参数值
	public void getEntityClass(){
		System.out.println(userservice.getEntityClass());
	}
	@Test		//测试获取实体的总记录数
	public void getCount(){
		System.out.println(userservice.getCount());
	}*/
	
	/**测试【分页查询】的各个方法
	 * 第一种：直接对数据库数据进行分页
	 * 第二种：先对数据进行排序，再对排序后的数据进行分页查询(需要使用Linked有序集合)
	 */
	@Test	
	public void getScrollData(){
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();//new一个map集合
		
		orderby.put("email", "asc");	//email是升序排序的
		orderby.put("password", "desc");	//密码是降序排序的
		
		//通过改变参数值，来测试不同的【分页查询方法】
		QueryResult<User> qt = userservice.getScrollData(User.class,0,5);//查询第一页(5条记录)：0-4,5-9
		
		//迭代查询的结果集，并打印
		for(User user : qt.getResultlist()){
			System.out.println(user.getUserUM());	//打印
		}
		System.out.println("总记录数："+qt.getRecordtotal());	//打印总记录数(角标从0开始的，总记录数需要+1更直观)
	}
	
	/**测试【用户是否存在】的方法
	@Test	
	public void exsit(){
		System.out.println(userservice.exsit("liyao1011"));
	}*/
}