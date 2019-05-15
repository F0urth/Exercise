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
    static Database newInstance() {
        return new Database();
    }

    private Database() {
        this.connection = DatabaseConnection.INSTANCE;
    }

    /**
     * Delegate method
     * @param queries
     */
    void insertQueries(List<String> queries) {
        this.connection.start();
        this.connection.addToQueue(queries);
        System.out.println("Gonna insert queries");
        queries.forEach(System.out::println);
    }

    boolean isReady() {
        return this.connection.isReady();
    }

    //---
}
