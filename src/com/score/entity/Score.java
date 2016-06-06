package com.score.entity;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.user.entity.User;
import com.work.entity.Work;
//【分数的实体类】
@Entity
public class Score implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer scoreId;		//【分数ID】
	private int score;				//【分数值】
	
	private Work work;				//【对应的任务】——报表数据和任务是【多对一】关联关系
	private User user;				//【报表数据关联的用户】——【多对一】关联关系
	
	public Score() {}
	public Score(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public Score(int score) {
		this.score = score;
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	
	@Column(length=10)
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	//分数和任务是一对一关联关系，并且【任务是维护端】
	@OneToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},mappedBy="score")
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	//分数和用户是多对一关联关系，并且【分数是维护端】
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
		result = prime * result + ((scoreId == null) ? 0 : scoreId.hashCode());
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
		Score other = (Score) obj;
		if (scoreId == null) {
			if (other.scoreId != null)
				return false;
		} else if (!scoreId.equals(other.scoreId))
			return false;
		return true;
	}
}