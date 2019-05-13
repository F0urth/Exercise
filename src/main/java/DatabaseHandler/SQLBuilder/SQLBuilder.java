package DatabaseHandler.SQLBuilder;

/**
 * @author F0urth
 */
public
    class SQLBuilder{

    private StringBuilder query;

    /**
     *  Factory Method
     * @return SQLBuilder instance
     */
    public static SQLBuilder getInstance() {
        return new SQLBuilder();
    }

    private SQLBuilder() {
        this.query = new StringBuilder();
    }

    /**
     * Adding SQL keyword to the query
     * @param type
     * @return this
     */
    public SQLBuilder addSQLKeyword(SQLKeyword type) {
        this.query.append(type.getCommand());
        return this;
    }

    /**
     *  Adding table name to the query
     * @param tableName
     * @return this
     */
    public SQLBuilder setTable(String tableName) {
        query.append(tableName);
        return this;
    }

    /**
     * Adding Columns to the query
     * @param first
     * @param columns
     * @return this
     */

    public SQLBuilder setColumns(String first, String... columns) {
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
    public SQLBuilder setValues(Object first, Object... values) {
        this.query.append(" (").append(setValuesTypeChecker(first));
        for (var val:values) this.query.append(", ").append(val);
        this.query.append("); ");
        return this;
    }

    /**
     * Help method => adding ' ' to the string class values
     * @param val
     * @return val or modify val
     */
    private Object setValuesTypeChecker(Object val) {
        if (val.getClass().getName().contains("String")) return "'" + val + "'";
        else return val;
    }

    /**
     * @return query in String format
     */
    public String buildQuery() {
        return this.query.toString();
    }
}