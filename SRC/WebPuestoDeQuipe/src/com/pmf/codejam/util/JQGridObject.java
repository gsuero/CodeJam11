package com.pmf.codejam.util;

import java.util.List;

public class JQGridObject {

	private int total; // total pages
	private List rows; // objeto con los itemes
	private int page; // pagina actual
	private Long records; //total de rows
	
	public int getTotal() {
		return total;
	}
	/**
	 * Must set the Records member first...
	 * @param rows
	 */
	public void setHowManyRowsPerView(int rows) {
		this.total = (int) Math.ceil((new Float(getRecords())/new Float(rows)));
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Long getRecords() {
		return records;
	}
	public void setRecords(Long records) {
		this.records = records;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
