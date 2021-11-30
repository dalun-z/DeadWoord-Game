package cs345.deadwood;

import java.util.*;

public class GameState {
    private static GameState instance;
    
    public Player currentPlayer;
    public BoardView boardView = new BoardView();
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Scene> scenes = new ArrayList<Scene>();
    public HashMap<String, Location> locations = new HashMap<String, Location>();
    public Integer days;
    public String action = "";

    private GameState() {};

    static {
        instance = new GameState();
    }

    public static GameState getInstance() {
        return instance;
    }

    public void nextPlayer() {
        this.currentPlayer = this.players.get(((this.currentPlayer.getPlayer() - 1) + 1) % this.players.size());
    }

    public boolean validateMove(int x, int y, ArrayList<String> validDestinations) {
        String destination = currentPlayer.getLocation();
        boolean valid = false;
        
        for (String d : validDestinations) {
            // System.out.println("Testing destination: " + d);
            Location testDest = locations.get(d);
            Area a = testDest.getCardArea();
            
            if (x >= a.getX() && x <= a.getX() + a.getW() && y >= a.getY() && y <= a.getY() + a.getH()) {
                valid = true;
                destination = d;
            }
        }

        // System.out.println("moving player...");
        currentPlayer.setLocation(destination);
        return valid;
    }

    public boolean validateRoleSelection(int x, int y, Player p) {
        Location loc = locations.get(p.getLocation());
        ArrayList<ExtraRole> extraRoles = loc.getRoles();
        ArrayList<StarringRole> starRoles = loc.getScene().getRoles();
        boolean valid = false;
        Role chosenRole = null;

        for (ExtraRole er : extraRoles) {
            Area a = er.getArea();
            
            if (x >= a.getX() && x <= a.getX() + a.getW() && y >= a.getY() && y <= a.getY() + a.getH()) {
                valid = true;
                chosenRole = er;
            }
        }

        for (StarringRole sr : starRoles) {
            Area a = sr.getArea();
            
            if (x >= a.getX() && x <= a.getX() + a.getW() && y >= a.getY() && y <= a.getY() + a.getH()) {
                valid = true;
                chosenRole = sr;
            }
        }
        
        p.setRole(chosenRole);
        synchronized(this) {this.notify();};
        return valid;
    }

    public void awaitRoleChoice() {
        try {
            synchronized(boardView.buttonPanel) {
                boardView.buttonPanel.wait();
            }
        } catch (InterruptedException e) {
            System.out.println("Role selection choice interrupted");
        }
    }

    public void movePlayer(Player p, String l) {
        p.setLocation(l);
    }
}
