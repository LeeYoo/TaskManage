package com.privilege.service.impl;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.service.PrivilegeGroupService;
import com.user.entity.User;
//【权限组业务接口的具体实现类】
@Service
@Transactional		//父类当中有这个注解，不加也可以
public class PrivilegeGroupServiceBean extends DaoSupport implements PrivilegeGroupService{

	//【保存权限组的方法】————权限组的id是采用UUID生成的，所以在保存权限组的时候，需要给它赋一个id————即，重写save方法。
	@Override
	public void save(Object entity) {
		PrivilegeGroup group = (PrivilegeGroup)entity;	//转换类型
		group.setGroupId(UUID.randomUUID().toString());	//赋uuid给id
		super.save(entity);	//保存 super.save(group);也行
	}

	//【删除权限组】——权限组作为关系被维护端，
	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for( Object id : entityids){
			//1.找到当前的权限组
			PrivilegeGroup group = this.find(PrivilegeGroup.class, id);
			//2.解除与之相关的关系，即就是用户和权限组的关系
			for(User user : group.getUsers()){
				user.getGroups().remove(group);//【删掉中间表中的记录】==【解除了关联关系】
			}
			//3.删除实体，首先要得到实体的托管对象(参数：实体类型，实体id)
			em.remove(em.getReference(entityClass , id));
		}
	}
}
