package cs345.deadwood;

public class Player {
    /* Player class with player info
    *   @param  player    The current rank of the player
    *   @param  area Current number of credits owned by the player
    *   @param  cash Current number of dollars in player's wallet
    *   @param  credit    Current role of the player
    *   @param  dice  Whether player is acting
    */

    private int player;
    private String area;
    private int cash;
    private int credit;
    private String dice;
    private int rank;

    public Player(int player, String area, int cash, int credit, String dice, int rank){
        this.player = player;
        this.area = area;
        this.cash = cash;
        this.credit = credit;
        this.dice = dice; 
        this.rank = rank;
    }

    public void setArea(String area){
        this.area = area;
    }

    public void setCash(int cash){
        this.cash += cash;
    }

    public void setCredit(int credit){
        this.credit += credit;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getPlayer(){
        return this.player;
    }

    public int getRank(){
        return this.rank;
    }

    public int getCash(){
        return this.cash;
    }

    public int getCredit(){
        return this.credit;
    }

    public String getArea(){
        return this.area;
    }

    public String getDice(){
        return "dice_" + this.dice + this.rank + ".png";
    }

    
    public void act() {
        // set role to complete, get credits/dollars

        // try to act the role
        //      if successful...
        //      else ...
    }
}