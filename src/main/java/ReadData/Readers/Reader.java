package ReadData.Readers;

import DatabaseHandler.Controler;
import Main.Main;
import ReadData.ProcessContainers.Builder;
import ReadData.ProcessContainers.ForXMLBuilders.ContactBuilder;
import ReadData.ProcessContainers.ForXMLBuilders.CustomerBuilder;
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
    default void readXML(String path) {
        System.out.println("Start reading");
        var args = new ArrayList<Builder>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse("/home/kamil/Projects/Java/Etap2Zadanie/data/dane-osoby.xml");
            NodeList persons = document.getElementsByTagName("person");
            for (int i = 0; i < persons.getLength(); i++) {
                Node p = persons.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element person = (Element) p;
                    var customerBuilder = new CustomerBuilder();
                    var id = Main.idCustomers.getAndIncrement();
                    customerBuilder.setId(id);
                    NodeList atributes = person.getChildNodes();
                    for (int j = 0; j < atributes.getLength(); j++) {
                        var atrib = atributes.item(j);
                        if (atrib.getNodeType() == Node.ELEMENT_NODE) {
                            Element val = (Element) atrib;
                            switch (val.getTagName()) {
                                case "age":
                                    customerBuilder.setAge(Integer.valueOf(val.getTextContent()));
                                    break;
                                case "name":
                                    customerBuilder.setName(val.getTextContent());
                                    break;
                                case "surname":
                                    customerBuilder.setSurname(val.getTextContent());
                                    break;
                                case "contacts":
                                    var contacts = val.getChildNodes();
                                    for (int k = 0; k < contacts.getLength(); k++) {
                                        var contactNode = contacts.item(k);
                                        if (contactNode.getNodeType() == Node.ELEMENT_NODE) {
                                            var contactBuilder = new ContactBuilder();
                                            var contacId = Main.idContact.getAndIncrement();
                                            contactBuilder.setId_customer(id);
                                            contactBuilder.setId(contacId);
                                            var contact = (Element) contactNode;
                                            var type = contact.getTagName();
                                            contactBuilder.setType(
                                                (type.equals("phone")) ? 2 : (type.equals("email")) ? 1 :
                                                    (type.equals("jabber")) ? 3 : 0);
                                            contactBuilder.setContact(contact.getTextContent());
                                            args.add(contactBuilder.buildContact());
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    args.add(customerBuilder.buildCustomer());
                    if (args.size() > 10000) {
                        Controler.INSTANCE
                            .insertQueries(processXML(args));
                        while (true) {
                            if (Controler.INSTANCE.isDBReady()) break;
                        }
                        args = new ArrayList<>();
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controler.INSTANCE
            .insertQueries(processXML(args));
        System.out.println("readed data");
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
        Controler.INSTANCE
            .insertQueries(processCSV(args));
    }
}
