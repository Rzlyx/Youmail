package org.suNing.utli;

public class PageBean {
	
	private int pageNum =1; //当前页
	
	private int pageSize = 3; // 每页显示的数量
	
	private int totalCount ; // 中数量条数
	
	private int totalPages;  // 总页数
	
	private int prevPage; //上一页
	
	private int nextPage; // 下一页

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 过去总页数
	 * @return
	 */
	public int getTotalPages() {
		// 通过 总条数-1再除以每页显示的条数+1就得除了总页数
		this.totalPages=(this.totalCount -1)/this.pageSize+1;
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * 上一页 
	 * @return
	 */
	public int getPrevPage() {
		this.prevPage = this.pageNum>1?this.pageNum-1:1;
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	/**
	 * 过去下一页
	 * @return
	 */
	public int getNextPage() {
		this.nextPage = this.pageNum < this.getTotalPages()?this.pageNum+1:this.getTotalPages();
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	

}
