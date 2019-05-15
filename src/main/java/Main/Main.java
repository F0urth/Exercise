package Main;
/**
 * in file README.MD you can see every note
 */


import DatabaseHandler.Controler;
import ReadData.FileReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author F0urth
 */


public
    class Main {


    public static AtomicInteger idCustomers = new AtomicInteger(0);
    public static AtomicInteger idContact = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        //data - start directory in project
        Files.walk(Paths.get("data"))
            .filter(path -> path.toString().contains("dane-osoby"))
            .filter(path -> !path.toFile().isDirectory())
            .map(Path::toString)
            .forEach(Controler.INSTANCE::read);


        Executors.newSingleThreadExecutor()
            .execute(
                () -> {
                    var jframe = new JFrame("If you feel danger you can stop at any time");
                    var safetyButton = new JButton("Safety Button");
                    safetyButton.setBackground(Color.RED);
                    jframe.setSize(
                        new Dimension(400,400));
                    jframe.add(safetyButton);
                    safetyButton.addActionListener(
                        l -> System.exit(1337));
                    jframe.setVisible(true);
                    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            );
    }
}
