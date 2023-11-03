/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2b
		int cmp = 0;
		if (account1.getAccountNumber() > account2.getAccountNumber())
		   cmp = 1;
		else if (account1.getAccountNumber() < account2.getAccountNumber())
		   cmp = -1;
		return cmp;
	}

}
