package ReadData;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author F0urth
 */


interface XMLReader {
    default void readXML(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null){
            //TODO
        }
    }
}
