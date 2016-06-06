package com.file.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.file.service.FileService;
//【文件业务接口的具体实现类】
@Service
@Transactional	//父类当中有这个注解，不加也可以
public class FileServiceBean extends DaoSupport implements FileService{}