package com.lxisoft.domain;

/**
 * @author kpmuh
 *
 */
public class CustError {
	private String status;
	private String message;
	
	public CustError() {
	}
	
	public CustError(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error occured....  [status-" + status + " & message-" + message + "]";
	}

	
}
