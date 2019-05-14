package DatabaseHandler;

import ReadData.Read;

import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author F0urth
 */

public
    enum Controler {

    INSTANCE;

    private Database database;
    private Read reader;
    private Queue<Runnable> runnables;

    {
        this.database = Database.getInstance();
        this.reader = Read.getInstance();
        this.runnables = new ConcurrentLinkedQueue<>();
    }

    public void addToRunnables(Runnable task) {
        this.runnables.add(task);
    }

    public void setReader(String path) throws FileNotFoundException {

    }


}
