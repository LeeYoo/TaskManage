package com.base.Utils;
import org.apache.commons.beanutils.Converter;
import com.privilege.entity.SystemPrivilegePK;
//��Ȩ��ID������ת������__��Ҫ����commons-beanutils-1.7.0.jar
public class SystemPrivilegePKConventer implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz,Object value) {	//clazzҪת������
		if(value instanceof SystemPrivilegePK){
			SystemPrivilegePK id = (SystemPrivilegePK) value;
			if(clazz.equals(String.class)){
				return id.getModule()+","+id.getPrivilege();		//���ص���ʽ��xxx,xxx��
			}else {
				return value;
			}
		}else {
			//����Ҫ�ѽ��յ������������Ҫ����ת����ת����action�����Զ�Ӧ�����ͣ�����SystemPrivilegePK���ͣ�
			try {
				String param = (String) value;		//��ת����String���ͣ�Ȼ����ָ�����飬�ٽ���ת����Ŀ������
				String[] arr = param.split(",");
				if(arr.length==2){
					return new SystemPrivilegePK(arr[0],arr[1]);	//Ȩ��ģ�飬Ȩ��ֵ
				}
			} catch (Exception e) {}
			return null;
		}
	}
}