package ReadData.Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public
    interface Reader
        extends CSVReader, XMLReader{

    default void read(BufferedReader reader) {
        var args = new ArrayList<String>();
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null){
                args.add(line);
                if (args.size() > 10000) {
                    processXML(args);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    default void read(String first, BufferedReader reader) {
        var args = new ArrayList<String>();
        args.add(first);
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null){
                args.add(line);
                if (args.size() > 10000) {
                    processCSV(args);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
