package DatabaseHandler;

import DatabaseHandler.Direct.DatabaseConnection;

import java.util.List;

/**
 * Class created to upper abstraction of database
 * Its just delegetor to
 *
 * @author F0urth
 * @see DatabaseHandler.Direct.DatabaseConnection
 */

class Database {

    private DatabaseConnection connection;

    static Database newInstance() {
        return new Database();
    }

    private Database() {
        this.connection = DatabaseConnection.INSTANCE;
    }

    void insertQueries(List<String> queries) {
        this.connection.start();
        this.connection.addToQueue(queries);
        System.out.println("Gonna insert queries");
        queries.forEach(System.out::println);
    }

    boolean isReady() {
        return this.connection.isReady();
    }

}
