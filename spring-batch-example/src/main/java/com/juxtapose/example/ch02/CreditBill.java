/**
 * 
 */
package com.juxtapose.example.ch02;

/**
 * 신용카드 청구서 모형.<br>
 * @author taegepn-park23(justerror23@gmail.com)
 */
public class CreditBill {
	private String accountID = "";	/** 카드계좌 ID */
	private String name = "";		/** 카드소지자 이름 */
	private double amount = 0;		/** 카드소지 금액 */
	private String date;			/** 소비일자，형식 YYYY-MM-DD HH:MM:SS*/
	private String address;			/** 소비장소 **/
	
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
