package cs345.deadwood;

import cs345.deadwood.Reward;

public class StarringRole implements Role {
    
    /**
     * 
     * @param   rank        required rank for this role
     * @param   complete    whether this role has been completed successfully
     * 
     */

    private int rank;
    private boolean complete;

    public int getRank() {
        return rank;
    }

    public boolean isComplete() {
        return complete;
    }

    public Reward setComplete() {
        complete = true;
        return new Reward("credits", 2);
    }
}
