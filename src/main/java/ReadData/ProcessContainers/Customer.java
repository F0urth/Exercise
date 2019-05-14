package ReadData.ProcessContainers;

import DatabaseHandler.SQLBuilder.SQLBuilder;
import DatabaseHandler.SQLBuilder.SQLKeyword;

public
    class Customer {

    private static final String table_name;

    private Integer id;
    private String name;
    private String surname;
    private Integer age;

    static {
        table_name = "CUSTOMERS";
    }

    public static Customer newInstance(Integer id, String name, String surname, Integer age) {
        return new Customer(id, name, surname, age);
    }

    private Customer(Integer id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    String buildSQLQuery() {
        return SQLBuilder.newInstance()
            .addSQLKeyword(SQLKeyword.INSERT)
            .setTable(table_name)
            .addSQLKeyword(SQLKeyword.VALUES)
            .setValues(id, name, surname, age)
            .buildQuery();
    }
}
