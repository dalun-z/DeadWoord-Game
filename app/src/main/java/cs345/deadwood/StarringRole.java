package cs345.deadwood;

import cs345.deadwood.Reward;

public class StarringRole implements Role {
    
    /**
     * 
     * @param   rank        required rank for this role
     * @param   complete    whether this role has been completed successfully
     * 
     */

    private String name;
    private int rank;
    private boolean complete;
    private Area area;
    private String line;

    public StarringRole(String name, int rank, Area area, String line) {
        this.name = name;
        this.rank = rank;
        this.area = area;
        this.complete = false;
        this.line = line;
    }

    public int getRank() {
        return rank;
    }

    public boolean isComplete() {
        return complete;
    }

    public Area getArea() {
        return this.area;
    }

    // TODO: implement proper reward for starring role - C2 for success
    public Reward setComplete() {
        complete = true;
        return new Reward("credits", 2);
    }

    public String getName() {
        return this.name;
    }

    public String getLine() {
        return this.line;
    }
}