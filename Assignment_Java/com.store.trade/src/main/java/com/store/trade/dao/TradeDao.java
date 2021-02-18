package com.store.trade.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.store.trade.bean.Trade;
import com.store.trade.exception.TradeException;

public interface TradeDao {

	public  static Map<String,Trade> tradeMap =new ConcurrentHashMap<>();
	
	public void addTrade(Trade trade) throws TradeException;
	
	public void updateTrade(Trade oldtrade, Trade newtrade) throws TradeException;

	public Trade findTrade(String TradeId, int version) throws TradeException;
	
	public List<Trade> findAll() throws TradeException;
}
