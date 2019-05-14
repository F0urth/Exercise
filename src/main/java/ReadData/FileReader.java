package ReadData;

import DatabaseHandler.Controler;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @author F0urth
 */
public
    class FileReader
        implements XMLReader, CSVReader {

    private BufferedReader reader;
    private Executor service;

    public static FileReader getInstance() {
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
                    () -> readXML(reader));
            else
                Controler.INSTANCE.addToRunnables(
                    () -> readCSV(firstLine, reader));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
