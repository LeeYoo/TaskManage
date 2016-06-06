package com.score.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.base.Utils.SiteUrl;
import com.opensymphony.xwork2.ActionSupport;
import com.score.entity.Score;
import com.score.service.ScoreService;
import com.user.entity.User;
import com.user.service.UserService;
import com.work.entity.Work;
import com.work.service.WorkService;
//�����������action��
@Controller		//Ĭ��bean�������ǣ�scoreAction
@Scope("prototype")
public class ScoreAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String workname;	//���������ơ�
	private Integer workId;		//������ID��
	private User user;				//���������ˡ�
	private Integer userno;		//���û�id��
	private Integer progress;		//����ǰ��ɽ��ȡ�
	private int score;			//������ֵ��
	private String message;
	private String urladdress;
	
	public ScoreAction() {}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUserno() {
		return userno;
	}
	public void setUserno(Integer userno) {
		this.userno = userno;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	@Resource ScoreService scoreService;
	@Resource WorkService workService;
	@Resource UserService userService;
	
	//��������UI��
	public String scoreUI()  {
		//�����õ�request,response,session,context����
		HttpServletRequest request = ServletActionContext.getRequest();
		//�õ���ǰwork���󣬲����йز�����װ���ݸ���һ��ҳ����ʾ
		Work work = workService.find(Work.class, workId);
		this.setWorkname(work.getWorkname());
		this.setUser(work.getUser());
		this.setProgress(work.getProgress());
		//��ŵ�ǰwork
		request.setAttribute("work", work);
		return "scoreUI";
	} 
	
	//���������֡�����>��ӷ����������
	public String add() throws Exception{
		Work work = workService.find(Work.class, workId);
		Score s = work.getScore();
		if(s != null){
			s.setScore(score);
			workService.update(work);
		}else {
			Score sc = new Score();
			sc.setScore(score);
			sc.setWork(work);
			sc.setUser(work.getUser());
			scoreService.save(sc);
			work.setScore(sc);
			workService.update(work);
		}
		message = "�������ֳɹ���";
		urladdress = SiteUrl.readUrl("manager.employeeWork");
		return "message";
	}
}