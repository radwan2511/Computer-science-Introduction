/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
public class BinaryNode<T> {

    protected T data;
    protected BinaryNode<T> left;
    protected BinaryNode<T> right;

    public BinaryNode(T element) {
        if (element == null)
            throw new IllegalArgumentException();
        this.data = element;
        left = null;
        right = null;
    }

    public void insert(T element) {
        if (Math.random() < 0.5) {
            if (left == null) left = new BinaryNode<T>(element);
            else left.insert(element);
        } else {
            if (right == null) right = new BinaryNode<T>(element);
            else right.insert(element);
        }
    }

    public BinaryNode<T> remove(T toRemove) {
        BinaryNode<T> output = this;

        if (data.equals(toRemove)) {
            if (left != null) {
                data = left.data;
                left = left.remove(data);
            } else if (right != null) {
                data = right.data;
                right = right.remove(data);
            } else output = null;
        } else {
            if (left != null && left.contains(toRemove))
                left = left.remove(toRemove);
            else if (right != null)
                right = right.remove(toRemove);
        }
        return output;
    }

    public boolean contains(T element) {
        boolean found = false;
        if (data.equals(element))
            found = true;
        else if (left != null && left.contains(element))
            found = true;
        else if (right != null && right.contains(element))
            found = true;
        return found;
    }

    public int height() {
        int leftH = -1, rightH = -1;
        if (left != null)
            leftH = left.height();
        if (right != null)
            rightH = right.height();
        return Math.max(leftH, rightH) + 1;
    }

    public int size() {
        int leftS = 0, rightS = 0;
        if (left != null)
            leftS = left.size();
        if (right != null)
            rightS = right.size();
        return leftS + rightS + 1;
    }

    public boolean equals(Object other) {
        boolean isEqual = true;
        //Check type
        if (!(other instanceof BinaryNode<?>))
            isEqual = false;
        else {
            BinaryNode<?> otherNode = (BinaryNode<?>) other;
            //Check data
            if (!data.equals(otherNode.data))
                isEqual = false;
                //Check left
            else if (((left == null) | (otherNode.left == null)) & (left != otherNode.left))
                isEqual = false;
            else if ((left != null) && !left.equals(otherNode.left))
                isEqual = false;
                //Check right
            else if (((right == null) | (otherNode.right == null)) & (right != otherNode.right))
                isEqual = false;
            else if ((right != null) && !right.equals(otherNode.right))
                isEqual = false;
        }
        return isEqual;
    }

    //Complete the following method
    public String toString() {
        // task4
        if(this.data == null)
        	throw new IllegalArgumentException("tree is null.");
        String tree = "";
        
        tree = inorder(this);
        return tree;
    }

    
    /* Given a binary tree, print  
    its nodes in inorder*/
    private String inorder(BinaryNode node) 
    {
    	String ans = "";
    	if (node != null) { 
   
		 /* first recur on left child */
		 ans = ans + inorder(node.left); 
		   
		 /* then add the data of node */
		 for (int i=0;i<node.getLevelOfNode(this,node.data,1)*2 - 2;i=i+1)
		 {
			 ans = ans +" ";
		 }
		 ans = ans + node.data+"\n";
		   
		 /* now recur on right child */
		 ans = ans + inorder(node.right);
    	}
    	return ans;
 } 
    
  //To get level of node in a binary tree
  	private int getLevelOfNode(BinaryNode root,T key,int level)
  	{
  		if(root==null)
  			return 0;
  		if(root.data.equals(key))
  			return level;
  		int result=getLevelOfNode(root.left,key,level+1) ;
  		if(result!=0)
  		{ 
  			// If found in left subtree , return 
  			return result;
  		}
  		result= getLevelOfNode(root.right,key,level+1);
   
  		return result;
  	}
    

}