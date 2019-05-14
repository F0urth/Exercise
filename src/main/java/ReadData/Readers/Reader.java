package ReadData.Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author F0urth
 */

public
    interface Reader
        extends CSVReader, XMLReader{
    /**
     * Take care of XMLFile
     * @param reader
     */
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

    /**
     * take care of CSVFile
     * @param first
     * @param reader
     */
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
