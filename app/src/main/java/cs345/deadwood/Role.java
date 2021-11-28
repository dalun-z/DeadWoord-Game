package cs345.deadwood;

interface Role {

    public int getRank();

    public Area getArea();
    
    public boolean isComplete();

    public Reward setComplete();
}