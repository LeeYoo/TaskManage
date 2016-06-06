package com.paging.entity;
/*����ҳ��������ֵ���㡿�������㿪ʼ�������ͽ�������
 * ��ҳҳ�볬������ʽ���ã���ǰҳʼ�մ�������ҳ������м�λ��(�����ڰٶȷ�ҳ)��
 * �õ���ʼ������ҳ��ͽ���������ҳ�룬�ӿ�ʼ������������������ʾ�ȶ�Ч����
 * [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]...........
 * ��Ҫ�õ�������������ʼ����ֵ����������ֵ����ǰҳ������ֵ
 */
public class PageIndex {
	private long startindex; //��ʼ����
	private long endindex; //��������
	
	public PageIndex(long startindex, long endindex) {
		this.startindex = startindex;
		this.endindex = endindex;
	}
	public long getStartindex() {
		return startindex;
	}
	public void setStartindex(long startindex) {
		this.startindex = startindex;
	}
	public long getEndindex() {
		return endindex;
	}
	public void setEndindex(long endindex) {
		this.endindex = endindex;
	}
	 
	//������PageIndex����(��ʼ����ֵ�ͽ�������ֵ)����������ÿ��Ҫ��ʾ������ҳ��������ǰҳ�룬��ҳ������
	public static PageIndex getPageIndex(long viewpagecount, int currentPage, long totalpage){
			long startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);
			long endpage = currentPage+viewpagecount/2;
			if(startpage<1){
				startpage = 1;
				if(totalpage>=viewpagecount) endpage = viewpagecount;
				else endpage = totalpage;
			}
			if(endpage>totalpage){
				endpage = totalpage;
				if((endpage-viewpagecount)>0) startpage = endpage-viewpagecount+1;
				else startpage = 1;
			}
			return new PageIndex(startpage, endpage);		
	}
}