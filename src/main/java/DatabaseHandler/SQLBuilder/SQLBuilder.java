package DatabaseHandler.SQLBuilder;

/**
 * @author F0urth
 * @see DatabaseHandler.SQLBuilder.SQLKeyword
 */
public class SQLBuilder {

    private StringBuilder query;

    /**
     * Strings that often presents
     * created for optimization
     */
    private static final class Consts {
        private static final String COMMA = ", ";
        private static final String LEFT_BRACKET = " ( ";
        private static final String RIGHT_BRACKET = " ) ";
        private static final String SEMICOLON = ";";
        private static final String APOSTROPHE = "'";
    }

    /**
     * Factory Method
     *
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
     *
     * @param type
     * @return this
     */
    public SQLBuilder addSQLKeyword(SQLKeyword type) {
        this.query.append(type.getCommand());
        return this;
    }

    /**
     * Adding table name to the query
     *
     * @param tableName
     * @return this
     */
    public SQLBuilder setTable(String tableName) {
        query.append(tableName);
        return this;
    }

    /**
     * Adding Columns to the query
     *
     * @param first
     * @param columns
     * @return this
     */

    public SQLBuilder setColumns(String first, String... columns) {
        this.query.append(Consts.LEFT_BRACKET).append(first);
        for (var column : columns) {
            this.query.append(Consts.COMMA).append(column);
        }
        this.query.append(Consts.RIGHT_BRACKET);
        return this;
    }

    /**
     * Adding Values to the Query
     *
     * @param first
     * @param values
     * @return this
     */
    public SQLBuilder setValues(Object first, Object... values) {
        this.query.append(Consts.LEFT_BRACKET).append(setValuesTypeChecker(first));
        for (var val : values) {
            this.query.append(Consts.COMMA).append(setValuesTypeChecker(val));
        }
        this.query.append(Consts.RIGHT_BRACKET).append(Consts.SEMICOLON);
        return this;
    }

    /**
     * Help method => adding ' ' to the string class values
     *
     * @param val
     * @return val or modify val
     */
    private Object setValuesTypeChecker(Object val) {
        if (!(val instanceof String)) {
            return val;
        } else {
            return Consts.APOSTROPHE + val + Consts.APOSTROPHE;
        }
    }

    /**
     * @return query in String format
     */
    public String buildQuery() {
        return this.query.toString();
    }

}