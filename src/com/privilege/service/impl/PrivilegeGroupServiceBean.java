package com.privilege.service.impl;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.base.dao.DaoSupport;
import com.privilege.entity.PrivilegeGroup;
import com.privilege.service.PrivilegeGroupService;
import com.user.entity.User;
//��Ȩ����ҵ��ӿڵľ���ʵ���ࡿ
@Service
@Transactional		//���൱�������ע�⣬����Ҳ����
public class PrivilegeGroupServiceBean extends DaoSupport implements PrivilegeGroupService{

	//������Ȩ����ķ�������������Ȩ�����id�ǲ���UUID���ɵģ������ڱ���Ȩ�����ʱ����Ҫ������һ��id��������������дsave������
	@Override
	public void save(Object entity) {
		PrivilegeGroup group = (PrivilegeGroup)entity;	//ת������
		group.setGroupId(UUID.randomUUID().toString());	//��uuid��id
		super.save(entity);	//���� super.save(group);Ҳ��
	}

	//��ɾ��Ȩ���顿����Ȩ������Ϊ��ϵ��ά���ˣ�
	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for( Object id : entityids){
			//1.�ҵ���ǰ��Ȩ����
			PrivilegeGroup group = this.find(PrivilegeGroup.class, id);
			//2.�����֮��صĹ�ϵ���������û���Ȩ����Ĺ�ϵ
			for(User user : group.getUsers()){
				user.getGroups().remove(group);//��ɾ���м���еļ�¼��==������˹�����ϵ��
			}
			//3.ɾ��ʵ�壬����Ҫ�õ�ʵ����йܶ���(������ʵ�����ͣ�ʵ��id)
			em.remove(em.getReference(entityClass , id));
		}
	}
}
