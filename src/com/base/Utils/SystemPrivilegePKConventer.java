package com.base.Utils;
import org.apache.commons.beanutils.Converter;
import com.privilege.entity.SystemPrivilegePK;
//【权限ID的类型转换器】__需要引入commons-beanutils-1.7.0.jar
public class SystemPrivilegePKConventer implements Converter{

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz,Object value) {	//clazz要转换的类
		if(value instanceof SystemPrivilegePK){
			SystemPrivilegePK id = (SystemPrivilegePK) value;
			if(clazz.equals(String.class)){
				return id.getModule()+","+id.getPrivilege();		//返回的样式（xxx,xxx）
			}else {
				return value;
			}
		}else {
			//代表要把接收到的请求参数需要进行转换（转换成action中属性对应的类型，比如SystemPrivilegePK类型）
			try {
				String param = (String) value;		//先转换成String类型，然后将其分割成数组，再将其转换成目标类型
				String[] arr = param.split(",");
				if(arr.length==2){
					return new SystemPrivilegePK(arr[0],arr[1]);	//权限模块，权限值
				}
			} catch (Exception e) {}
			return null;
		}
	}
}