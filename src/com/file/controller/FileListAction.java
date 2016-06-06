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
//���ļ��б�
@Controller		//Ĭ��bean�������ǣ�"fileListAction"
@Scope("prototype")
public class FileListAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer fileId;				
	private String fileName;		
	private String fileNote;				
	//�������
	private Set<File> childfiles = new HashSet<File>();
	//���������ࡿ
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
	
	//��������൱�е�execute����(ִ�е���˼)
	public String execute() throws Exception {
		
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions=request.getSession();
		User user = (User)sessions.getAttribute("user");
		
		StringBuilder sb = new StringBuilder("o.dept.deptname=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(user.getDept().getDeptname());	//��?1��������ֵ
		
		if(this.getParentId() != null && this.getParentId()>0){
			sb.append(" and o.parent.fileId=?"+(params.size()+1));//ʵ����Ҳ��?2
			params.add(parentId);//��?2��������ֵ����������������idֵ���õ���������ȥ
		}else {
			//��������𡿡������û�и����,��֤�ڶ������ҳ�浱��ֻ��ʾ������𣬶�����ʾ��������ݣ�
			sb.append(" and o.parent is null");
		}
		
		//����PageView���󲢳�ʼ����ע�⣺��ǰҳ������������������������ġ���>������Ҫ�õ�formbean����
		PageView<File> pageView = new PageView<File>(8, this.getPage());	//������ÿҳ��ʾ��¼������ǰҳ��
		
		int firstindex = (pageView.getCurrentpage()-1) * pageView.getMaxresult();//��ȡ����ʼ����
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fileId", "asc");
		
		QueryResult<File> qr = fileService.getScrollData(File.class, 
				firstindex, pageView.getMaxresult(), sb.toString(), params.toArray(),orderby);
		
		pageView.setQueryResult(qr);//set�����ڲ������������ã��Ѿ���������ҳ������������ֵ
		
		request.setAttribute("pageView", pageView);//����װ�õ�����pageview�����ŵ�request��
		return SUCCESS;
	}
}