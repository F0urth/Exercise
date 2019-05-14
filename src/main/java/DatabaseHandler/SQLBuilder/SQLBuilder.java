package DatabaseHandler.SQLBuilder;

/**
 * @author F0urth
 * @see DatabaseHandler.SQLBuilder.SQLKeyword
 * @see DatabaseHandler.SQLBuilder.CommandGetter
 */
public
    class SQLBuilder {

    private StringBuilder query;

    /**
     * Strings that often presents
     * created for optimization
     */
    private static final String comma = ", ";
    private static final String leftBracket = " ( ";
    private static final String rightBracket = " ) ";
    private static final String semicolon = ";";
    private static final String apostrophe = "'";

    /**
     *  Factory Method
     * @return SQLBuilder instance
     */
    public static SQLBuilder newInstance() {
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
        this.query.append(leftBracket).append(first);
        for (var column : columns) this.query.append(comma).append(column);
        this.query.append(rightBracket);
        return this;
    }

    /**
     * Adding Values to the Query
     * @param first
     * @param values
     * @return this
     */
    public SQLBuilder setValues(Object first, Object... values) {
        this.query.append(leftBracket).append(setValuesTypeChecker(first));
        for (var val : values) this.query.append(comma).append(setValuesTypeChecker(val));
        this.query.append(rightBracket).append(semicolon);
        return this;
    }

    /**
     * Help method => adding ' ' to the string class values
     * @param val
     * @return val or modify val
     */
    private Object setValuesTypeChecker(Object val) {
        if (val == null || !val.getClass().getName().contains("String")) return val;
        else return apostrophe + val + apostrophe;
    }

    /**
     * @return query in String format
     */
    public String buildQuery() {
        return this.query.toString();
    }
}