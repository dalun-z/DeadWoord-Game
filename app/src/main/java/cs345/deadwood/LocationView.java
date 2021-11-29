package cs345.deadwood;

import javax.swing.*;

import java.awt.Component;
import java.util.*;

public class LocationView {

    private JFrame board;
    private HashMap<String, Location> locations;
    private JLabel card;
    private JLabel role1;
    private JLabel shotIcon;
    private GameState global = GameState.getInstance();
    private HashMap<String, JLabel> locationLabels = new HashMap<String, JLabel>();

    public LocationView(JFrame parentContainer) {
        board = parentContainer;
        this.locations = global.locations;
    }

    public void drawLocations() {
        for(String key : locations.keySet()) {
            if (key != "office" && key != "trailer") {
                Location l = locations.get(key);
                String imagePath = l.getScene().getImagePath();
                System.out.println("Image path: " + imagePath);
                
                if (l.isRevealed()) {
                    card = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + imagePath).getPath().replace("%20", " ")));
                    System.out.println("card dimensions: x: " + l.getCardArea().getX() + ", y: " + l.getCardArea().getY());
                    card.setLocation(l.getCardArea().getX(), l.getCardArea().getY()); 
                    card.setSize(l.getCardArea().getW(), l.getCardArea().getH()); 
                    card.setName(key);
                    board.add(card);
                } else {
                    card = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath().replace("%20", " ")));
                    locationLabels.put(key, card);
                    card.setLocation(l.getCardArea().getX(), l.getCardArea().getY()); 
                    card.setSize(l.getCardArea().getW(), l.getCardArea().getH()); 
                    card.setName(key);
                    board.add(card);
                }
            }
        }
    }

    public void revealLocation(String loc) {
        Location l = locations.get(loc);
        String imagePath = l.getScene().getImagePath();
        JLabel label = locationLabels.get(loc);
                
        label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + imagePath).getPath().replace("%20", " ")));
    }
}
