package ReadData.Readers;

import DatabaseHandler.Controler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author F0urth
 */

public
    interface Reader
        extends CSVReader, XMLReader{

    /**
     * Take care of XMLFile
     * @param path
     */
    default void read(String path) {
        var args = new ArrayList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            var documentBuilder = factory.newDocumentBuilder();
            var document = documentBuilder.parse(path);
            var person = document.getElementsByTagName("person");
            for (int i = 0; i < person.getLength(); i++) {
                var element = person.item(i);
                if (element.getNodeType() == Node.ELEMENT_NODE) {
                    Element ele = (Element) element;

                }

                if (args.size() > 10000) {

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Take care of CSVFile
     * @param reader
     */
    default void read(BufferedReader reader) {
        var args = new ArrayList<String>();
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null){
                args.add(line);
                if (args.size() > 10000) {
                    Controler.INSTANCE
                        .insertQueries(processCSV(args));
                    while (true){
                        if (Controler.INSTANCE.isDBReady()) break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
