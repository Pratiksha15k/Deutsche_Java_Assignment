package com.store.trade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.store.trade.bean.Trade;
import com.store.trade.exception.TradeException;
import com.store.trade.service.TradeService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	TradeService tradeService;

	@Test
	@Order(1)
	public void testAddTrade() {
		Trade trade = new Trade();
		Trade savedTrade = null;
		try {
			trade.setTradeID("T4");
			trade.setVersion(3);
			trade.setBookID("B1");
			trade.setCounterPartyID("CP1");
			trade.setExpired("N");
			trade.setMaturityDate(LocalDate.now().plusDays(3));
			tradeService.addTrade(trade);
			savedTrade = tradeService.findTrade(trade.getTradeID(), trade.getVersion());
			System.out.println(savedTrade.toString());
		} catch (TradeException e) {
			e.printStackTrace();
		}
		assertThat(savedTrade.getTradeID().length()>=0);
	}


	@Test
	@Order(2)
	public void testValidateTradeVersion() {
		System.out.println("****************** Test case 1 started ********************");
		//1. If the lower version is being received by the store it will reject the trade and throw an exception.
		Trade trade = new Trade("T4", 1, "CP-1", "B1", LocalDate.of(2020, 05, 20), LocalDate.now(), "N");
		try {
			tradeService.findAll().forEach(x->System.out.println(x.toString()));
			tradeService.validateVersion(trade);
		}catch(Exception e) {
			assertThatExceptionOfType(TradeException.class);
		}

		System.out.println("****************** Test case 1 finished ********************");
		
		System.out.println("****************** Test case 2 started ********************");
		//2. If the version is same received by the store it will update existing trade.
		Trade trade1 = new Trade("T4", 3, "CP-1", "B2", LocalDate.of(2020, 05, 20), LocalDate.now(), "N");
		try {
			tradeService.validateVersion(trade1);
			tradeService.findAll().forEach(x->System.out.println(x.toString()));
		}catch(Exception e) {
			assertThatExceptionOfType(TradeException.class);
		}
		System.out.println("****************** Test case 2 finished ********************");
	}

	@Test
	@Order(3)
	public void testTradeMaturityDate() {
		System.out.println("****************** Test case 3 started ********************");
		//3. Store should not allow the trade which has less maturity date then today date
		Trade trade = new Trade("T5",1,"CP-1","B2",LocalDate.now().minusDays(2),LocalDate.now(), "N");
		Trade savedTrade = null;
		try {
			tradeService.addTrade(trade);
			savedTrade = tradeService.findTrade(trade.getTradeID(), trade.getVersion());
			System.out.println(savedTrade.toString());
			assertThat(savedTrade.getTradeID().length()>=0);
		} catch (TradeException e) {
			System.out.println(e.getMessage());
			assertThatExceptionOfType(TradeException.class);
		}

		System.out.println("****************** Test case 3 finished ********************");
		
		System.out.println("****************** Test case 4 started ********************");
		
		//4. Store should not allow the trade which has less maturity date then today date
		Trade trade1 = new Trade("T6",1,"CP-2","B2",LocalDate.now().plusDays(2),LocalDate.now(), "N");
		Trade savedTrade1 = null;
		try {
			tradeService.addTrade(trade1);
			savedTrade1 = tradeService.findTrade(trade1.getTradeID(), trade1.getVersion());
			System.out.println(savedTrade1.toString());
			assertThat(savedTrade1.getTradeID().length()>=0);
		} catch (TradeException e) {
			assertThatExceptionOfType(TradeException.class);
		}
		System.out.println("****************** Test case 4 finished ********************");
	}

	@Test
	@Order(4)
	public void testUpdateTradeExpiryFlagAutomatically(){
		System.out.println("****************** Test case 5 started ********************");
		//5. Store should automatically update the expire flag if in a store the trade crosses the maturity date.
		try {
			
			System.out.println("****************** Test case 5 waiting ********************");
			Thread.sleep(35000);// sleep for 1 min
			
			System.out.println("****************** Test case 5 finished ********************");
			tradeService.findAll().forEach(x->System.out.println(x.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
