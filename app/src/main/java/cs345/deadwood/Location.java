package cs345.deadwood;

import java.util.ArrayList;

public class Location {
    private Area cardArea;
    private Scene scene;
    private ArrayList<String> neighbors;
    private ArrayList<Area> takes;
    private int currentTake;
    private ArrayList<ExtraRole> roles;

    public Location(Area cardArea, ArrayList<String> neighbors, ArrayList<Area> takes, ArrayList<ExtraRole> roles) {
        this.cardArea = cardArea;
        this.neighbors = neighbors;
        this.takes = takes;
        this.currentTake = 0;
        this.roles = roles;
    }

    public Area getCardArea() {
        return this.cardArea;
    } 

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return this.scene;
    }

    public ArrayList<String> getNeighbors() {
        return neighbors;
    }

    public Area getCurrentTake() {
        return this.takes.get(this.currentTake);
    }

    public void nextTake() {
        this.currentTake++;
    }

    public ArrayList<ExtraRole> getRoles() {
        return this.roles;
    }
}
