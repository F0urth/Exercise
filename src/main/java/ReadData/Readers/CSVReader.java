package ReadData.Readers;


import java.util.List;

/**
 * @author F0urth
 */

interface CSVReader {

    default List<String> processCSV(List<String> data) {

        return null;
    }
}
