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
//���������ݻ�ȡ��ʵ���ࡿ
@Entity
public class ChartInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer chartId;		//����������ID��
	private Date updateTime;	//�����ݸ��µ�ʱ�䡿
	private Integer progress;		//����ǰ���ȡ�
	
	private Work work;				//����Ӧ�����񡿡����������ݺ������ǡ����һ��������ϵ
	private User user;				//���������ݹ������û������������һ��������ϵ
	
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
	//��ʱ������͡�
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
	
	//�������ݺ������ǡ����һ��������ϵ��������ά����
	//�����ط�ʽ�ǡ����������ء���������>��Ϊ���ʹ�õ�json��ʱ�򣬿��ܻ�����ӳټ��ص��쳣
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinColumn(name="work_no")
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	//������û��Ƕ��һ������ϵ����������ά���������弶��ˢ�¼��ɡ���Ա��Ϊ��
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