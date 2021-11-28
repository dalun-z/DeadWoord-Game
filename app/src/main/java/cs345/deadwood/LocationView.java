package cs345.deadwood;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class LocationView {

    private final JFrame board;
    private HashMap<String, Location> locations;
    private JLabel card;
    private JLabel role1;
    private JLabel shotIcon;

    public LocationView(JFrame parentContainer, HashMap<String, Location> locations) {
        board = parentContainer;
        this.locations = locations;
    }

    public void drawLocations() {
        for(String key : locations.keySet()) {
            Location l = locations.get(key);
            String imagePath = l.getScene().getImagePath();

            if (l.isRevealed()) {
                card = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + imagePath).getPath().replace("%20", " ")));
                System.out.println("card dimensions: x: " + l.getCardArea().getX() + ", y: " + l.getCardArea().getY());
                card.setLocation(l.getCardArea().getX(), l.getCardArea().getY()); 
                card.setSize(l.getCardArea().getW(), l.getCardArea().getH()); 
                board.add(card);
            } else {
                card = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath().replace("%20", " ")));
                card.setLocation(l.getCardArea().getX(), l.getCardArea().getY()); 
                card.setSize(l.getCardArea().getW(), l.getCardArea().getH()); 
                board.add(card);
            }
        }
    }
}
