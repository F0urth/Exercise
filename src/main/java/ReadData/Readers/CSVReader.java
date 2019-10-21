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
     *
     * @param data
     * @return list of sql queries
     */
    default List<String> processCSV(List<String> data) {
        var res = new ArrayList<String>();
        for (var item : data) {
            var tab = item.split(Consts.CSV_SPLITTER_REGEX);
            var idCustomer = Main.IdsContainor.ID_CUSTOMER.getAndIncrement();
            res.add(Customer.newInstance(idCustomer, tab[Consts.NAME_INDEX].trim(), tab[Consts.SURNAME_INDEX].trim(),
                (tab[Consts.AGE_INDEX].isBlank()) ? null : Integer.valueOf(tab[Consts.AGE_INDEX].trim())).buildSQLQuery());
            for (int i = Consts.CONTACTS_START_POINT; i < tab.length; i++) {
                var idContact = Main.IdsContainor.ID_CONTACT.getAndIncrement();
                res.add(Contact.newInstance(idContact, idCustomer, Contact.getContactType(tab[i]), tab[i]).buildSQLQuery());
            }
        }
        return res;
    }

    class Consts {
        private static final Integer NAME_INDEX = 0;
        private static final Integer SURNAME_INDEX = 1;
        private static final Integer AGE_INDEX = 2;
        private static final Integer CONTACTS_START_POINT = 4;
        private static final String CSV_SPLITTER_REGEX = ",";
    }
}
