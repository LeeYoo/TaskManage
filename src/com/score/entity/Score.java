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
//��������ʵ���ࡿ
@Entity
public class Score implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer scoreId;		//������ID��
	private int score;				//������ֵ��
	
	private Work work;				//����Ӧ�����񡿡����������ݺ������ǡ����һ��������ϵ
	private User user;				//���������ݹ������û������������һ��������ϵ
	
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
	
	//������������һ��һ������ϵ�����ҡ�������ά���ˡ�
	@OneToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE},mappedBy="score")
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	
	//�������û��Ƕ��һ������ϵ�����ҡ�������ά���ˡ�
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