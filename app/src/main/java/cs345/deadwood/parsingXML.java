package cs345.deadwood;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;
import java.util.*;

public class parsingXML{
    // private int x, y;
    Document doc = null;

    // public static void main(String[] args) {
    //     Document doc = null;
    //     parsingXML parsing = new parsingXML();

    //     try {
    //         URL resource = parsingXML.class.getClassLoader().getResource("cards.xml");
    //         doc = parsing.getDocFromFile(resource.getPath().replace("%20", " "));
    //         parsing.readBookData(doc);

    //     } catch (NullPointerException e) {
    //         System.out.println("Error = " + e);
    //         return;
    //     } catch (Exception e) {
    //         System.out.println("Error = " + e);
    //         return;
    //     }
    // }

    public parsingXML() {}

    public void parseBoard(HashMap<String, Location> map) {
        Document doc = null;

        try {
            URL resource = parsingXML.class.getClassLoader().getResource("board.xml");
            doc = this.getDocFromFile(resource.getPath().replace("%20", " "));
            this.readBoardData(doc, map);
        } catch (NullPointerException e) {
            System.out.println("Error = " + e);
            return;
        } catch (Exception e) {
            System.out.println("Error = " + e);
            return;
        }
    }
    
    public void parseCards(ArrayList<Scene> scenes) {
        Document doc = null;

        try {
            URL resource = parsingXML.class.getClassLoader().getResource("cards.xml");
            doc = this.getDocFromFile(resource.getPath().replace("%20", " "));
            this.readCardData(doc, scenes);
        } catch (NullPointerException e) {
            System.out.println("Error = " + e);
            return;
        } catch (Exception e) {
            System.out.println("Error = " + e);
            return;
        }
    }

