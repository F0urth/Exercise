package DatabaseHandler;

import DatabaseHandler.Direct.DatabaseConnection;


/**
 * @author F0urth
 */

class Database {

    private DatabaseConnection connection;

    static Database getInstance() {
        return new Database();
    }

    private Database() {
        this.connection = DatabaseConnection.INSTANCE;
    }
}
