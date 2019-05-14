package ReadData.Readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author F0urth
 */

interface XMLReader {

    default List<String> processXML(List<String> data) {
        return null;
    }
}
