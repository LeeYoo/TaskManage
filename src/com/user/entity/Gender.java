package com.user.entity;
/**
 * 【性别类】
 * 外部定义一个抽象方法，在属性内部定义一个内部类，必须实现该抽象方法
 * 从而达到另取名称的作用。更直观。
 */
public enum Gender {
	MAN{
		public String getName(){
			return "男";
		}
	},
	WOMEN{
		public String getName(){
			return "女";
		}
	};
	
	//定义抽象方法，内部类必须对该方法进行实现
	public abstract String getName();
}