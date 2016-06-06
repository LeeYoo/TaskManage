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
//���ļ�ʵ�塿��������>���������ϵͳ�Ȳ�������ģ�顿
//@Entity
public class File implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer fileId;				
	private String fileName;		
	private String fileNote;				
	//�������
	private Set<File> childfiles = new HashSet<File>();
	//���������ࡿ
	private File parent;
	private Dept dept;
	private User user;
	
	//���캯��
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
	
	/*��Ʒ(��ǰ����)�������(����)��һ�Զ������ϵ��һ�ǹ�����ϵ�ı�ά����(��Ҫ����mappedBy����)
	 * ͬʱ��mappedBy��ֵ������һ���е���һ�����Ը��������ϵ��ά������ָ����Ҫ��ProductType(��ǰ��)�е�parent����ά����ϵ��
	 * ��ע�⡿Ĭ�����ӳټ��صģ���entityManageFactory�ر�֮�󣬾Ͳ��ܹ��ٻ�ø��ӳټ��ص������ˣ�Ҫ������ر�֮���ֻ�ø�����
	 * ���������������web.xml�ļ������ý���ӳټ�����������ã�
	 */
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE},mappedBy="parent")
	public Set<File> getChildfiles() {
		return childfiles;
	}
	public void setChildfiles(Set<File> childfiles) {
		this.childfiles = childfiles;
	}
	
	/*��Ʒ���(��ǰ���)�͸�����Ƕ��һ������ϵ�����һ��Ϊ��ϵά����
	 *ָ����ά��������ֶε������� parentid(�޸�Ĭ�ϵ��������)!
	 *optional��ֵĬ�Ͼ���true�������parent��Ϥ�п��Բ�����ֵ(��������޸���)
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
	
	//��дhashCode������equals���������������ıȽ�
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