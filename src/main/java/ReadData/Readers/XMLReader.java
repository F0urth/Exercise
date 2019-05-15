package ReadData.Readers;

import ReadData.ProcessContainers.Builder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface to change 'Class type data' into SQL queries
 * @author F0urth
 */

interface XMLReader {

    default List<String> processXML(List<Builder> data) {
        return data.stream()
            .map(Builder::buildSQLQuery)
            .collect(Collectors.toList());
    }
}
