package co.gov.sdp.spdd.web.util;

import org.springframework.data.domain.Page;

public class DataTableResultInfo {
	
    private Page<?> data;
    private int draw;//the NO.of requests
    private int length;
    private long recordsTotal;
    private long recordsFiltered;
	/**
	 * @return the data
	 */
	public Page<?> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Page<?> data) {
		this.data = data;
	}
	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * @return the recordsTotal
	 */
	public long getRecordsTotal() {
		return recordsTotal;
	}
	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	/**
	 * @return the recordsFiltered
	 */
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
    
    
}