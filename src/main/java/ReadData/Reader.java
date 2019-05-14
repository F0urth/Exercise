package ReadData;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface Reader {

    default void read(BufferedReader reader) {
        var args = new ArrayList<String>();
        try (reader) {
            String line;
            while ((line = reader.readLine()) != null){
                args.add(line);
                if (args.size() > 10000) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<String> process(List<String> data);
}
