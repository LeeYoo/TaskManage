package com.file.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.file.service.FileService;
//���ļ�ҵ��ӿڵľ���ʵ���ࡿ
@Service
@Transactional	//���൱�������ע�⣬����Ҳ����
public class FileServiceBean extends DaoSupport implements FileService{}