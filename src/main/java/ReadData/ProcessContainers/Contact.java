package ReadData.ProcessContainers;

import DatabaseHandler.SQLBuilder.SQLBuilder;
import DatabaseHandler.SQLBuilder.SQLKeyword;


/**
 * @author F0urth
 */

public
    class Contact {

    private static final String table_name;

    private final Integer id;
    private final Integer id_customer;
    private final Integer type;
    private final String contact;

    static {
        table_name = "CONTACTS";
    }

    public static Contact newInstance(Integer id, Integer id_customer, Integer type, String contact) {
        return new Contact(id, id_customer, type, contact);
    }

    private Contact(Integer id, Integer id_customer, Integer type, String contact) {
        this.id = id;
        this.id_customer = id_customer;
        this.type = type;
        this.contact = contact;
    }

    String buildQuery() {
        return SQLBuilder.newInstance()
            .addSQLKeyword(SQLKeyword.INSERT)
            .setTable(table_name)
            .addSQLKeyword(SQLKeyword.VALUES)
            .setValues(id, id_customer, type, contact)
            .buildQuery();
    }
}
