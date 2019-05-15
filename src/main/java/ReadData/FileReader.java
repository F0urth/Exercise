package ReadData;

import DatabaseHandler.Controler;
import ReadData.Readers.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @author F0urth
 */

public
    class FileReader
        implements Reader {

    private BufferedReader reader;
    private Executor service;

    public static FileReader newInstance() {
        return new FileReader();
    }

    private FileReader() {
        this.service = Executors
            .newFixedThreadPool(5);
    }

    public void read(String path) {
        if (path.endsWith(".xml")){
            System.out.println("Deceted XML");
            Controler.INSTANCE
                .addToRunnables(() -> readXML(path));
        } else {
            System.out.println("DECETED CSV");
            try {
                this.reader = new BufferedReader(new java.io.FileReader(path, StandardCharsets.UTF_8));
                Controler.INSTANCE
                    .addToRunnables(() -> read(reader));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(Runnable task) {
        System.out.println("Start executing");
        this.service.execute(task);
    }
}
