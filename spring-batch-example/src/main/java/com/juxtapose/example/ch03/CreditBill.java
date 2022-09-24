/**
 * 
 */
package com.juxtapose.example.ch03;

/**
 * 신용카드 대 청구서 모형 .<br>
 * @author taegepn-park23(justerror23@gmail.com)
 */
public class CreditBill {
	private String accountID = "";	/** 카드 계좌 ID */
	private String name = "";		/** 카드를 소지한 사람의 이름 */
	private double amount = 0;		/** 소비액 */
	private String date;			/** 소비일 ，형식YYYY-MM-DD HH:MM:SS*/
	private String address;			/** 소비처 **/
	
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("accountID=" + getAccountID() + ";name=" + getName() + ";amount="
				+ getAmount() + ";date=" + getDate() + ";address=" + getAddress());
		return sb.toString();
	}
}
