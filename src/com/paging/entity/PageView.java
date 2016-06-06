package com.paging.entity;
import java.util.List;
/*����װ��ҳ��Ŀ��Ϣ��
 * ע�⣺���ڶ��ҳ�涼��Ҫִ�з�ҳ�������ҷ�ҳ��ʽһ�£����ԣ���Ҫ����ҳ��Ϣ������װ��һ��jspҳ�棡
 * ���Ժ��jspҳ��������Ҫʹ�÷�ҳ���ܣ�ֻ��Ҫ����ҳҳ����ؽ������ɣ�
 * ��������>�ֽ�action��jspҳ����������ݣ������Ǵ�ŵ�request�������еķ�ҳ����(���Ʊ���һ��)��װ��һ���൱�У�
 */
public class PageView<T> {
	
	private List<T> records;				//����ҳ���ݡ�����>��Ҫ��ҳ��ʾ�ļ�¼�����(ѭ��������Ǹ������)��
	private PageIndex pageindex;		//��ҳ�뿪ʼ�����ͽ�������������ͨ��java���װ�ģ����������
	private long totalpage = 1;			//����ҳ����	�������������
	private int maxresult = 10;			//��ÿҳ��ʾ��¼����
	private int currentpage = 1;		//����ǰҳ�롿
	private long totalrecord;				//���ܼ�¼����
	private int pagecode = 5;			//��ÿ��Ҫ��ʾ������ҳ��������
	
	//�����㿪ʼ������ֵ��
	public int getFirstResult() {
		return (this.currentpage-1)*this.maxresult;
	}
	
	public PageView() {}	//�չ��캯����ʺζ�˽������󡪡��޷�ҳ��ʾ����
	//������(�����ṩ��������������Ϊ��ߵĲ�����Ҫ��������õ���)
	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	//�����ò�ѯ���������
	public void setQueryResult(QueryResult<T> qr){
		setRecords(qr.getResultlist());				//���ý����
		setTotalrecord(qr.getRecordtotal());		//�����ܼ�¼������set�����ڲ�������������
	}
	
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		//��������ҳ�������������á�������Ҫ�õ�ÿҳ��ʾ�ܼ�¼��maxresult���ܼ�¼��totalrecord����õ�
		setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);
	}
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	public PageIndex getPageindex() {
		return pageindex;
	}
	
	public long getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		//����������ֵ��,��ҳ��totalpage�Ѿ������������
		this.pageindex = PageIndex.getPageIndex(pagecode, currentpage, totalpage);
	}
	
	public int getMaxresult() {
		return maxresult;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}
	
	public int getPagecode() {
		return pagecode;
	}
	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}
}