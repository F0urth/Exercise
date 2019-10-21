package DatabaseHandler.Direct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * DatabaseConnection class
 * its work for local machine database
 * logging as a root
 * without password
 *
 * @author F0urth
 */

public enum DatabaseConnection {
    INSTANCE;


    public static final Integer BATCH_SIZE = 3;
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
    DatabaseConnection() {
        Properties prop = new Properties();
        try {
            FileInputStream props = new FileInputStream("../resources/config.properties");
            prop.load(props);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't read config file");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load config file");
        }
        this.user = prop.getProperty("db.user");
        this.password = prop.getProperty("db.password");
        this.DB_URL = prop.getProperty("db.url");
        this.JDBC_DRIVER = prop.getProperty("db.jdbc.driver");
        this.thread = Executors.newSingleThreadScheduledExecutor();
        this.toSaveQueue = new ConcurrentLinkedQueue<>();
    }

    public void addToQueue(List<String> queries) {
        this.toSaveQueue.add(queries);
    }

    /**
     * Execute at least once per 100 Milliseconds
     * oneIteration Method if the query will take more than
     * 100 milliseconds its gonna wait until finishing
     */

    public void start() {
        thread.scheduleAtFixedRate(
            this::oneIteration,
            1000, 100, TimeUnit.MILLISECONDS);
    }

    /**
     * connect to database execute one big query close connection
     */
    private void oneIteration() {
        if (!this.toSaveQueue.isEmpty()) {
            try {
                Class.forName(JDBC_DRIVER);
                try (Connection connection = DriverManager.getConnection(DB_URL, user, password)) {
                    Statement statement = connection.createStatement();
                    Objects.requireNonNull(this.toSaveQueue.poll()).forEach(
                        sql -> {
                            try {
                                statement.execute(sql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                } catch (SQLException sqlEx) {
                    for (var ex : sqlEx) {
                        ex.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Insert Data");
        }
    }

    /**
     * @return readiness of database
     */
    public boolean isReady() {
        return this.toSaveQueue.size() < BATCH_SIZE;
    }
}
