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
//【员工实体】
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userno;							//【id号】
	private String userUM;							//【UM号】
	private String username;							//【用户名】
	private String realname;							//【真实名称】
	private String password;							//【密码】
	private String email;								//【邮箱】
	private Gender gender;							//【性别】
	private Dept dept;									//【隶属部门】多对一
	private int identity;								//【用户身份】
	private Set<Work> works = new HashSet<Work>();	//【承担任务】一对多
	//【下属员工】
	private Set<User> childusers = new HashSet<User>();
	//【所属上级】
	private User parent;

	//【员工具有的所有权限组】_多对多
	private Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();	 
	
	//【分数】_一对多
	private Set<Score> score = new HashSet<Score>();
	
	//【对应的报表记录】_一对多
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
	
	@Column(length=32,nullable=false)		//MD5编码16位/32位...
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
	 *【枚举类型的性别，采用注解@Enumerated来映射枚举类型】 
	 * 映射枚举类型到数据库字段，可以采用两种类型：STRING,ORDINAL(索引值)
	 * ——>String(数据库字段生成的类型是varchar类型，就需要定义长度)
	 * ——>ORDINAL索引值(数据库字段默认生成int类型)
	 */
	@Enumerated(EnumType.STRING)
	@Column(length=5)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	//员工和部门是多对一的,部门号可以为空！
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="dept_no")	//外键指向的是部门表的id值！
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}

	//员工和工作是一对多的，工作可为空
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST},mappedBy="user")
	public Set<Work> getWorks() {
		return works;
	}
	public void setWorks(Set<Work> works) {
		this.works = works;
	}
	
	/*员工(当前对象)和下属员工(属性)是一对多关联关系，一是关联关系的被维护端(需要定义mappedBy属性)
	 * 同时，mappedBy的值代表多的一方中的哪一个属性负责关联关系的维护——指定需要由User(当前类)中的parent属性维护关系。
	 * 【注意】默认是延迟加载的，当entityManageFactory关闭之后，就不能够再获得该延迟加载的属性了，要想在其关闭之后，又获得该属性
	 * ——解决方案，在web.xml文件中配置解决延迟加载问题的配置！
	 */
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<User> getChildusers() {
		return childusers;
	}
	public void setChildusers(Set<User> childusers) {
		this.childusers = childusers;
	}
	
	/*下属员工(当前类别)和上级是多对一关联关系，多的一方为关系维护端
	 *指定所维护的外键字段的名称是 parentid(修改默认的外键名称)!
	 *optional的值默认就是true，代表该parent熟悉感可以不存在值(顶级类别无父类)
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentid")
	public User getParent() {
		return parent;
	}
	public void setParent(User parent) {
		this.parent = parent;
	}
	
	/*【用户和权限组】是多对多关联关系——因为要为员工股制定权限组，所以定义用户为管理关系的维护端。
	 * ——级联策略：只需要级联刷新！用户已经存在，权限组也已经存在，知识需要建立关系而已！
	 * ——加载策略：需要立即加载，因为用户一登录到系统，就要需要使用到权限，进行权限判断，所以就必须首先拿到其对应的权限组。
	 * 在多对多关联关系中，关系的维护方定义关系的外键等操作，包括【中间表的定义】，也必须在维护端定义。
	 * 【关联表的定义由@JoinTable完成】——维护端是【用户】，被维护端是【权限组】——而权限主键则是联合主键，那么就需要两个外键字段
	 * joinColumns:代表中间表与关联关系维护方之间的外键定义，也就是中间表中各外键的属性名
	 * inverseJoinColumns：代表中间表与关联关系的被维护方之间的外键定义。
	 */
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="user_group",
					//——中间表与关联关系【维护端】之间的外键定义,——>这儿就不需要具体指明了，因为只有一个主键！
					joinColumns=@JoinColumn(name="user_Id"),				
					//——中间表与关联关系的【被维护端】之间的外键定义
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
	
	//提供【添加权限组】的方法
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