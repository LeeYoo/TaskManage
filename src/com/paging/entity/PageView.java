package com.paging.entity;
import java.util.List;
/*【封装分页栏目信息】
 * 注意：由于多个页面都需要执行分页操作，且分页样式一致，所以：需要将分页信息单独封装到一个jsp页面！
 * 再以后的jsp页面中如有要使用分页功能，只需要将分页页面加载进来即可！
 * ————>现将action往jsp页面输出的数据，即就是存放到request作用域中的分页数据(名称保持一致)封装到一个类当中！
 */
public class PageView<T> {
	
	private List<T> records;				//【分页数据】——>需要分页显示的记录结果集(循环输出的那个结果集)！
	private PageIndex pageindex;		//【页码开始索引和结束索引】——通过java类封装的，计算出来的
	private long totalpage = 1;			//【总页数】	——计算出来的
	private int maxresult = 10;			//【每页显示记录数】
	private int currentpage = 1;		//【当前页码】
	private long totalrecord;				//【总记录数】
	private int pagecode = 5;			//【每次要显示出来的页码数量】
	
	//【计算开始的索引值】
	public int getFirstResult() {
		return (this.currentpage-1)*this.maxresult;
	}
	
	public PageView() {}	//空构造函数猪屎味了建立对象——无分页显示需求
	//构造器(必须提供这两个参数，因为后边的参数需要其来计算得到！)
	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	//【设置查询结果方法】
	public void setQueryResult(QueryResult<T> qr){
		setRecords(qr.getResultlist());				//设置结果集
		setTotalrecord(qr.getRecordtotal());		//设置总记录数——set方法内部存在连环调用
	}
	
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		//【计算总页数并给属性设置】——需要用到每页显示总记录数maxresult和总记录数totalrecord计算得到
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
		//【计算索引值】,总页数totalpage已经被计算出来了
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