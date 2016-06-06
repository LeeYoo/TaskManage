package com.paging.entity;
import java.util.List;
/**————>【查询结果】
 *	【描述记录集和总记录数的类(分页部分的)】 
 * 	@param <T>  代表实体类型
 */
public class QueryResult<T> {
	private List<T> resultlist;	//【记录集】元素的类型由类上的泛型所指定
	private long recordtotal;		//【总记录数】
	
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