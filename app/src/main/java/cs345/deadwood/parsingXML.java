package cs345.deadwood;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;

public class parsingXML{

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
            System.out.println("Cards : " + (i+1));

            Node card = cards.item(i);
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            System.out.println("Card name is : " + cardName);

            NodeList child = card.getChildNodes();

            for(int j = 0; j < child.getLength(); j++){
                Node sub = child.item(j);

                if()
            }
        }
    }
}
