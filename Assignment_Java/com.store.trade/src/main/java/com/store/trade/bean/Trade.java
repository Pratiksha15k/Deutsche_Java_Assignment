package com.store.trade.bean;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Trades")
public class Trade {
	
	@Id
	String TradeID;

	int Version;
	
	String CounterPartyID;
	
	String BookID;
	
	LocalDate MaturityDate;
	
	LocalDate CreatedDate;
	
	String IsExpired;
	
	public Trade() {
		super();
	}

	public Trade(String TradeID, int Version, String CounterPartyID, String BookID, LocalDate MaturityDate,
			LocalDate CreatedDate, String IsExpired) {
		super();
		this.TradeID = TradeID;
		this.Version = Version;
		this.CounterPartyID = CounterPartyID;
		this.BookID = BookID;
		this.MaturityDate = MaturityDate;
		this.CreatedDate = CreatedDate;
		this.IsExpired = IsExpired;
	}
	
	public String getTradeID() {
		return TradeID;
	}

	public void setTradeID(String tradeID) {
		TradeID = tradeID;
	}

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		this.Version = version;
	}

	public String getCounterPartyID() {
		return CounterPartyID;
	}

	public void setCounterPartyID(String counterPartyID) {
		this.CounterPartyID = counterPartyID;
	}

	public String getBookID() {
		return BookID;
	}

	public void setBookID(String bookID) {
		this.BookID = bookID;
	}

	public LocalDate getMaturityDate() {
		return MaturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.MaturityDate = maturityDate;
	}

	public LocalDate getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(LocalDate localDate) {
		this.CreatedDate = localDate;
	}

	public String getExpired() {
		return IsExpired;
	}

	public void setExpired(String isExpired) {
		this.IsExpired = isExpired;
	}

	@Override
	public String toString() {
		return "Trade [TradeID=" + TradeID + ", Version=" + Version + ", CounterPartyID=" + CounterPartyID + ", BookID="
				+ BookID + ", MaturityDate=" + MaturityDate + ", CreatedDate=" + CreatedDate + ", IsExpired="
				+ IsExpired + "]";
	}
	
}
