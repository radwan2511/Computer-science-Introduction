

public class Bit {

    private  boolean value;
    public static  final Bit ONE  = new Bit(true);
    public static  final Bit ZERO = new Bit(false);

    public Bit(boolean value) {
        this.value = value;
    }

    public Bit(int intValue) {
        if (intValue == 0)
            value = false;
        else {
            if (intValue == 1)
                value = true;
            else throw new IllegalArgumentException(value + " is neither 0 nor 1.");
        }
    }

    public String toString() {
        return "" + toInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Bit))
            return false;
        else return value == ((Bit) obj).value;
    }

    public Bit negate() {
        Bit output;
        if (value)
            output = ZERO;
        else output = ONE;
        return output;
    }

    public int toInt() {
        int output;
        if(value)
            output = 1;
        else
            output = 0;
        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 1.1 ================================================
    public static Bit fullAdderSum(Bit bit1, Bit bit2, Bit bit3) {
        if(bit1 == null | bit2 == null | bit3 == null){
            throw new IllegalArgumentException("One of the bits is null.");
        }
        return new Bit((bit1.toInt() + bit2.toInt() + bit3.toInt()) % 2 );
    }

    public static Bit fullAdderCarry(Bit bit1, Bit bit2, Bit bit3) {
        if(bit1 == null | bit2 == null | bit3 == null){
            throw new IllegalArgumentException("One of the bits is null.");
        }
        return new Bit((bit1.toInt() + bit2.toInt() + bit3.toInt()) / 2 );
    }

}
