import java.util.Iterator;
import java.util.NoSuchElementException;


public class ThresholdBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
    private BankAccount current;
    private int balanceThreshold;
    
    public ThresholdBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, int balanceThreshold) {
        // task 5c
        this.iterator = (BinaryTreeInOrderIterator<BankAccount>) bankAccountsTree.iterator();
        this.balanceThreshold  = balanceThreshold;
        
        boolean found = false;
    	while(iterator.hasNext() & found != true) {
        	BankAccount b = iterator.next();
        	if(b.getBalance() >= balanceThreshold) {
        		found = true;
        		current = b;
        	}
        }
    }

    //Complete the following method
    @Override
    public boolean hasNext() {
        // task 5c
    	return current != null;
    }

    //Complete the following method
    @Override
    public BankAccount next() {
        // task 5c
        if(!hasNext())
        	throw new NoSuchElementException();
        BankAccount ans = current;
        
        boolean found = false;
    	while(iterator.hasNext() & found != true) {
        	BankAccount b = iterator.next();
        	if(b.getBalance() >= balanceThreshold) {
        		found = true;
        		current = b;
        	}
        }
        if(found == false)
            current = null;
        
        return ans;
    }
}
