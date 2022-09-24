/**
 * 
 */
package com.juxtapose.example.ch03;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author taegeon-park23(justerror23@gmail.com)
 */
public class CreditBillProcessor implements
		ItemProcessor<CreditBill, CreditBill> {

	public CreditBill process(CreditBill bill) throws Exception {
		System.out.println(bill.toString());
		return bill;
	}
}
