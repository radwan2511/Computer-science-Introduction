
import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {
    	if (element == null)
			throw new IllegalArgumentException("input argument is null");
		if (element.toInt() == 1)
			numberOfOnes = numberOfOnes + 1;
		super.addLast(element);
		
    }

    public void addFirst(Bit element) {
    	if (element == null)
			throw new IllegalArgumentException("input argument is null");
		if (element.toInt() == 1)
			numberOfOnes = numberOfOnes + 1;
		super.addFirst(element);
    }

    public Bit removeLast() {
    	Bit last = super.getLast();
		if (last.toInt() == 1)
			numberOfOnes = numberOfOnes - 1;
		super.removeLast();
		return last;
    }

    public Bit removeFirst() {
    	Bit first = super.getFirst();
		if (first.toInt() == 1)
			numberOfOnes = numberOfOnes - 1;
		super.removeFirst();
		return first;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
        String output = super.toString();
    	output = output.replace("[", "");
    	output = output.replace("]", "");
    	output = output.replace(", ", "");
        String reversed="";
        for (int i = 0; i < output.length(); i++) {
        	reversed = output.charAt(i) + reversed;
        }
        output = "<"+reversed+">";  
    	return output;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
        if (other == null)
			throw new IllegalArgumentException("input argument is null");
    	if (other.contains(null))
			throw new IllegalArgumentException("input argument contains null");
    	super.clear();
    	Iterator<Bit> bitList = other.iterator();
        while(bitList.hasNext()) {
        	addLast(bitList.next());
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
        return super.size() > 0 && (super.getLast().toInt() == 0 | numberOfOnes > 1);
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
        boolean ans = isNumber();
        String str = toString();
        str = str.replace("<", "");
        str = str.replace(">", "");
        boolean a = (str.equals("0") | str.equals("01") | str.equals("11"));
        boolean b = super.size() > 2 && ( str.substring(0,2).equals("01") | str.substring(0,2).equals("10"));
        boolean c = super.size() > 2 && numberOfOnes == 2 & str.substring(0,2).equals("11");
        ans = ans & ( a | b | c);
        return ans;
    }

    public void reduce() {
        while(!isReduced() & super.size() > 0) {
    		removeLast();
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
        BitList comp = new BitList();
        Iterator<Bit> bitList = super.iterator();
        while(bitList.hasNext()) {
        	comp.addLast(bitList.next().negate());
        }
        return comp;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
        Bit ans = null;
    	if (super.size() > 0) {
        	ans = removeFirst();
        }
    	return ans;
    }

    public void shiftLeft() {
        addFirst(new Bit(0));
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
        while (super.size() < newLength) {
    		addLast(super.getLast());
    	}
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
