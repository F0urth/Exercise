package DatabaseHandler.Direct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * DatabaseConnection class
 * its work for local machine database
 * logging as a root
 * without password
 *
 * @author F0urth
 */

public
    enum DatabaseConnection {
    INSTANCE;

    /**
     * Basic dependencies
     */
    private final String DB_URL;
    private final String JDBC_DRIVER;
    private final String user;
    private final String password;
    private ScheduledExecutorService thread;
    private Queue<List<String>> toSaveQueue;

    //Driver and url
    {
        this.user = "root";
        this.password = "";
        this.DB_URL = "jdbc:mysql://localhost:3306/MySql_Database?serverTimezone=UTC";
        this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
        this.thread = Executors
            .newSingleThreadScheduledExecutor();
        this.toSaveQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * adding to ThreadSafe Queue list of queries
     * @param queries
     */

    public void addToQueue(List<String> queries) {
        this.toSaveQueue.add(queries);
    }

    /**
     * Execute at least once per 100 Milliseconds
     * oneIteration Method if the query will take more than
     * 100 milliseconds its gonna wait until finishing
     * TODO Przemyśl czy dobrze że tu scheduleAtFixedRate
     */

    public void start() {
        thread.scheduleAtFixedRate(
            this::oneIteration,
            1000, 100, TimeUnit.MILLISECONDS
        );
    }

    /**
     * connect to database execute one big query close connection
     */
    private void oneIteration() {
        if (!this.toSaveQueue.isEmpty()) {
            try {
                Class.forName(JDBC_DRIVER);
                //connection setup
                try (Connection connection = DriverManager.getConnection(DB_URL, user, password)) {
                    Statement statement = connection.createStatement();
                    //var query = String.join("\n", Objects.requireNonNull(this.toSaveQueue.poll()));
                    Objects.requireNonNull(this.toSaveQueue.poll()).forEach(
                        sql -> {
                            try {
                                statement.execute(sql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                    });
                } catch (SQLException sqlEx) {
                    for (var ex : sqlEx) ex.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Insert Data");
        }
    }

    public boolean isReady() {
        return this.toSaveQueue.size() < 3;
    }
}
