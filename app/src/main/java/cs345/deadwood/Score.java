package cs345.deadwood;

import java.util.*;


public class Score{

    private int cash;
    private int credit;
    private int rank;

    public Score(){

    }

    int calScore(int cash, int credit, int rank){

        return cash + credit + 5 * rank;

    }

}