package com.base.Utils;
import java.io.IOException;
import java.util.Properties;
/**
 * 【读取siteurl的配置文件的key】
 * ——>性能分析：
 *	因为每次调用readUrl(点击message页面的确定按钮)都会首先要读取配置文件，然后将其装载进来，所以会很耗性能！
 *	——>解决方案
 *	采用单例状态就可以解决这个问题！确保配置温江只装载一次即可！这样每次调用的时候就会只读取一次配置文件了！
 */
public class SiteUrl {
	//【单例状态】static
	private static Properties properties = new Properties();
	//【静态代码块，使得配置文件只装载一次】
	static{
		try {
			//通过类装载器获取资源作为数据流传给load方法
			properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//【读取属性文件key值的方法】
	public static String readUrl(String key){		
		return (String)properties.get(key);
	}
}