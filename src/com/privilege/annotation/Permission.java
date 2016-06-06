package com.privilege.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*��Ȩ�޿��ơ�
 * annotationע����һ������ĳ־÷�Χ����������Χ
 * ����@Retention�������ע�Ᵽ���Ľ׶�:
 * 1.SOURCE(ֻ������Դ�����У�������class��ע��ͻᱻ����)
 * 2.CLASS(ֻ�����ڱ�������class�ļ���)
 * 2.RUNTIME(������Դ����ͱ�����class�ļ��в������ŷ������ڵ���ļ��ض�����)
 * ����@Target�������ע���ע�ķ�Χ��ElementType���ܵ���һ������
 * 1.METHOD(��ע�ڷ�����)
 * 2.FIELD(��ע���ֶ���)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

	String module();		//ģ��
	String privilege();	//Ȩ��ֵ
}
