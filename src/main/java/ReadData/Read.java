package ReadData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author F0urth
 */
public
    class Read
        implements XMLReader, CSVReader{

    private BufferedReader reader;

    public static Read getInstance(String path) throws FileNotFoundException {
        return new Read(path);
    }

    private Read(String path) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(path));
    }

    public void read() throws IOException {
        String firstLine;
        if ((firstLine = reader.readLine()).contains("xml"))
            readXML(reader);
        else 
            readCSV(firstLine, reader);
    }
}
