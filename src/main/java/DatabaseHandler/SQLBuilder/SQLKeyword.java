package DatabaseHandler.SQLBuilder;

/**
 * @author F0urth
 * Enum contains basic SQL Syntax created to work with
 * @see DatabaseHandler.SQLBuilder.SQLBuilder
 */
public enum SQLKeyword {

    INSERT("INSERT INTO "),
    UPDATE("UPDATE "),
    DELETE("DELETE FROM "),
    SET(" SET "),
    WHERE(" WHERE "),
    VALUES(" VALUES "),
    SELECT("SELECT ");

    private String command;


    SQLKeyword(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
