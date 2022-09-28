/**
 * 
 */
package com.juxtapose.example.ch11.multithread;

import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import com.juxtapose.example.ch11.data.DataShareBean;

/**
 * 
 * @author bruce.liu(mailto:jxta.liu@gmail.com)
 * 2013-11-17上午07:36:36
 */
public class CreditBillProcessor implements
		ItemProcessor<CreditBill, DestinationCreditBill> {
	JdbcTemplate jdbcTemplate = null;
	DataShareBean<CreditBill> dataShareBean;

	public DestinationCreditBill process(CreditBill bill) throws Exception {
		System.out.println(bill.toString());
		System.out.println("Job Process Thread name: " + Thread.currentThread().getName());
		DestinationCreditBill destCreditBill = new DestinationCreditBill();
		destCreditBill.setAccountID(bill.getAccountID());
		destCreditBill.setAddress(bill.getAddress());
		destCreditBill.setAmount(bill.getAmount());
		destCreditBill.setDate(bill.getDate());
		destCreditBill.setId(UUID.randomUUID().toString() +"::" + bill.getId());
		destCreditBill.setName(bill.getName());
		if(null != jdbcTemplate){

			if("03".equals(bill.getId())) {
				dataShareBean.putData(bill);
				throw new RuntimeException(bill.getId());
			}

			if("13".equals(bill.getId())) {
				dataShareBean.putData(bill);
				throw new RuntimeException(bill.getId());
			}

			initStep()
				.andThen(step01())
				.andThen(step02())
				.andThen(step03())
				.apply(bill);

		}

		return destCreditBill;
	}

	private String createUUID() {
		return UUID.randomUUID().toString().substring(0, 7);
	}

	private Function<CreditBill, CreditBill> initStep() throws SQLException {
		return (bill) -> {
			return bill;
		};
	}

	private Function<CreditBill, CreditBill> step01() throws SQLException {
		return (bill) -> {
			jdbcTemplate.update("INSERt INTO t_credit(`ID`,`ACCOUNTID`,`NAME`,`AMOUNT`,`DATE`,`ADDRESS`,`FLAG`) values (?,'ACCOUNTID','NAME',100.00,'DATE','ADDRESS','FLAG')", createUUID());
			return bill;
		};
	}

	private Function<CreditBill, CreditBill> step02() throws SQLException {
		return (bill) -> {
			jdbcTemplate.update("INSERt INTO t_credit(`ID`,`ACCOUNTID`,`NAME`,`AMOUNT`,`DATE`,`ADDRESS`,`FLAG`) values (?,'ACCOUNTID','NAME',100.00,'DATE','ADDRESS','FLAG')", createUUID());
			return bill;
		};
	}

	private Function<CreditBill, CreditBill> step03() throws SQLException {
		throw new SQLException();
		// return (bill) -> {
		// 	jdbcTemplate.update("update t_credit set flag=? where id=?", "true", bill.getId());
		// 	return bill;
		// };
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataShareBean<CreditBill> getDataShareBean() {
		return dataShareBean;
	}

	public void setDataShareBean(DataShareBean<CreditBill> dataShareBean) {
		this.dataShareBean = dataShareBean;
	}
}
