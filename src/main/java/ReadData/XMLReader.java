package ReadData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author F0urth
 */


interface XMLReader extends Reader{
    default void readXML(BufferedReader reader) {
        read(reader);
    }

    @Override
    default List<String> process(List<String> data) {

        return Collections.emptyList();
    }
}
