package ReadData.ProcessContainers;

import DatabaseHandler.SQLBuilder.SQLBuilder;
import DatabaseHandler.SQLBuilder.SQLKeyword;

import java.util.regex.Pattern;


/**
 * Class mediate between read form file date and sql query in String format
 *
 * @author F0urth
 */

public final class Contact extends Builder {

    private static final String TABLE_NAME = "CONTACTS";

    /*
     * RFC 5322 Official Standard
     * if we like to simplify this we can write simple `\S+@\S+`
     * and for example send veryfication email
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
        "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f]" +
        ")*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
        "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c" +
        "\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    private final Integer id;
    private final Integer idCustomer;
    private final ContactType type;
    private final String contact;

    /**
     * Factory method
     *
     * @param id
     * @param id_customer
     * @param type
     * @param contact
     * @return new Contact class easy changable to sql query in String format
     */
    public static Contact newInstance(Integer id, Integer id_customer, ContactType type, String contact) {
        return new Contact(id, id_customer, type, contact);
    }

    private Contact(Integer id, Integer idCustomer, ContactType type, String contact) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.type = type;
        this.contact = contact;
    }

    /**
     * @return SQL query in String format based of properties of this class
     */

    @Override
    public String buildSQLQuery() {
        return SQLBuilder.newInstance()
            .addSQLKeyword(SQLKeyword.INSERT)
            .setTable(TABLE_NAME)
            .addSQLKeyword(SQLKeyword.VALUES)
            .setValues(id, idCustomer, type.ordinal(), contact)
            .buildQuery();
    }

    /**
     * Checking Type of the Contact
     *
     * @param contact
     * @return Type of the Contact (integer - 0 - unknown, 1 - email, 2 - phone, 3- jabber)
     */
    public static ContactType getContactType(String contact) {
        String trim = contact.trim();
        if (trim.isEmpty()) {
            return ContactType.UNKNOWN;
        } else if (EMAIL_PATTERN.matcher(contact).matches()) {
            return ContactType.EMAIL;
        } else if (trim.matches("[0-9]+") && trim.length() == 9) {
            return ContactType.PHONE;
        } else {
            return ContactType.JABBER;
        }
    }

    public enum ContactType {
        UNKNOWN,
        EMAIL,
        PHONE,
        JABBER
    }

    /**
     * Situational Creator for class
     *
     * @author F0urth
     * @see Contact
     */
    public static class ContactBuilder {

        private Integer id;
        private Integer id_customer;
        private ContactType type;
        private String contact;

        public void setId(Integer id) {
            this.id = id;
        }

        public void setId_customer(Integer id_customer) {
            this.id_customer = id_customer;
        }

        public void setType(ContactType type) {
            this.type = type;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        /**
         * @return new of instance Contact
         * @see Contact
         */
        public Contact buildContact() {
            return newInstance(id, id_customer, type, contact);
        }
    }
}
