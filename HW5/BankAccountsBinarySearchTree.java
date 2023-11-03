/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}
	
	// Complete the following methods
	public void balance(){
		// task 5b
		List<BankAccount> list = new DynamicArray<BankAccount>();
		Iterator<BankAccount> tree = this.iterator();
		while(tree.hasNext()) {
			list.add(tree.next());
		}
		root = null;
		buildBalancedTree(list,0,list.size()-1);
		
	}
	
	private void buildBalancedTree(List<BankAccount> list, int low, int high){
		// task 5b
		/* Base Case */
        if (low <= high) {   
 	        /* Get the middle element and make it root */
	        int mid = (low + high) / 2; 
	        insert(list.get(mid)); 
	        /* Recursively construct the left subtree and make it 
	         left child of root */
	        buildBalancedTree(list, low, mid - 1); 
	        /* Recursively construct the right subtree and make it 
	         right child of root */
	        buildBalancedTree(list, mid + 1, high); 
        }  
	}
}
