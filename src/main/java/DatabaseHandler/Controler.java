package DatabaseHandler;

import ReadData.FileReader;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
        this.database = Database.getInstance();
        this.reader = FileReader.getInstance();
        this.runnables = new ConcurrentLinkedQueue<>();
    }

    public void addToRunnables(Runnable task) {
        this.runnables.add(task);
    }

    public Runnable getTask() {
        return this.runnables.poll();
    }

    public void read(String path) {
        this.reader.read(path);
    }


}
