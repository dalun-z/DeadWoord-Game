package cs345.deadwood;

import java.util.*;


public class Scene {
    int number;
    int budget;
    String imagePath;
    String name;
    String description;
    ArrayList<StarringRole> roles;

    public Scene(String name, String imagePath, int budget) {
        this.name = name;
        this.imagePath = imagePath;
        this.budget = budget;
        this.roles = new ArrayList<StarringRole>();
    }

    public Scene(String name, String imagePath, String description, int budget, int number, ArrayList<StarringRole> roles) {
        this.name = name;
        this.imagePath = imagePath;
        this.budget = budget;
        this.number = number;
        this.description = description;
        this.roles = roles;
    }

    public int getNumber() {
        return this.number;
    }

    public int getBudget() {
        return this.budget;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<StarringRole> getRoles() {
        return this.roles;
    }

    public void setNumber(int n) {
        this.number = n;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void addRole(StarringRole role) {
        this.roles.add(role);
    }
}
