package ReadData;

import DatabaseHandler.Controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @author F0urth
 */
public
    class Read
        implements XMLReader, CSVReader {

    private BufferedReader reader;
    private Executor service;

    public static Read getInstance() {
        return new Read();
    }

    private Read() {
        this.service = Executors
            .newFixedThreadPool(5);
    }

    public void read(String path) {

        try {
            reader = new BufferedReader(
                new FileReader(path));
            String firstLine;
            if ((firstLine = reader.readLine()).contains("xml"))
                Controler.INSTANCE.addToRunnables(
                    () -> readXML(reader));
            else
                Controler.INSTANCE.addToRunnables(
                    () -> readCSV(firstLine, reader));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
