package cs345.deadwood;

public class Dice {
    /*
    *   @param  faces   Total faces for this die
    *   @param  value   Current showing face of the die
    *
    *   
    */

    private static int value;


    /*
    *   @return     The new value of this die
    *
    */

    public int roll() {
        value = (int) Math.floor((Math.random() * 6)  + 1);
        return value;
    }

    public int getValue() {return value;};
}