package cs345.deadwood;

import java.util.ArrayList;

public class Location {
    
    private Integer x;
    private Integer y;

    private ArrayList<Location> neighbors;
    private Scene scene;

    public Location(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
