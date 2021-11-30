package cs345.deadwood;

interface Role {

    public int getRank();

    public Area getArea();

    public String getLine();
    
    public boolean isComplete();

    public Reward setComplete();
}