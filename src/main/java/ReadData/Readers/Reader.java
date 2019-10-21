package ReadData.Readers;

import DatabaseHandler.Controller;
import Main.Main;
import ReadData.ProcessContainers.Builder;
import ReadData.ProcessContainers.Contact;
import ReadData.ProcessContainers.Customer;
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

public interface Reader extends CSVReader, XMLReader {

    Integer BATCH_SIZE = 10000;

    /**
     * Take care of XMLFile
     * Method parse xml file and thought 'XMLBuilder' classes
     *
     * @param path
     * @see Contact.ContactBuilder
     * @see Customer.CustomerBuilder
     */
    default void readXML(String path) {
        System.out.println("Start reading");
        var args = new ArrayList<Builder>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            NodeList persons = document.getElementsByTagName("person");
            for (int i = 0; i < persons.getLength(); i++) {
                Node p = persons.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element person = (Element) p;
                    var customerBuilder = new Customer.CustomerBuilder();
                    var id = Main.Main.IdsContainer.ID_CUSTOMER.getAndIncrement();
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
                                            var contactBuilder = new Contact.ContactBuilder();
                                            var contacId = Main.Main.IdsContainer.ID_CONTACT.getAndIncrement();
                                            contactBuilder.setId_customer(id);
                                            contactBuilder.setId(contacId);
                                            var contact = (Element) contactNode;
                                            var type = contact.getTagName();
                                            contactBuilder.setType(
                                                (type.equals("phone")) ? Contact.ContactType.PHONE :
                                                    (type.equals("email")) ? Contact.ContactType.EMAIL :
                                                    (type.equals("jabber")) ? Contact.ContactType.JABBER :
                                                        Contact.ContactType.UNKNOWN);
                                            contactBuilder.setContact(contact.getTextContent());
                                            args.add(contactBuilder.buildContact());
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    args.add(customerBuilder.buildCustomer());
                    if (args.size() > BATCH_SIZE) {
                        Controller.INSTANCE
                            .insertQueries(processXML(args));
                        while (true) {
                            if (Controller.INSTANCE.isDBReady()) {
                                break;
                            }
                        }
                        args = new ArrayList<>();
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        Controller.INSTANCE.insertQueries(processXML(args));
        System.out.println("readed data");
    }

    /**
     * Take care of CSVFile
     *
     * @param reader
     */
    default void read(BufferedReader reader) {
        var args = new ArrayList<String>();
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null) {
                args.add(line);
                if (args.size() > BATCH_SIZE) {
                    Controller.INSTANCE.insertQueries(processCSV(args));
                    while (true) {
                        if (Controller.INSTANCE.isDBReady()) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller.INSTANCE.insertQueries(processCSV(args));
    }
}
