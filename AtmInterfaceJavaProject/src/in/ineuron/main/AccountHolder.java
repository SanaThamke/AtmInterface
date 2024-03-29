package in.ineuron.main;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class AccountHolder {

	// uuid = unique universal id
	private String firstName;
	private String lastName;
	private String uuid;
	
	private byte pinHash[];
	//the MD5 hash of the user's pin number
	private ArrayList<Account> accounts;
	
	public AccountHolder(String firstName,String lastName,String pin,CanaraBank theBank) {
		
		this.firstName=firstName;
		this.lastName=lastName;
		//to store MD5 hash ,rather than original value for security purpose
		
		try {
			MessageDigest md =MessageDigest.getInstance("MD5");
			this.pinHash=md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, cuaght nosuchalgorithmexception");
			e.printStackTrace();
			System.exit(1);
		}
		//get a new ,unique universal ID for the user
		this.uuid=theBank.getNewUserUUID();
		//empty list of accounts 
		this.accounts=new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created.\n",lastName,firstName,this.uuid);
		
		
		
	}
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	public String getUUID() {
		return this.uuid;
		
	}
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
		return MessageDigest.isEqual(md.digest(aPin.getBytes()),this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, cuaght nosuchalgorithmexception");
			e.printStackTrace();
			System.exit(1);
		}
		return false;
		
	}
	public String getFirstName() {
	
		return this.firstName;
		 
	}
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s' s accounts summary\n",this.firstName);
	for(int a=0;a<this.accounts.size();a++) {
		System.out.printf("%d) %s\n",a+1,this.accounts.get(a).getSummaryLine());	
	}
	System.out.println();
	}
	public int numAccounts() {
		
		return this.accounts.size();
	}
	public void printAcctTrnasHistory(int accIdx) {
		this.accounts.get(accIdx).printTransHistory();
		
	}
	
	
	public double getAcctBalance(int acctIdx) {
		
		return this.accounts.get(acctIdx).getBalance();
	}
	public String getAcctUUID(int acctIdx) {
		
		return this.accounts.get(acctIdx).getUUID();
	}
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		
		this.accounts.get(acctIdx).addTransaction(amount,memo);
	}
	
}
	
	
	

