package in.ineuron.main;

import java.util.ArrayList;

public class Account {

	private String name;
	private String uuid;
	private AccountHolder holder;
	
	// the list of transactions for this account
	private ArrayList<BankTransaction> transactions;
	 
	
	public Account(String name,AccountHolder holder,CanaraBank theBank) {
		
		//set the account name an holder
		this.name=name;
		this.holder=holder;
		//get new Account UUID
		this.uuid=theBank.getNewAccountUUID();
		//init transactions
		this.transactions=new ArrayList<BankTransaction>();
		
		//add to holder and bank lists
	
		
	}


	public String getUUID() {
		
		return this.uuid;
	}


	public String getSummaryLine() {
		//get the accounts balance 
		double balance=this.getBalance();
		//format the summaryLine
		if(balance>=0) {
			return String.format("%s: $%.02f: %s",this.uuid,balance,this.name);
		}else {
			return String.format("%s: $(%.02f): %s",this.uuid,balance,this.name);
		}	
	}
	public  double getBalance() {
		double balance=0;
		for(BankTransaction bt:this.transactions) {
			balance+=bt.getAmount();
		}
		return balance;
	}


	public void printTransHistory() {
		
		System.out.printf("\nTransaction hstory for the account %\n",this.uuid);
		for(int t =this.transactions.size()-1;t>=0;t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
	System.out.println();
	}


	public void addTransaction(double amount, String memo) {
		BankTransaction newTrans=new BankTransaction(amount,memo,this);
		this.transactions.add(newTrans);
	}
}
