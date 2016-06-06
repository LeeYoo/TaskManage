package com.base.Utils;
import java.io.IOException;
import java.util.Properties;
/**
 * ����ȡsiteurl�������ļ���key��
 * ����>���ܷ�����
 *	��Ϊÿ�ε���readUrl(���messageҳ���ȷ����ť)��������Ҫ��ȡ�����ļ���Ȼ����װ�ؽ��������Ի�ܺ����ܣ�
 *	����>�������
 *	���õ���״̬�Ϳ��Խ��������⣡ȷ�������½�ֻװ��һ�μ��ɣ�����ÿ�ε��õ�ʱ��ͻ�ֻ��ȡһ�������ļ��ˣ�
 */
public class SiteUrl {
	//������״̬��static
	private static Properties properties = new Properties();
	//����̬����飬ʹ�������ļ�ֻװ��һ�Ρ�
	static{
		try {
			//ͨ����װ������ȡ��Դ��Ϊ����������load����
			properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//����ȡ�����ļ�keyֵ�ķ�����
	public static String readUrl(String key){		
		return (String)properties.get(key);
	}
}