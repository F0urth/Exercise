package Main;
/**
 * in file README.MD you can see every note
 */


import ReadData.FileReader;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author F0urth
 */


public
    class Main {


    public static AtomicInteger idCustomers = new AtomicInteger(0);
    public static AtomicInteger idContact = new AtomicInteger(0);

    public static void main(String[] args) {
        var reader = FileReader.newInstance();
    }
}
