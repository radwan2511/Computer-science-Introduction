/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
	
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	// END OF Given code -----------------------------------
	
	// Complete the methods from here on

	public boolean add(BankAccount newAccount) {
		// task 6a
		boolean valid = lookUp(newAccount.getAccountNumber()) == null & lookUp(newAccount.getName()) == null;
		if(valid)
		{
			namesTree.insert(newAccount);
			accountNumbersTree.insert(newAccount);
		}
		
		return valid;
	}

	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:
		boolean deleted = toRemove != null;
		// task 6b
		if(deleted) {
			namesTree.remove(toRemove);
			accountNumbersTree.remove(toRemove);
		}
		return deleted;
	}
	
	public boolean delete(int accountNumber){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:
		boolean deleted = toRemove != null;
		// task 6b
		if(deleted) {
			namesTree.remove(toRemove);
			accountNumbersTree.remove(toRemove);
		}
		return deleted;
	}

	public boolean depositMoney(int amount, int accountNumber){
		// task 6d
		BankAccount toDiposit = lookUp(accountNumber);
		boolean deposited = toDiposit != null;
		if(deposited) {
			deposited = toDiposit.depositMoney(amount);
		}
		
		return deposited;
	}

	public boolean withdrawMoney(int amount, int accountNumber){
		// task 6e
		BankAccount toWithdraw = lookUp(accountNumber);
		boolean withdrawed = toWithdraw != null;
		if(withdrawed) {
			withdrawed = toWithdraw.withdrawMoney(amount);
		}
		
		return withdrawed;
	}	
}
