package ReadData.Readers;


import Main.Main;
import ReadData.ProcessContainers.Contact;
import ReadData.ProcessContainers.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Processing CSV File
 *
 * @author F0urth
 */

interface CSVReader {

    /**
     * Method take each of element and produce
     * of it customer and contacts to him
     * @param data
     * @return list of sql queries
     */
    default List<String> processCSV(List<String> data) {
        var res = new ArrayList<String>();
        for (var item : data) {
            var tab = item.split(",");
            var idCustomer = Main.idCustomers.getAndIncrement();
            res.add(Customer.newInstance(idCustomer, tab[0].trim(), tab[1].trim(),
                (tab[2].isBlank()) ? null : Integer.valueOf(tab[2].trim())).buildSQLQuery());
            for (int i = 4; i < tab.length; i++) {
                var idContact = Main.idContact.getAndIncrement();
                res.add(Contact.newInstance(idContact, idCustomer,
                    Contact.getContactType(tab[i]), tab[i]).buildSQLQuery());
            }
        }
        return res;
    }
}
