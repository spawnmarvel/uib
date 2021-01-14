package com.task1.tcp;

import java.util.Date;
/**
 * A class which is a log object that has two fields, an ip-field and a date field
 * @author dro068 and jkl070
 *
 */

public class LogObject {

	private String ip;
	private Date date;
    /**
     *The constructor
     * @param ip
     * @param date
     */
	public LogObject(String ip, Date date) {
		this.ip = ip;
		this.date = date;
	}
	/**
	 * get ip
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * set ip
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * get date
	 * @return
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * set date
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}


}
