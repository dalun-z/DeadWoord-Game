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
    private String location;
    private int cash;
    private int credit;
    private String dice;
    private int rank;
    private Role role = null;

    public Player(int player, String location, int cash, int credit, String dice, int rank){
        this.player = player;
        this.location = location;
        this.cash = cash;
        this.credit = credit;
        this.dice = dice; 
        this.rank = rank;
    }

    public void setLocation(String location){
        this.location = location;
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

    public void setRole(Role r) {
        this.role = r;
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

    public String getLocation(){
        return this.location;
    }

    public String getDice(){
        return "dice_" + this.dice + this.rank + ".png";
    }

    public Role getRole() {
        return this.role;
    }

    
    public void act() {
        // set role to complete, get credits/dollars

        // try to act the role
        //      if successful...
        //      else ...
    }
}