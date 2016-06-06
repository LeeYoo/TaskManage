package com.privilege.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*【权限控制】
 * annotation注解有一个保存的持久范围——三个范围
 * ——@Retention代表这个注解保留的阶段:
 * 1.SOURCE(只保留在源代码中，编译后的class中注解就会被丢掉)
 * 2.CLASS(只保留在编译过后的class文件中)
 * 2.RUNTIME(保留在源代码和编译后的class文件中并且随着方法所在的类的加载而存在)
 * ——@Target代表这个注解标注的范围，ElementType接受的是一个数组
 * 1.METHOD(标注在方法上)
 * 2.FIELD(标注在字段上)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

	String module();		//模块
	String privilege();	//权鲜值
}
