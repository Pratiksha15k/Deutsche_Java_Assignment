package com.store.trade.exception;

public class TradeException extends Exception {

	private static final long serialVersionUID = 1L;

	public TradeException(String message) {
		super(message);
	}

	public TradeException(Throwable throwable) {
		super(throwable);
	}

	public TradeException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