    private Document getDocFromFile(String filename) throws ParserConfigurationException {
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = null;

            try {
                doc = db.parse(filename);
            } catch (Exception ex) {
                System.out.println("XML parse failure");
                ex.printStackTrace();
            }
            return doc;
        } // exception handling
    }

    public void readCardData(Document d, ArrayList<Scene> scenes){
        Element root = d.getDocumentElement();

        NodeList cards = root.getElementsByTagName("card");

        for(int i = 0; i < cards.getLength(); i++){

            Node card = cards.item(i);
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            // System.out.println("Card name is : " + cardName);

            String imgFileName = card.getAttributes().getNamedItem("img").getNodeValue();
            // System.out.println("Card img file name is : " + imgFileName);

            String cardBudget = card.getAttributes().getNamedItem("budget").getNodeValue();
            // System.out.println("Card budget is : " + cardBudget);

            Scene scene = new Scene(cardName, imgFileName, Integer.parseInt(cardBudget));

            NodeList part = card.getChildNodes();

            for(int j = 0; j < part.getLength(); j++){
                Node parts = part.item(j);

                // ByTagName (part, area, etc)
                if("scene".equals(parts.getNodeName())){
                    String sceneNum = parts.getAttributes().getNamedItem("number").getNodeValue();
                    // System.out.println("Scene number is : " + sceneNum);
                    String sceneTxt = parts.getTextContent();
                    // System.out.println("Scene description : " + sceneTxt);

                    scene.setNumber(Integer.parseInt(sceneNum));
                    scene.setDescription(sceneTxt);
                }
                
                if("part".equals(parts.getNodeName())){
                    String partName = parts.getAttributes().getNamedItem("name").getNodeValue();
                    // System.out.println("Part name : " + partName);
                    String level = parts.getAttributes().getNamedItem("level").getNodeValue();
                    // System.out.println("Level is : " + level);
                    
                    StarringRole role = new StarringRole(partName, Integer.parseInt(level));
                    
                    NodeList partInfos = parts.getChildNodes();
                    for(int k = 0; k < partInfos.getLength(); k++){
    
                        Node partInfo = partInfos.item(k);
                        if("area".equals(partInfo.getNodeName())){
                            String x = partInfo.getAttributes().getNamedItem("x").getNodeValue();
                            String y = partInfo.getAttributes().getNamedItem("y").getNodeValue();
                            String h = partInfo.getAttributes().getNamedItem("h").getNodeValue();
                            String w = partInfo.getAttributes().getNamedItem("w").getNodeValue();
                            // System.out.print("Location coordinates : ");
                            // System.out.println("x = " + x + ", y = " + y + ", h = " + h + ", w = " + w);

                            Area a = new Area(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h));
                            role.setArea(a);
                        }

                        if(partInfo.getNodeName().equals("line")) {
                            String line = partInfo.getTextContent();
                            
                            role.setLine(line);
                        }

                        scene.addRole(role);
                    }
                }
            }

            scenes.add(scene);
            // System.out.println("\n");
        }
    }

    public void readBoardData(Document d, HashMap<String, Location> map) {
        Element root = d.getDocumentElement();
        NodeList board = root.getElementsByTagName("set");
        
        for(int i = 0; i < board.getLength(); i++) {
            Node set = board.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();
            // System.out.println("Set name is: " + setName);

            Area cardArea = new Area();
            ArrayList<String> neighbors = new ArrayList<String>();
            ArrayList<Area> takes = new ArrayList<Area>();
            ArrayList<ExtraRole> roles = new ArrayList<ExtraRole>();

            NodeList nodes = set.getChildNodes();
            
            for (int j = 0; j < nodes.getLength(); j++) {
                Node subNode = nodes.item(j);

                NodeList subNodeComponents = subNode.getChildNodes();
                //ByTagName
                // Process neighbors of set
                if("neighbors".equals(subNode.getNodeName())) {
                    for(int k = 0; k < subNodeComponents.getLength(); k++) {
                        Node component = subNodeComponents.item(k);
                        if("neighbor".equals(component.getNodeName())) {
                            String setNeighbor = component.getAttributes().getNamedItem("name").getNodeValue();
                            neighbors.add(setNeighbor);
                            // System.out.println("Set Neighbor is: " + setNeighbor);
                        }
                    }
                }
                
                // Process card area for set
                if("area".equals(subNode.getNodeName())) {
                    String x = subNode.getAttributes().getNamedItem("x").getNodeValue();
                    String y = subNode.getAttributes().getNamedItem("y").getNodeValue();
                    String h = subNode.getAttributes().getNamedItem("h").getNodeValue();
                    String w = subNode.getAttributes().getNamedItem("w").getNodeValue();

                    cardArea.setX(Integer.parseInt(x));
                    cardArea.setY(Integer.parseInt(y));
                    cardArea.setW(Integer.parseInt(w));
                    cardArea.setH(Integer.parseInt(h));

                    // System.out.print("Set coordinates: ");
                    // System.out.println("x = " + x + ", y = " + y + ", h = " + h + ", w = " + w);
                }
                
                if("takes".equals(subNode.getNodeName())) {
                    for(int k = 0; k < subNodeComponents.getLength(); k++) {
                        Node takeNode = subNodeComponents.item(k);
                        
                        if("take".equals(takeNode.getNodeName())) {
                            // String setTake = takeNode.getAttributes().getNamedItem("number").getNodeValue();
                            // System.out.println("Set Takes: " + setTake);
                            
                            NodeList areas = takeNode.getChildNodes();
                            for(int l = 0; l < areas.getLength(); l++) {
                                Node areaNode = areas.item(l);
                                if("area".equals(areaNode.getNodeName())) {
                                    String x = areaNode.getAttributes().getNamedItem("x").getNodeValue();
                                    String y = areaNode.getAttributes().getNamedItem("y").getNodeValue();
                                    String h = areaNode.getAttributes().getNamedItem("h").getNodeValue();
                                    String w = areaNode.getAttributes().getNamedItem("w").getNodeValue();
                                    
                                    // System.out.print("Takes coordinates: ");
                                    // System.out.println("x = " + x + ", y = " + y + ", h = " + h + ", w = " + w);
                                    
                                    Area a = new Area(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h));
                                    takes.add(a);
                                }
                            }
                        }
                    }
                }
                
                if("part".equals(subNode.getNodeName())) {
                    for(int k = 0; k < subNodeComponents.getLength(); k++) {
                        Node partNode = subNodeComponents.item(k);
                        if("part".equals(partNode.getNodeName())) {
                            String roleName;
                            int rank;
                            Area area = new Area();
                            String line = "";

                            String partName = partNode.getAttributes().getNamedItem("name").getNodeValue();
                            // System.out.println("Part name: " + partName);
                            roleName = partName;

                            String partLevel = partNode.getAttributes().getNamedItem("level").getNodeValue();
                            // System.out.println("Part level: " + partLevel);
                            rank = Integer.parseInt(partLevel);

                            NodeList partsPartInfo = partNode.getChildNodes();
                            for(int l = 0; l < partsPartInfo.getLength(); l++) {
                                Node partComponent = subNodeComponents.item(l);
                                
                                if ("area".equals(partComponent.getNodeName())) {
                                    String x = partComponent.getAttributes().getNamedItem("x").getNodeValue();
                                    String y = partComponent.getAttributes().getNamedItem("y").getNodeValue();
                                    String h = partComponent.getAttributes().getNamedItem("h").getNodeValue();
                                    String w = partComponent.getAttributes().getNamedItem("w").getNodeValue();
                                    // System.out.print("Takes coordinates: ");
                                    System.out.println("x = " + x + ", y = " + y + ", h = " + h + ", w = " + w);
                                    
                                    area.setX(Integer.parseInt(x));
                                    area.setY(Integer.parseInt(y));
                                    area.setW(Integer.parseInt(w));
                                    area.setH(Integer.parseInt(h));
                                }
                                
                                if ("line".equals(partComponent.getNodeName())) {
                                    String partLine = partComponent.getTextContent();
                                    // System.out.println("part line: " + partLine);
                                    line = partLine;
                                }
                            }

                            ExtraRole role = new ExtraRole(roleName, rank, area, line);
                            roles.add(role);
                        }
                    }
                }

                // System.out.println("\n");
            }
            Location loc = new Location(cardArea, neighbors, takes, roles);
            map.put(setName, loc);
            // System.out.println("Added location to map");
        }
    }
}
