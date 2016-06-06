package com.chart.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.chart.service.ChartService;
//【报表业务bean接口的具体实现类】
@Service
@Transactional	//父类当中有这个注解，不加也可以
public class ChartServiceBean extends DaoSupport implements ChartService{}
