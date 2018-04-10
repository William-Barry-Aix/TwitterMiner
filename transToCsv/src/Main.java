import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("usage: exemple.trans exemple.dico");
            System.exit(1);
        }

        try {
            BufferedReader tra = new BufferedReader(new FileReader(args[0]));
            BufferedReader dic = new BufferedReader(new FileReader(args[1]));
            String outputCSVFileName = args[0].replace(".trans", ".csv");
            FileWriter writer = new FileWriter(outputCSVFileName, false);
            CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT);
            new Transcoder(csv, tra, dic).exec();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
