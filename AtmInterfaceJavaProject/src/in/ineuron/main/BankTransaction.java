package in.ineuron.main;

import java.util.Date;

public class BankTransaction {

	private double amount;
	private Date timestamp;
	private String memo;
	private Account inAccount;

	
	public BankTransaction( double amount,Account inAccount) {
		this.inAccount=inAccount;
		this.amount=amount;
		this.timestamp=new Date();
		this.memo="";
		
	}
	public BankTransaction( double amount,String memo,Account inAccount) {
		//two arg constructor
		
		this(amount,inAccount);
		this.memo=memo;
	}
	public double getAmount() {
		
		return this.amount;
	}
	public String getSummaryLine() {
		if(this.amount>=0) {
			return String.format("%s: $%.02f: %s",this.timestamp.toString(),this.amount,this.memo);
		
		}else {
			return String.format("%s: $%.02f: %s",this.timestamp.toString(),-this.amount,this.memo);
		}
	}

}
