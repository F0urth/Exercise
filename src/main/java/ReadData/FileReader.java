package ReadData;

import DatabaseHandler.Controller;
import ReadData.Readers.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @author F0urth
 */

public final
    class FileReader
        implements Reader {

    private BufferedReader reader;
    private Executor service;

    /**
     * Factory method
     * @return new Instance
     */
    public static FileReader newInstance() {
        return new FileReader();
    }

    private FileReader() {
        this.service = Executors
            .newFixedThreadPool(5);
    }

    /**
     * detected the type of file and make it runnable
     * @param path
     */

    public void read(String path) {
        if (path.endsWith(".xml")){
            System.out.println("Detected XML");
            Controller.INSTANCE
                .addToRunnables(() -> readXML(path));
        } else {
            System.out.println("Detected CSV");
            try {
                this.reader = new BufferedReader(new java.io.FileReader(path, StandardCharsets.UTF_8));
                Controller.INSTANCE
                    .addToRunnables(() -> read(reader));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * execute a 'read' task in own service
     * @param task
     */
    public void execute(Runnable task) {
        System.out.println("Start executing");
        this.service.execute(task);
    }
}
