package com.user.entity;
/**
 * ����ݽ�ɫ�ࡿ
 * �ⲿ����һ�����󷽷����������ڲ�����һ���ڲ��࣬����ʵ�ָó��󷽷�
 * �Ӷ��ﵽ��ȡ���Ƶ����á���ֱ�ۡ�
 */
public enum Identity {
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