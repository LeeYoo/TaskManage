package com.privilege.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.user.entity.User;
/*【权限组实体】——权限和权限组是多对多关联关系——【权限组实体】是关联关系维护端
 * 比如建立关系：privilegeGroup.getPrivileges().add(privilege);体现在中间表就相当于insert into ...语句。
 * 比如解除关系：privilegeGroup.getPrivileges().remove(privilege);体现在中间表就相当于delete from ...语句。
 * 这些操作都是在关联关系的维护端完成的。
 *  */
@Entity
public class PrivilegeGroup implements  Serializable{
	private static final long serialVersionUID = 1L;
	
	private String groupId;		//采用UUID生成
	private String name;		//权限组名称
	
	//权限组所具有的权限——权限和权限组是多对多关联关系——【权限组实体】是关联关系维护端
	private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();	
	
	//【权限组所对应的员工】_多对多
	private Set<User> users = new HashSet<User>();
	
	//构造函数
	public PrivilegeGroup() {}
	public PrivilegeGroup(String groupId) {
		this.groupId = groupId;
	}
	@Id @Column(length=36)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*【权限和权限组是多对多关系】——根据需求可定义权限组为关联关系的维护端！（在更新权限组的时候，更新其所具有的权限）
	 * 多对多关联关系，是由中间表(关联表)来完成关联动作的！
	 * ——【级联策略】——只需要级联刷新即可！
	 * 不需要级联保存(权限在系统初始化的时候就已经先存在了，不需要再在保存权限组的时候再进行权限的保存操作)
	 * 不需要级联更新(功能模块权限一旦系统初始化完成之后，就不需要修改了)
	 * 不需要级联删除(在删除权限组的时候，肯定不可能级联删除掉该组下的所有权限)
	 * ——【抓取策略】——默认是延迟加载的，我们这里需要【立即加载】，因为用户一旦登陆到后台就要使用到后台的功能，那么就需要权限。
	 * 在多对多关联关系中，关系的维护方定义关系的外键等操作，包括【中间表的定义】，也必须在维护端定义。
	 * 【关联表的定义由@JoinTable完成】——维护端是【权限组】，被维护端是【权限】——而权限主键则是联合主键，那么就需要两个外键字段
	 * joinColumns:代表中间表与关联关系维护方之间的外键定义，也就是中间表中各外键的属性名
	 * inverseJoinColumns：代表中间表与关联关系的被维护方之间的外键定义。
	 * referencedColumnName：代表声明中间表的外键字段具体是和关联关系被维护端中的哪一个字段相关联的！
	 */
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="group_privilege",
					//——中间表与关联关系【维护端】之间的外键定义,——>这儿就不需要具体指明了，因为只有一个主键！
					joinColumns=@JoinColumn(name="g_Id"),				
					//——中间表与关联关系的【被维护端——被维护端是联合主键的】之间的外键定义——>联合主键需要具体指明！
					inverseJoinColumns={@JoinColumn(name="p_module",referencedColumnName="module"),
													@JoinColumn(name="p_privilege",referencedColumnName="privilege")})		
	public Set<SystemPrivilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<SystemPrivilege> privileges) {
		this.privileges = privileges;
	}
	
	/*【权限组和用户是多对多关系】——当前类是关联关系【被维护端】！
	 * 级联策略：根据应用需求，它没有级联保存和级联更新，基本上也用不到级联删除！
	 * 声明mappedBy和默认本身就是延迟加载。
	 */
	@ManyToMany(cascade=(CascadeType.REFRESH),mappedBy="groups")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	//提供【添加privilege权限】的方法
	public void addSystemPrivilege(SystemPrivilege privilege){
		this.getPrivileges().add(privilege);//this.privileges.add(privilege);
	}
	//提供【删除privilege权限】的方法
	public void removeSystemPrivilege(SystemPrivilege privilege){
		/*	删除某个指定的privilege对象，要判断privileges集合中是否包含要删除的privilege，
			如果包含，就将其删除。
			那么如何判断privilege存在于该privileges集合中呢？就需要通过privilege的id来判断了！
			所以在privilege类中需要重写hashCode()和equals(Object obj)方法来判断了。*/
		if(this.privileges.contains(privilege)){	//contains底层会自动调用hashCode方法来判断。	
			this.getPrivileges().remove(privilege);			
		}
	}
		
}