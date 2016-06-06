package com.user.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.chart.entity.ChartInfo;
import com.dept.entity.Dept;
import com.privilege.entity.PrivilegeGroup;
import com.score.entity.Score;
import com.work.entity.Work;
//��Ա��ʵ�塿
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userno;							//��id�š�
	private String userUM;							//��UM�š�
	private String username;							//���û�����
	private String realname;							//����ʵ���ơ�
	private String password;							//�����롿
	private String email;								//�����䡿
	private Gender gender;							//���Ա�
	private Dept dept;									//���������š����һ
	private int identity;								//���û���ݡ�
	private Set<Work> works = new HashSet<Work>();	//���е�����һ�Զ�
	//������Ա����
	private Set<User> childusers = new HashSet<User>();
	//�������ϼ���
	private User parent;

	//��Ա�����е�����Ȩ���顿_��Զ�
	private Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();	 
	
	//��������_һ�Զ�
	private Set<Score> score = new HashSet<Score>();
	
	//����Ӧ�ı����¼��_һ�Զ�
	private Set<ChartInfo> chartInfo = new HashSet<ChartInfo>();
	
	public User() {}
	public User(String userUM, String username, String realname,
			String password, Gender gender) {
		this.userUM = userUM;
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.gender = gender;
	}

	@Id @Column(length=36)
	@GeneratedValue
	public Integer getUserno() {
		return userno;
	}
	public void setUserno(Integer userno) {
		this.userno = userno;
	}
	
	@Column(length=2,nullable=false)
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	@Column(length=20,nullable=false,unique=true)
	public String getUserUM() {
		return userUM;
	}
	public void setUserUM(String userUM) {
		this.userUM = userUM;
	}
	
	@Column(length=20,nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length=20,nullable=false)
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Column(length=32,nullable=false)		//MD5����16λ/32λ...
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length=45)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *��ö�����͵��Ա𣬲���ע��@Enumerated��ӳ��ö�����͡� 
	 * ӳ��ö�����͵����ݿ��ֶΣ����Բ����������ͣ�STRING,ORDINAL(����ֵ)
	 * ����>String(���ݿ��ֶ����ɵ�������varchar���ͣ�����Ҫ���峤��)
	 * ����>ORDINAL����ֵ(���ݿ��ֶ�Ĭ������int����)
	 */
	@Enumerated(EnumType.STRING)
	@Column(length=5)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	//Ա���Ͳ����Ƕ��һ��,���źſ���Ϊ�գ�
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="dept_no")	//���ָ����ǲ��ű��idֵ��
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}

	//Ա���͹�����һ�Զ�ģ�������Ϊ��
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST},mappedBy="user")
	public Set<Work> getWorks() {
		return works;
	}
	public void setWorks(Set<Work> works) {
		this.works = works;
	}
	
	/*Ա��(��ǰ����)������Ա��(����)��һ�Զ������ϵ��һ�ǹ�����ϵ�ı�ά����(��Ҫ����mappedBy����)
	 * ͬʱ��mappedBy��ֵ������һ���е���һ�����Ը��������ϵ��ά������ָ����Ҫ��User(��ǰ��)�е�parent����ά����ϵ��
	 * ��ע�⡿Ĭ�����ӳټ��صģ���entityManageFactory�ر�֮�󣬾Ͳ��ܹ��ٻ�ø��ӳټ��ص������ˣ�Ҫ������ر�֮���ֻ�ø�����
	 * ���������������web.xml�ļ������ý���ӳټ�����������ã�
	 */
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<User> getChildusers() {
		return childusers;
	}
	public void setChildusers(Set<User> childusers) {
		this.childusers = childusers;
	}
	
	/*����Ա��(��ǰ���)���ϼ��Ƕ��һ������ϵ�����һ��Ϊ��ϵά����
	 *ָ����ά��������ֶε������� parentid(�޸�Ĭ�ϵ��������)!
	 *optional��ֵĬ�Ͼ���true�������parent��Ϥ�п��Բ�����ֵ(��������޸���)
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentid")
	public User getParent() {
		return parent;
	}
	public void setParent(User parent) {
		this.parent = parent;
	}
	
	/*���û���Ȩ���顿�Ƕ�Զ������ϵ������ΪҪΪԱ�����ƶ�Ȩ���飬���Զ����û�Ϊ�����ϵ��ά���ˡ�
	 * �����������ԣ�ֻ��Ҫ����ˢ�£��û��Ѿ����ڣ�Ȩ����Ҳ�Ѿ����ڣ�֪ʶ��Ҫ������ϵ���ѣ�
	 * �������ز��ԣ���Ҫ�������أ���Ϊ�û�һ��¼��ϵͳ����Ҫ��Ҫʹ�õ�Ȩ�ޣ�����Ȩ���жϣ����Ծͱ��������õ����Ӧ��Ȩ���顣
	 * �ڶ�Զ������ϵ�У���ϵ��ά���������ϵ������Ȳ������������м��Ķ��塿��Ҳ������ά���˶��塣
	 * ��������Ķ�����@JoinTable��ɡ�����ά�����ǡ��û�������ά�����ǡ�Ȩ���顿������Ȩ����������������������ô����Ҫ��������ֶ�
	 * joinColumns:�����м���������ϵά����֮���������壬Ҳ�����м���и������������
	 * inverseJoinColumns�������м���������ϵ�ı�ά����֮���������塣
	 */
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="user_group",
					//�����м���������ϵ��ά���ˡ�֮����������,����>����Ͳ���Ҫ����ָ���ˣ���Ϊֻ��һ��������
					joinColumns=@JoinColumn(name="user_Id"),				
					//�����м���������ϵ�ġ���ά���ˡ�֮����������
					inverseJoinColumns=@JoinColumn(name="group_Id"))
	public Set<PrivilegeGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<PrivilegeGroup> groups) {
		this.groups = groups;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE},mappedBy="user")
	public Set<ChartInfo> getChartInfo() {
		return chartInfo;
	}
	public void setChartInfo(Set<ChartInfo> chartInfo) {
		this.chartInfo = chartInfo;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE},mappedBy="user")
	public Set<Score> getScore() {
		return score;
	}
	public void setScore(Set<Score> score) {
		this.score = score;
	}
	
	//�ṩ�����Ȩ���顿�ķ���
	public void addPrivilegeGroup(PrivilegeGroup group){
		this.getGroups().add(group);//this.groups.add(group);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userno == null) ? 0 : userno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userno == null) {
			if (other.userno != null)
				return false;
		} else if (!userno.equals(other.userno))
			return false;
		return true;
	}
	
	public void addDept(Dept dept){
		dept.setDeptID(11);
		dept.setDeptname("ss");
		this.dept.addUser(null);
	}
}