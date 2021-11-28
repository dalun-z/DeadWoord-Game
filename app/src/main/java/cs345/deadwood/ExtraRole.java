package cs345.deadwood;

import cs345.deadwood.Reward;

public class ExtraRole implements Role {
    
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

    public ExtraRole(String name, int rank, Area area, String line) {
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

    //TODO: implement proper reward for extra role - $1 + C1 for success, $1 for fail
    public Reward setComplete() {
        complete = true;
        return new Reward("dollars", 2);
    }

    public String getName() {
        return this.name;
    }

    public String getLine() {
        return this.line;
    }
}