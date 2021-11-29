package cs345.deadwood;

import java.util.*;

public class GameState {
    public Player currentPlayer;
    public BoardView boardView = new BoardView();
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Scene> scenes = new ArrayList<Scene>();
    public HashMap<String, Location> locations = new HashMap<String, Location>();
    public Integer days;

    public GameState() {}
}
