package in.ineuron.main;

import java.util.ArrayList;
import java.util.Random;

public class CanaraBank 
{
	
	private String name;
	private ArrayList<AccountHolder> accountholders;
	private ArrayList<Account> accounts;
	
	public CanaraBank(String name) {
		this.name=name;
		this.accountholders=new ArrayList<AccountHolder>();
		this.accounts=new ArrayList<Account>();
	}
	
	public String getNewUserUUID() {
		//inits
		String uuid;
		Random rng =new Random();
		int len=10;
		boolean nonUnique;
		//continung looping until we get a unique ID
		do {
		//generate the number 
			uuid="";
			for(int c =0;c<len;c++) {
			uuid+= ((Integer)rng.nextInt(10)).toString();	
			}
			//check to make sure its unique
			nonUnique=false;
			for( Account a:this.accounts) {
				if (uuid.compareTo(a.getUUID())==0) {
					nonUnique =true;
					break;
				}
			}
			
			
		} while(nonUnique);
		
		
		return uuid;
	}
	
	
	public String getNewAccountUUID() {
		String uuid;
		Random rng =new Random();
		int len=6;
		boolean nonUnique;
		//continung looping until we get a unique ID
		do {
		//generate the number 
			uuid="";
			for(int c =0;c<len;c++) {
			uuid+= ((Integer)rng.nextInt(10)).toString();	
			}
			//check to make sure its unique
			nonUnique=false;
			for( AccountHolder ac:this.accountholders) {
				if (uuid.compareTo(ac.getUUID())==0) {
					nonUnique =true;
					break;
				}
			}
			
			
		} while(nonUnique);
		
		
		return uuid;
		
	}
	
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	} 
	public AccountHolder addAccountHolder(String firstName,String lastName,String pin) {
	
		AccountHolder newAccountHolder= new AccountHolder(firstName,lastName,pin,this);
		this.accountholders.add(newAccountHolder);
		
		Account newAccount =new Account("Savings",newAccountHolder,this);
		newAccountHolder.addAccount(newAccount);
		this.addAccount(newAccount);
		return newAccountHolder;
		
	}
	public AccountHolder HolderLogin(String AccountHolderID,String pin) {
		for (AccountHolder ac:this.accountholders) {
			if(ac.getUUID().compareTo(AccountHolderID)==0 && ac.validatePin(pin)) {
				return ac;
			}
			
		}
		return null;
			
	}

	public String getName() {
		
		return this.name;
	}

	

}
