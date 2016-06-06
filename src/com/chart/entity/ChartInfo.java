package com.chart.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.user.entity.User;
import com.work.entity.Work;
//【报表数据获取的实体类】
@Entity
public class ChartInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer chartId;		//【报表数据ID】
	private Date updateTime;	//【数据更新的时间】
	private Integer progress;		//【当前进度】
	
	private Work work;				//【对应的任务】——报表数据和任务是【多对一】关联关系
	private User user;				//【报表数据关联的用户】——【多对一】关联关系
	
	public ChartInfo() {}
	public ChartInfo(Integer chartId, Date updateTime) {
		this.chartId = chartId;
		this.updateTime = updateTime;
	}
	public ChartInfo(Integer chartId) {
		this.chartId = chartId;
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getChartId() {
		return chartId;
	}
	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}
	//【时间戳类型】
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(length=50,nullable=false)
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
	//报表数据和任务是【多对一】关联关系，报表是维护端
	//【加载方式是——立即加载】————>因为后边使用到json的时候，可能会出现延迟加载的异常
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinColumn(name="work_no")
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	//报表和用户是多对一关联关系【声明它是维护方，定义级联刷新即可】，员工为空
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="user_no")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chartId == null) ? 0 : chartId.hashCode());
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
		ChartInfo other = (ChartInfo) obj;
		if (chartId == null) {
			if (other.chartId != null)
				return false;
		} else if (!chartId.equals(other.chartId))
			return false;
		return true;
	}
}