package com.ironchain.common.exception;

import com.ironchain.common.domain.R;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private short sc = R.SC_ERROR;
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message){
		super(message);
	}
	public ServiceException(short sc, Exception e) {
		super(e);
		this.sc = sc;
	}
	
	public ServiceException(short sc, String message){
		super(message);
		this.sc = sc;
	}
	
	public short getSc() {
		return sc;
	}
}
