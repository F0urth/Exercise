package ReadData.Readers;


import Main.Main;
import ReadData.ProcessContainers.Customer;

import java.util.List;

/**
 * @author F0urth
 */

interface CSVReader {

    default List<String> processCSV(List<String> data) {
        for (var item : data) {
            var tab = item.split(",");
            var idCustomer = Main.idCustomers.incrementAndGet();
            var customer = Customer.newInstance(idCustomer, tab[0].trim(), tab[1].trim(),
                (tab[2].isBlank()) ? null : Integer.valueOf(tab[2].trim()));
        }
        return null;
    }
}
