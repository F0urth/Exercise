package DatabaseHandler;

import ReadData.FileReader;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author F0urth
 */

public
    enum Controler {

    INSTANCE;

    private Database database;
    private FileReader reader;
    private Queue<Runnable> runnables;

    {
        this.database = Database.newInstance();
        this.reader = FileReader.newInstance();
        this.runnables = new ConcurrentLinkedQueue<>();
    }

    public void addToRunnables(Runnable task) {
        System.out.println("ADDED to runnable queue");
        this.runnables.add(task);
        start();
    }

    public Runnable getTask() {
        return this.runnables.poll();
    }

    public void read(String path) {
        this.reader.read(path);
    }

    public void insertQueries(List<String> queries) {
        database.insertQueries(queries);
    }

    public boolean isDBReady() {
        return this.database.isReady();
    }

    private void start() {
        Executors.newSingleThreadScheduledExecutor()
            .scheduleAtFixedRate(
                this::oneIteration,
                50, 50, TimeUnit.MILLISECONDS
            );
    }

    private void oneIteration() {
        if (!this.runnables.isEmpty()) {
            this.reader
                .execute(getTask());
        }
    }
}
