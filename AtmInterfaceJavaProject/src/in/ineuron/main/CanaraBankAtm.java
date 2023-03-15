package in.ineuron.main;

import java.util.Scanner;

public class CanaraBankAtm {

	public static void main(String[] args) {
		
		//Init Scanner 
		Scanner scan =new Scanner(System.in);
		
		CanaraBank theBank=new CanaraBank("Bank of Canara");
		
		//add a Account Holder
		AccountHolder aAccountHolder =theBank.addAccountHolder("vijay", "kumar", "1234");
		
		//add a checking account for a our user
		Account newAccount=new Account("checking",aAccountHolder,theBank);
		aAccountHolder.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		AccountHolder curAccountHolder;
		while(true) {
			
			curAccountHolder=CanaraBankAtm.mainMenuPrompt(theBank,scan);
			CanaraBankAtm.printAccountHolderMenu(curAccountHolder,scan);
		
		}
	}
	public static AccountHolder mainMenuPrompt(CanaraBank theBank,Scanner scan) {
		String AccountHolderID;
		String pin;
		AccountHolder authAccountHolder;
		do {
			System.out.printf("\n\nWelcome to %s\n\n",theBank.getName());
			System.out.println("Enter AccountHolder ID:");
			AccountHolderID=scan.nextLine();
			System.out.println("Enter pin:");
			pin=scan.nextLine();
			
			authAccountHolder =theBank.HolderLogin(AccountHolderID, pin);
			if (authAccountHolder ==null) {
				System.out.println("Incorrect user ID/PIN combination"+ "please try again");
			}
		}while(authAccountHolder==null);
		
		return authAccountHolder;
		
	}
	public static void printAccountHolderMenu(AccountHolder theAccountHolder,Scanner scan) {
		theAccountHolder.printAccountsSummary();
		
		int choice;
		
		do {
			System.out.printf("Welcome %s,what would like to do?",theAccountHolder.getFirstName());
			
			System.out.println("1) Showthe transaction histroy");
			System.out.println("2) WithDrawl");
			System.out.println("3) Deposit");
			System.out.println("4) Transfer");
			System.out.println("5) Quit");
			System.out.println();
			System.out.println("Enter choice: ");
			choice = scan.nextInt();
			
			if(choice<1||choice>5) {
				System.out.println("Invalid choice.. please choose 1 to 5");
			}
		}while(choice < 1||choice >5);
		
		//process the choice
		switch(choice ) {
		
		case 1:
			CanaraBankAtm.showTransactionHistory(theAccountHolder,scan);
			break;
		case 2:
			CanaraBankAtm.withdrawlFunds(theAccountHolder,scan);
			break;
		case 3:
			CanaraBankAtm.depositFunds(theAccountHolder,scan);
			break;
		case 4:
			CanaraBankAtm.transferFunds(theAccountHolder,scan);
			break;
		case 5:
			//gobble up rest of previous 
			scan.nextInt();
			break;
		}
		
		//re-display this menu the user wants to quit 
		if(choice!=5) {
			CanaraBankAtm.printAccountHolderMenu(theAccountHolder, scan);
			
		}
	}
	public static void showTransactionHistory(AccountHolder theAccountHolder,Scanner scan) {
		int theAcct;
		do {
			System.out.printf("Enter the number (1-%d) of the account\n" + " whose transactio histreoy you want to see:" ,theAccountHolder.numAccounts());
		theAcct=scan.nextInt()-1;
		if(theAcct <0||theAcct>=theAccountHolder.numAccounts()) {
			System.out.println("Invalid account . pleasse try again.");
		}
		}while(theAcct <0||theAcct>=theAccountHolder.numAccounts());
		theAccountHolder.printAcctTrnasHistory(theAcct);
	}


	public static void withdrawlFunds(AccountHolder theAccountHolder,Scanner scan) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		String memo;
		
		do {
			System.out.printf("Enter the number (1-%d)of the account\n"+"the withdraw from: ",theAccountHolder.numAccounts());
			fromAcct=scan.nextInt()-1;
			if(fromAcct<0||fromAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account . please try again.");
			}
		}while(fromAcct<0||fromAcct>=theAccountHolder.numAccounts());
		acctBal=theAccountHolder.getAcctBalance(fromAcct);
		do {
			System.out.printf("Enter the amount to withdraw (max $.02f): $",acctBal);
			amount =scan.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater than zero 0");
			}else if (amount>acctBal) {
				System.out.printf("Amount must be not greater than\n "+"balamce of $.02f.\n",acctBal);
			}
		}while(amount<0||amount>acctBal);
		//gobble up rest of previous 
		scan.nextInt();
		
		System.out.println("Enter a memo");
		memo=scan.nextLine();
		//DO THE WITHDRaw
		theAccountHolder.addAcctTransaction(fromAcct, -1*amount, memo);
		
	}
	public static void depositFunds(AccountHolder theAccountHolder,Scanner scan) {
		
		int toAcct;
		double amount;
		double acctBal;
		String memo;
		
		do {
			System.out.printf("Enter the number (1-%d)of the account\n"+"the to despoit in: ",theAccountHolder.numAccounts());
			toAcct=scan.nextInt()-1;
			if(toAcct<0||toAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account . please try again.");
			}
		}while(toAcct<0||toAcct>=theAccountHolder.numAccounts());
		acctBal=theAccountHolder.getAcctBalance(toAcct);
		do {
			System.out.printf("Enter the amount to transer (max $.02f): $",acctBal);
			amount =scan.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater than zero 0");
			}
		}while(amount<0);
		//gobble up rest of prevoius 
		scan.nextInt();
		
		System.out.println("Enter a memo");
		memo=scan.nextLine();
		//DO THE WITHDRaw
		theAccountHolder.addAcctTransaction(toAcct, amount, memo);
		
		
		
	}
	public static void transferFunds(AccountHolder theAccountHolder,Scanner scan) {
	
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		do {
			System.out.printf("Enter the number (1-%d)of the account\n"+"the transfer from: ",theAccountHolder.numAccounts());
			fromAcct=scan.nextInt()-1;
			if(fromAcct<0||fromAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account . please try again.");
			}
		}while(fromAcct<0||fromAcct>=theAccountHolder.numAccounts());
		
		acctBal=theAccountHolder.getAcctBalance(fromAcct);
		//get the account to transfer

		do {
			System.out.println("Enter the number (1-%d)of the account\n"+"the transfer to: ");
			toAcct=scan.nextInt()-1;
			if(toAcct<0||toAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account . please try again.");
			}
		}while(toAcct<0||toAcct>=theAccountHolder.numAccounts());
		
		do {
			System.out.printf("Enter the amount to transer (max $.02f): $",acctBal);
			amount =scan.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater than zero 0");
			}else if (amount>acctBal) {
				System.out.printf("Amount must be not greater than\n "+"balamce of $.02f.\n",acctBal);
			}
		}while(amount<0||amount>acctBal);
		//finally ,do the transfer
		theAccountHolder.addAcctTransaction(fromAcct,-1*amount,String.format("transfer to acount %s",theAccountHolder.getAcctUUID(toAcct)));
		theAccountHolder.addAcctTransaction(toAcct,amount,String.format("transfer to acount %s",theAccountHolder.getAcctUUID(fromAcct)));
	}
}
