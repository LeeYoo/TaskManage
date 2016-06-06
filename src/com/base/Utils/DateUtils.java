package com.base.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//这是我们自定义的日期类型转化工具类
public class DateUtils {
	
	//【字符串转化成日期对象】
	public static Date stringToDate(String str){
		//这个对象可以完成【日期转字符串；字符串转日期】的双向过程(参数代表目标样式)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//这儿会有异常，try catch一下
			return sdf.parse(str);//这样就可以将字符串转成日期了
		} catch (ParseException e) {
			//转成RuntimeException不用处理了。
			throw new RuntimeException("日期格式不正确！");
		}
	}
}