package com.sunjinxu.tools;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 对Page<E>结果进行包装
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageInfo<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	// 当前页
	public int pageNum;
	// 每页的数量
	public int pageSize;
	// 当前页的数量
	public int size;

	// 由于startRow和endRow不常用，这里说个具体的用法
	// 可以在页面中"显示startRow到endRow 共size条数据"

	// 当前页面第一个元素在数据库中的行号
	public int startRow;
	// 当前页面最后一个元素在数据库中的行号
	public int endRow;
	// 总记录数
	public long total;
	// 总页数
	public int pages;
	// 结果集
	public List<T> list;

	// 前一页
	public int prePage;
	// 下一页
	public int nextPage;

	// 是否为第一页
	public boolean isFirstPage = false;
	// 是否为最后一页
	public boolean isLastPage = false;
	// 是否有前一页
	public boolean hasPreviousPage = false;
	// 是否有下一页
	public boolean hasNextPage = false;
	// 导航页码数
	public int navigatePages;
	// 所有导航页号
	public int[] navigatepageNums;
	// 导航条上的第一页
	public int navigateFirstPage;
	// 导航条上的最后一页
	public int navigateLastPage;

	public PageInfo() {
	}

	/**
	 * 包装Page对象
	 *
	 * @param list
	 */
	public PageInfo(List<T> list,int total,int pageNum,int pageSize) {
		this(list, 5, total,pageNum,pageSize);
	}

	/**
	 * 包装Page对象
	 *
	 * @param list
	 *            page结果
	 * @param navigatePages
	 *            页码数量
	 */
	public PageInfo(List<T> list ,int navigatePages, int total,int pageNum,int pageSize) {
		if (list instanceof Page) {
			Page page = (Page) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			
			this.pages = page.getPages();
			this.list = page;
			this.size = page.size();
			this.total = page.getTotal();
			// 由于结果是>startRow的，所以实际的需要+1
			if (this.size == 0) {
				this.startRow = 0;
				this.endRow = 0;
			} else {
				this.startRow = page.getStartRow() + 1;
				// 计算实际的endRow（最后一页的时候特殊）
				this.endRow = this.startRow - 1 + this.size;
			}
		} else if (list instanceof Collection) {
			this.pageNum = pageNum;
			this.pageSize = pageSize; // 每页的数量
			this.total = total; 		// 总记录数
			this.list = list; 			// 结果集
			this.size = list.size(); 	// 结果集大小
			this.startRow = 0; // 当前页面第一个元素在数据库中的行号
			this.endRow = list.size() > 0 ? (pageSize - 1+startRow) : startRow;// 当前页面最后一个元素在数据库中的行号
			calcPages();
		}
		if (list instanceof Collection) {
			this.navigatePages = navigatePages;
			// 计算导航页
			calcNavigatepageNums();
			// 计算前后页，第一页，最后一页
			calcPage();
			// 判断页面边界
			judgePageBoudary();
		}
	}

	/**
	 * 计算总页数
	 */
	public void calcPages() {
		if (total >= pageSize) {
			if (total % pageSize == 0) {
				pages = (int) Math.ceil(total / pageSize);
			} else {
				pages = (int) Math.ceil(total / pageSize) + 1;
			}
		} else {
			pages = this.pageSize > 0 ? 1 : 0;
		}
	}

	/**
	 * 计算导航页
	 */
	private void calcNavigatepageNums() {
		// 当总页数小于或等于导航页码数时
		if (pages <= navigatePages) {
			navigatepageNums = new int[pages];
			for (int i = 0; i < pages; i++) {
				navigatepageNums[i] = i + 1;
			}
		} else { // 当总页数大于导航页码数时
			navigatepageNums = new int[navigatePages];
			int startNum = pageNum - navigatePages / 2;
			int endNum = pageNum + navigatePages / 2;
			if (startNum < 1) {
				startNum = 1;
				// (最前navigatePages页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			} else if (endNum > pages) {
				endNum = pages;
				// 最后navigatePages页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatepageNums[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 计算前后页，第一页，最后一页
	 */
	private void calcPage() {
		if (navigatepageNums != null && navigatepageNums.length > 0) {
			navigateFirstPage = navigatepageNums[0];
			navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
			if (pageNum > 1) {
				prePage = pageNum - 1;
			}
			if (pageNum < pages) {
				nextPage = pageNum + 1;
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void judgePageBoudary() {
		isFirstPage = pageNum == 1;
		isLastPage = pageNum == pages || pages == 0;
		;
		hasPreviousPage = pageNum > 1;
		hasNextPage = pageNum < pages;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int setPageSize(int pageSize) {
		return this.pageSize = pageSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Deprecated
	// firstPage就是1, 此函数获取的是导航条上的第一页, 容易产生歧义
	public int getFirstPage() {
		return navigateFirstPage;
	}

	@Deprecated
	public void setFirstPage(int firstPage) {
		this.navigateFirstPage = firstPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Deprecated
	// 请用getPages()来获取最后一页, 此函数获取的是导航条上的最后一页, 容易产生歧义.
	public int getLastPage() {
		return navigateLastPage;
	}

	@Deprecated
	public void setLastPage(int lastPage) {
		this.navigateLastPage = lastPage;
	}

	public boolean isIsFirstPage() {
		return isFirstPage;
	}

	public void setIsFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public int getNavigateFirstPage() {
		return navigateFirstPage;
	}

	public int getNavigateLastPage() {
		return navigateLastPage;
	}

	public void setNavigateFirstPage(int navigateFirstPage) {
		this.navigateFirstPage = navigateFirstPage;
	}

	public void setNavigateLastPage(int navigateLastPage) {
		this.navigateLastPage = navigateLastPage;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PageInfo{");
		sb.append("pageNum=").append(pageNum);
		sb.append(", pageSize=").append(pageSize);
		sb.append(", size=").append(size);
		sb.append(", startRow=").append(startRow);
		sb.append(", endRow=").append(endRow);
		sb.append(", total=").append(total);
		sb.append(", pages=").append(pages);
		sb.append(", list=").append(list);
		sb.append(", prePage=").append(prePage);
		sb.append(", nextPage=").append(nextPage);
		sb.append(", isFirstPage=").append(isFirstPage);
		sb.append(", isLastPage=").append(isLastPage);
		sb.append(", hasPreviousPage=").append(hasPreviousPage);
		sb.append(", hasNextPage=").append(hasNextPage);
		sb.append(", navigatePages=").append(navigatePages);
		sb.append(", navigateFirstPage=").append(navigateFirstPage);
		sb.append(", navigateLastPage=").append(navigateLastPage);
		sb.append(", navigatepageNums=");
		if (navigatepageNums == null)
			sb.append("null");
		else {
			sb.append('[');
			for (int i = 0; i < navigatepageNums.length; ++i)
				sb.append(i == 0 ? "" : ", ").append(navigatepageNums[i]);
			sb.append(']');
		}
		sb.append('}');
		return sb.toString();
	}
}