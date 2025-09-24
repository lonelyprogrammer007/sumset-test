package co.gov.sdp.spdd.web.util;

import java.util.List;

public class DataTable<T> {

    private int draw;
    private int start;
    private long recordsTotal;
    private long recordsFiltered;
    private long totalPonderacion;
    private long numConsecutivo;
    private List<T> data;
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
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
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
	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	public Long getTotalPonderacion() {
		return totalPonderacion;
	}
	public void setTotalPonderacion(Long totalPonderacion) {
		this.totalPonderacion = totalPonderacion;
	}
	public long getNumConsecutivo() {
		return numConsecutivo;
	}
	public void setNumConsecutivo(long numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}
	public void setTotalPonderacion(long totalPonderacion) {
		this.totalPonderacion = totalPonderacion;
	}


    // setter and getter ...

}