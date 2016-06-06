package com.paging.entity;
/*【分页所需索引值计算】——计算开始索引，和结束索引
 * 分页页码超链接样式设置：当前页始终处于所列页码的最中间位置(类似于百度分页)：
 * 得到开始索引的页码和结束索引的页码，从开始迭代到结束，即可显示既定效果。
 * [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]...........
 * 需要得到三个参数：开始索引值，结束索引值，当前页码索引值
 */
public class PageIndex {
	private long startindex; //开始索引
	private long endindex; //结束索引
	
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
	 
	//【返回PageIndex对象(开始索引值和结束索引值)】（参数：每次要显示出来的页码数，当前页码，总页码数）
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