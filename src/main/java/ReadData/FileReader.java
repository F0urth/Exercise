package ReadData;

import DatabaseHandler.Controler;
import ReadData.Readers.Reader;

import java.io.BufferedReader;
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
        try {
            reader = new BufferedReader(
                new java.io.FileReader(path));
            String firstLine;
            if ((firstLine = reader.readLine()).contains("xml"))
                Controler.INSTANCE.addToRunnables(
                    () -> read(reader));
            else
                Controler.INSTANCE.addToRunnables(
                    () -> read(firstLine, reader));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
