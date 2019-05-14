package ReadData.ProcessContainers;

import DatabaseHandler.SQLBuilder.SQLBuilder;
import DatabaseHandler.SQLBuilder.SQLKeyword;

import java.util.regex.Pattern;


/**
 * Class mediate between read form file date and sql query in String format
 * @author F0urth
 */

public final
    class Contact {

    private static final String table_name;
    private static final Pattern emailPattern;

    private final Integer id;
    private final Integer id_customer;
    private final Integer type;
    private final String contact;

    static {
        table_name = "CONTACTS";

        /*
         * RFC 5322 Official Standard
         * if we like to simplify this we can write simple \S+@\S+
         * and for example send veryfication email
         */
        emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f]" +
            ")*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
            "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c" +
            "\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    /**
     * Factory method
     * @param id
     * @param id_customer
     * @param type
     * @param contact
     * @return new Contact class easy changable to sql query in String format
     */
    public static Contact newInstance(Integer id, Integer id_customer, Integer type, String contact) {
        return new Contact(id, id_customer, type, contact);
    }

    private Contact(Integer id, Integer id_customer, Integer type, String contact) {
        this.id = id;
        this.id_customer = id_customer;
        this.type = type;
        this.contact = contact;
    }

    /**
     *
     * @return SQL query in String format based of properties of this class
     */
    String buildQuery() {
        return SQLBuilder.newInstance()
            .addSQLKeyword(SQLKeyword.INSERT)
            .setTable(table_name)
            .addSQLKeyword(SQLKeyword.VALUES)
            .setValues(id, id_customer, type, contact)
            .buildQuery();
    }

    /**
     * Checking Type of the Contact
     * @param contact
     * @return Type of the Contact (integer - 0 - unknown, 1 - email, 2 - phone, 3- jabber)
     */
    public static Integer getContactType(String contact) {
        String trim = contact.trim();
        if (trim.isEmpty())
            return 0;
        else if (emailPattern.matcher(contact).matches())
            return 1;
        else if (trim.matches("[0-9]+"))
            return 2;
        else
            return 3;

    }
}
