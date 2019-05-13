package DatabaseHandler.SQLBuilder;

public
    class SQLBuilder{

    private StringBuilder query;

    private SQLBuilder() {
        this.query = new StringBuilder();
    }

    /**
     * Adding SQL keyword to the query
     * @param type
     * @return this
     */
    private SQLBuilder addSQLKeyword(SQLKeyword type) {
        this.query.append(type.getCommand());
        return this;
    }

    /**
     *  Adding table name to the query
     * @param tableName
     * @return this
     */
    private SQLBuilder setTable(String tableName) {
        query.append(tableName);
        return this;
    }

    /**
     * Adding Columns to the query
     * @param first
     * @param columns
     * @return this
     */

    private SQLBuilder setColumns(String first, String... columns) {
        this.query.append(" (").append(first);
        for (String column : columns) this.query.append(", ").append(column);
        this.query.append(") ");
        return this;
    }

    /**
     * Adding Values to the Query
     * @param first
     * @param values
     * @return this
     */
    private SQLBuilder setValues(Object first, Object... values) {
        this.query.append(" (").append(setValuesTypeChecker(first));
        for (var val:values) this.query.append(", ").append(val);
        this.query.append("); ");
        return this;
    }

    // ADDING ' ' to string values
    private Object setValuesTypeChecker(Object val) {
        if (val.getClass().getName().contains("String")) return "'" + val + "'";
        else return val;
    }

    /**
     * @return query in String format
     */
    private String buildQuery() {
        return this.query.toString();
    }
}