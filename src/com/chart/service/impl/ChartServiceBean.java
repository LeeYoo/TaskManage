package com.chart.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.chart.service.ChartService;
//������ҵ��bean�ӿڵľ���ʵ���ࡿ
@Service
@Transactional	//���൱�������ע�⣬����Ҳ����
public class ChartServiceBean extends DaoSupport implements ChartService{}
