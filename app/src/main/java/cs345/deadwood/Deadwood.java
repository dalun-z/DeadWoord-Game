/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cs345.deadwood;

import java.util.ArrayList;
import java.util.HashMap;
public class Deadwood {
    
    public static void main(String[] args) {
        // BoardView view = new BoardView();
        // view.init();
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Scene> scenes = new ArrayList<Scene>();
        HashMap<String, Location> locations = new HashMap<String, Location>();

        parsingXML parser = new parsingXML();

        parser.parse(locations, "board");
        // parser.readCardData();

        System.out.println("Locations: ");
        System.out.println(locations);
        // for(i = whatever to total locations) {
        //     locations.get(whatever).scene = math.random(scene);
        // }

        LaunchPage launchPage = new LaunchPage(players);

        players.add(new Player(5, "Jail", 4, 3, "b", 6));


    }
}