package DatabaseHandler;

import DatabaseHandler.Direct.DatabaseConnection;
import java.util.List;

/**
 * Class created to upper abstraction of database
 * Its just delegetor to
 * @see DatabaseHandler.Direct.DatabaseConnection
 * @author F0urth
 */

class Database {

    private DatabaseConnection connection;

    /**
     * Factory method
     * @return instance of the class
     */
    static Database getInstance() {
        return new Database();
    }

    private Database() {
        this.connection = DatabaseConnection.INSTANCE;
    }

    /**
     * Delegate method
     * @param queries
     */
    public void insertQueries(List<String> queries) {
        this.connection.addToQueue(queries);
    }

    //---
}
