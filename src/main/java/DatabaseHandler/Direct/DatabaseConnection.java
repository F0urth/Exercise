package DatabaseHandler.Direct;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author F0urth
 */

public
    enum DatabaseConnection {
    INSTANCE;

    private final String DB_URL;
    private final String JDBC_DRIVER;
    private Executor thread;

    //Driver and url
    {
        this.DB_URL = "jdbc:mysql://localhost:3306/MySql_Database?serverTimezone=UTC";
        this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
        this.thread = Executors
            .newSingleThreadExecutor();
    }



}
