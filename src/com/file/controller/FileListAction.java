package com.file.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dept.entity.Dept;
import com.file.entity.File;
import com.file.service.FileService;
import com.opensymphony.xwork2.ActionSupport;
import com.paging.entity.PageView;
import com.paging.entity.QueryResult;
import com.user.entity.User;
//【文件列表】
@Controller		//默认bean的名称是："fileListAction"
@Scope("prototype")
public class FileListAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer fileId;				
	private String fileName;		
	private String fileNote;				
	//【子类别】
	private Set<File> childfiles = new HashSet<File>();
	//【所属父类】
	private File parent;
	private int page;
	private Dept dept;
	private User user;
	private Integer parentId;
	
	public FileListAction() {}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNote() {
		return fileNote;
	}
	public void setFileNote(String fileNote) {
		this.fileNote = fileNote;
	}
	public Set<File> getChildfiles() {
		return childfiles;
	}
	public void setChildfiles(Set<File> childfiles) {
		this.childfiles = childfiles;
	}
	public File getParent() {
		return parent;
	}
	public void setParent(File parent) {
		this.parent = parent;
	}
	public int getPage() {
		return page<1?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Resource FileService fileService;
	
	//覆盖这个类当中的execute方法(执行的意思)
	public String execute() throws Exception {
		
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		User user = (User)sessions.getAttribute("user");
		
		StringBuilder sb = new StringBuilder("o.dept.deptname=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(user.getDept().getDeptname());	//给?1参数设置值
		
		if(this.getParentId() != null && this.getParentId()>0){
			sb.append(" and o.parent.fileId=?"+(params.size()+1));//实际上也是?2
			params.add(parentId);//给?2参数设置值——把所属父类别的id值设置到参数当中去
		}else {
			//【顶级类别】——如果没有父类别,保证在顶级类别页面当中只显示顶级类别，而不显示子类别内容！
			sb.append(" and o.parent is null");
		}
		
		//创建PageView对象并初始化！注意：当前页参数，是由请求参数传过来的——>所以需要得到formbean对象！
		PageView<File> pageView = new PageView<File>(8, this.getPage());	//参数：每页显示记录数，当前页！
		
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//获取到开始索引
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fileId", "asc");
		
		QueryResult<File> qr = fileService.getScrollData(File.class, 
				firstindex, pageView.getMaxresult(), sb.toString(), params.toArray(),orderby);
		
		pageView.setQueryResult(qr);//set方法内部存在连环调用！已经计算了总页数和两个索引值
		
		request.setAttribute("pageView", pageView);//将封装好的整个pageview对象存放到request中
		return SUCCESS;
	}
}