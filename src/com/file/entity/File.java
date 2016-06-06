package com.file.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.dept.entity.Dept;
import com.user.entity.User;
//【文件实体】————>【任务管理系统先不引进该模块】
//@Entity
public class File implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer fileId;				
	private String fileName;		
	private String fileNote;				
	//【子类别】
	private Set<File> childfiles = new HashSet<File>();
	//【所属父类】
	private File parent;
	private Dept dept;
	private User user;
	
	//构造函数
	public File() {}
	public File(Integer fileId) {
		this.fileId = fileId;
	}
	public File(Integer fileId, String fileName) {
		this.fileId = fileId;
		this.fileName = fileName;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	@Column(length=36,nullable=false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(length=200,nullable=true)
	public String getFileNote() {
		return fileNote;
	}
	public void setFileNote(String fileNote) {
		this.fileNote = fileNote;
	}
	
	/*产品(当前对象)和子类别(属性)是一对多关联关系，一是关联关系的被维护端(需要定义mappedBy属性)
	 * 同时，mappedBy的值代表多的一方中的哪一个属性负责关联关系的维护——指定需要由ProductType(当前类)中的parent属性维护关系。
	 * 【注意】默认是延迟加载的，当entityManageFactory关闭之后，就不能够再获得该延迟加载的属性了，要想在其关闭之后，又获得该属性
	 * ——解决方案，在web.xml文件中配置解决延迟加载问题的配置！
	 */
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<File> getChildfiles() {
		return childfiles;
	}
	public void setChildfiles(Set<File> childfiles) {
		this.childfiles = childfiles;
	}
	
	/*产品类别(当前类别)和父类别是多对一关联关系，多的一方为关系维护端
	 *指定所维护的外键字段的名称是 parentid(修改默认的外键名称)!
	 *optional的值默认就是true，代表该parent熟悉感可以不存在值(顶级类别无父类)
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentid")
	public File getParent() {
		return parent;
	}
	public void setParent(File parent) {
		this.parent = parent;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="dept_no")
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="user_no")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//重写hashCode方法和equals方法，方便与对象的比较
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
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
		File other = (File) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		return true;
	}
}