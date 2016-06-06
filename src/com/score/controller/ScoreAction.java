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
//【处理分数的action】
@Controller		//默认bean的名称是：scoreAction
@Scope("prototype")
public class ScoreAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String workname;	//【任务名称】
	private Integer workId;		//【任务ID】
	private User user;				//【任务负责人】
	private Integer userno;		//【用户id】
	private Integer progress;		//【当前完成进度】
	private int score;			//【分数值】
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
	
	//【分数的UI】
	public String scoreUI()  {
		//首先拿到request,response,session,context对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//拿到当前work对象，并将有关参数封装传递给下一个页面显示
		Work work = workService.find(Work.class, workId);
		this.setWorkname(work.getWorkname());
		this.setUser(work.getUser());
		this.setProgress(work.getProgress());
		//存放当前work
		request.setAttribute("work", work);
		return "scoreUI";
	} 
	
	//【任务评分】——>添加分数表的数据
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
		message = "任务评分成功！";
		urladdress = SiteUrl.readUrl("manager.employeeWork");
		return "message";
	}
}