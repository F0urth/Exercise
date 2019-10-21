package ReadData.ProcessContainers;

import DatabaseHandler.SQLBuilder.SQLBuilder;
import DatabaseHandler.SQLBuilder.SQLKeyword;

/**
 * Class mediate between read form file date and sql query in String format
 *
 * @author F0urth
 */

public final class Customer extends Builder {

    private static final String TABLE_NAME = "CUSTOMERS";

    private final Integer id;
    private final String name;
    private final String surname;
    private final Integer age;

    /**
     * Factory method
     *
     * @param id
     * @param name
     * @param surname
     * @param age
     * @return new instance of Customer which gonna be translate into sql query in String format
     */
    public static Customer newInstance(Integer id, String name, String surname, Integer age) {
        return new Customer(id, name, surname, age);
    }

    private Customer(Integer id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
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
            .setValues(id, name, surname, age)
            .buildQuery();
    }

    /**
     * Class is situational creator for class
     *
     * @author F0urth
     * @see Customer
     */

    public static class CustomerBuilder {

        private Integer id;
        private String name;
        private String surname;
        private Integer age;

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        /**
         * @return new Instance of
         * @see Customer
         */
        public Customer buildCustomer() {
            return newInstance(id, name, surname, age);
        }
    }
}
