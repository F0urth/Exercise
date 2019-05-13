package ReadData;

import java.io.BufferedReader;

/**
 * @author F0urth
 */


interface CSVReader {
    default void readCSV(String firstLine, BufferedReader reader) {

    }
}
