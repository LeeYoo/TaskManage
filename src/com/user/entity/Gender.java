package com.user.entity;
/**
 * ���Ա��ࡿ
 * �ⲿ����һ�����󷽷����������ڲ�����һ���ڲ��࣬����ʵ�ָó��󷽷�
 * �Ӷ��ﵽ��ȡ���Ƶ����á���ֱ�ۡ�
 */
public enum Gender {
	MAN{
		public String getName(){
			return "��";
		}
	},
	WOMEN{
		public String getName(){
			return "Ů";
		}
	};
	
	//������󷽷����ڲ������Ը÷�������ʵ��
	public abstract String getName();
}