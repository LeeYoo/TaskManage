package com.paging.entity;
import java.util.List;
/**��������>����ѯ�����
 *	��������¼�����ܼ�¼������(��ҳ���ֵ�)�� 
 * 	@param <T>  ����ʵ������
 */
public class QueryResult<T> {
	private List<T> resultlist;	//����¼����Ԫ�ص����������ϵķ�����ָ��
	private long recordtotal;		//���ܼ�¼����
	
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getRecordtotal() {
		return recordtotal;
	}
	public void setRecordtotal(long recordtotal) {
		this.recordtotal = recordtotal;
	}
	
}