import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("usage: exemple.csv");
            System.exit(1);
        }

        try {
            BufferedReader csv = new BufferedReader(new FileReader(args[0]));
            FileWriter out = new FileWriter(args[0].replace(".csv", ".trans"));
            FileWriter dic = new FileWriter(args[0].replace(".csv", ".dico"));
            new Transcoder(csv, out, dic).exec();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
