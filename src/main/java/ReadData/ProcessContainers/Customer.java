package ReadData.ProcessContainers;

import DatabaseHandler.SQLBuilder.SQLBuilder;
import DatabaseHandler.SQLBuilder.SQLKeyword;

import java.util.Optional;

/**
 * Class mediate between read form file date and sql query in String format
 * @author F0urth
 */

public final
    class Customer
        extends Builder{

    private static final String table_name;

    private final Integer id;
    private final String name;
    private final String surname;
    private final Optional<Integer> age;

    static {
        table_name = "CUSTOMERS";
    }

    /**
     * Factory method
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
        this.age = Optional.ofNullable(age);
    }

    /**
     * @return SQL query in String format based of properties of this class
     */

    @Override
    public String buildSQLQuery() {
        return SQLBuilder.newInstance()
            .addSQLKeyword(SQLKeyword.INSERT)
            .setTable(table_name)
            .addSQLKeyword(SQLKeyword.VALUES)
            .setValues(id, name, surname, age.orElse(null))
            .buildQuery();
    }
}
