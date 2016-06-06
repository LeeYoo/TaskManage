package com.base.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//���������Զ������������ת��������
public class DateUtils {
	
	//���ַ���ת�������ڶ���
	public static Date stringToDate(String str){
		//������������ɡ�����ת�ַ������ַ���ת���ڡ���˫�����(��������Ŀ����ʽ)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//��������쳣��try catchһ��
			return sdf.parse(str);//�����Ϳ��Խ��ַ���ת��������
		} catch (ParseException e) {
			//ת��RuntimeException���ô����ˡ�
			throw new RuntimeException("���ڸ�ʽ����ȷ��");
		}
	}
}