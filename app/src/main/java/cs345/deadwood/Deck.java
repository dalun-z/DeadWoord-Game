package cs345.deadwood;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;

public class Deck {
    private NodeList cards;
    private Document doc;
    
    // building a document from the XML file
    // returns a Document object after loading the book.xml file.
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

    public void init() {
        URL cardsFile = Deck.class.getClassLoader().getResource("cards.xml");
        
        try {
            doc = this.getDocFromFile(cardsFile.getPath().replace("%20", " "));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
            return;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }

        readCardData(doc);
    }

    private void readCardData(Document d) {
        Element root = d.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");

        for (int i = 0; i < cards.getLength(); i++) {
            System.out.println("reading card...");

            Node card = cards.item(i);
            String cardData = card.getAttributes().getNamedItem("name").getNodeValue();

            System.out.println("Card name: " + cardData);
            System.out.println();
        }
    }
    // public NodeList deal() {
    //     return new NodeList();
    // }
}
