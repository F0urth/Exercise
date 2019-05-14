package ReadData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author F0urth
 */


interface CSVReader extends Reader{

    default void readCSV(String firstLine, BufferedReader reader) {
        read(reader);
    }

    @Override
    default List<String> process(List<String> data) {
        return Collections.emptyList();
    }
}
