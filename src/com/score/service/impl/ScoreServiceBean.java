package com.score.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.score.service.ScoreService;
//������ҵ��bean�ӿڵľ���ʵ���ࡿ
@Service
@Transactional	//���൱�������ע�⣬����Ҳ����
public class ScoreServiceBean extends DaoSupport implements ScoreService{
	
}
