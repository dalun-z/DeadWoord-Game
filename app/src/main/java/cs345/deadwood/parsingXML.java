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

public class parsingXML{


    private int x, y;
    public static void main(String[] args) {
        Document doc = null;
        parsingXML parsing = new parsingXML();

        try {
            URL resource = parsingXML.class.getClassLoader().getResource("cards.xml");
            doc = parsing.getDocFromFile(resource.getPath().replace("%20", " "));
            parsing.readBookData(doc);

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

    private void readBookData(Document d){
        Element root = d.getDocumentElement();

        NodeList cards = root.getElementsByTagName("card");

        for(int i = 0; i < cards.getLength(); i++){

            Node card = cards.item(i);
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            System.out.println("Card name is : " + cardName);

            String imgFileName = card.getAttributes().getNamedItem("img").getNodeValue();
            System.out.println("Card img file name is : " + imgFileName);

            String cardBudget = card.getAttributes().getNamedItem("budget").getNodeValue();
            System.out.println("Card budget is : " + cardBudget);

            NodeList part = card.getChildNodes();

            for(int j = 0; j < part.getLength(); j++){
                Node parts = part.item(j);

                // ByTagName (part, area, etc)
                if("scene".equals(parts.getNodeName())){
                    String sceneNum = parts.getAttributes().getNamedItem("number").getNodeValue();
                    System.out.println("Scene number is : " + sceneNum);
                    String sceneTxt = parts.getTextContent();
                    System.out.println("Scene describtion : " + sceneTxt);
                }
                
                if("part".equals(parts.getNodeName())){
                    String partName = parts.getAttributes().getNamedItem("name").getNodeValue();
                    System.out.println("Part name : " + partName);
                    String level = parts.getAttributes().getNamedItem("level").getNodeValue();
                    System.out.println("Level is : " + level);

                }

                NodeList partInfos = parts.getChildNodes();
                for(int k = 0; k < partInfos.getLength(); k++){

                    Node partInfo = partInfos.item(k);
                    if("area".equals(partInfo.getNodeName())){
                        String x = partInfo.getAttributes().getNamedItem("x").getNodeValue();
                        String y = partInfo.getAttributes().getNamedItem("y").getNodeValue();
                        String h = partInfo.getAttributes().getNamedItem("h").getNodeValue();
                        String w = partInfo.getAttributes().getNamedItem("w").getNodeValue();
                        System.out.print("Location coordinates : ");
                        System.out.println("x = " + x + ", y = " + y + ", h = " + h + ", w = " + w);
                    }
                }
            }
            System.out.println("\n");
        }
    }
}
