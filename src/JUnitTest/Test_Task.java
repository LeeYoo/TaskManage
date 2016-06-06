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
//Ա��ʵ����ĵ�Ԫ����
public class Test_Task {

	//�ͻ���ͨ���ӿ���ʹ��UserServiceBean��ʵ�����Լ��䷽���ġ�
	private static  UserService userservice;
	private static  DeptService deptservice;
	private static  WorkService workservice;
	
	@BeforeClass
	//����ִ��(�ʺ������ݵĳ�ʼ������)
	public static void setUpBeforeClass() throws Exception {
		try {
			//ʵ����Spring�������������ļ�beans.xml��Ĭ�ϵ����е���beanҲ���ᱻ������һ��entityManagerFactory���󱻴������ͻ������ṹ�����ɡ�
			ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
			
			//��Spring������ȡ��һ��beanʵ��(��ļ�����)
			userservice = (UserService)act.getBean("userServiceBean");
			deptservice = (DeptService)act.getBean("deptServiceBean");
			workservice = (WorkService)act.getBean("workServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test		//�����û�
	public void save_user() {
		Random r = new Random();//�����������
		String[] firstNames = {"��","��","��","��","��","��"};
		for (int i = 1; i < 51; i++) {
			
			//user.setWork(new Work("����"+i, DateUtils.stringToDate("2015-10-19"), GrandNum.Simple));
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
		System.out.println("ִ�н���");
	}
	
	@Test		//���沿��
	public void save_dept(){
		// ���6������(�������Ŷ����ʱ������Dept���еĴ��η���)
		String[] deptnames = { "�칫��", "�����󻮲�", "������Դ��", "��������", "��Ӫ����","�ۺϽ��ڲ�"};
		for (int i = 0; i < deptnames.length; i++) {
			Dept d = new Dept(deptnames[i]);
			deptservice.save(d);
		}
	}
	
	@Test		//��������
	public void save_work() {
		Random r = new Random();//�����������
		String[] jobNames = {"����_1","����_2","����_3","����_4","����_5","����_6"};
		for (int i = 0; i < jobNames.length; i++) {
			Work work = new Work(jobNames[i]);
			work.setPlaytime(DateUtils.stringToDate("2015-10-19"));
			work.setGrand(GrandNum.Simple);
			
			workservice.save(work);
		}
	}
	@Test		//�����û�
	public void update() {
		User user = userservice.find(User.class,1);
		user.setEmail("liyao@qq.com");
		
		userservice.update(user);
	}
	@Test		//ɾ���û�
	public void delete() {
		userservice.delete(User.class,3);
	}
	@Test		//�����û�
	public void find() {
		User user = userservice.find(User.class,2);
		System.out.println(user.getUserUM());
	}
	/*@Test		//���Ի�ȡ�����ڵ��� (������������userserviceBean��)
	public void getChildClass(){
		System.out.println(userservice.getChildClass());
	}
	@Test		//���Ի�ȡ�����ڵ���ĸ��൱��֧�ַ��͵��Ǹ���ķ����ж���ľ������ֵ
	public void getEntityClass(){
		System.out.println(userservice.getEntityClass());
	}
	@Test		//���Ի�ȡʵ����ܼ�¼��
	public void getCount(){
		System.out.println(userservice.getCount());
	}*/
	
	/**���ԡ���ҳ��ѯ���ĸ�������
	 * ��һ�֣�ֱ�Ӷ����ݿ����ݽ��з�ҳ
	 * �ڶ��֣��ȶ����ݽ��������ٶ����������ݽ��з�ҳ��ѯ(��Ҫʹ��Linked���򼯺�)
	 */
	@Test	
	public void getScrollData(){
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();//newһ��map����
		
		orderby.put("email", "asc");	//email�����������
		orderby.put("password", "desc");	//�����ǽ��������
		
		//ͨ���ı����ֵ�������Բ�ͬ�ġ���ҳ��ѯ������
		QueryResult<User> qt = userservice.getScrollData(User.class,0,5);//��ѯ��һҳ(5����¼)��0-4,5-9
		
		//������ѯ�Ľ����������ӡ
		for(User user : qt.getResultlist()){
			System.out.println(user.getUserUM());	//��ӡ
		}
		System.out.println("�ܼ�¼����"+qt.getRecordtotal());	//��ӡ�ܼ�¼��(�Ǳ��0��ʼ�ģ��ܼ�¼����Ҫ+1��ֱ��)
	}
	
	/**���ԡ��û��Ƿ���ڡ��ķ���
	@Test	
	public void exsit(){
		System.out.println(userservice.exsit("liyao1011"));
	}*/
}