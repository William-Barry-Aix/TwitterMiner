import java.io.*;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("usage: exemple.out 200");
            System.exit(1);
        }
        try{
            int minconf = Integer.parseInt(args[1]);
            BufferedReader out = new BufferedReader(new FileReader(args[0]));
            FileWriter freq = new FileWriter(args[0].replace(".out", ".freq"));
            new Transcoder(out, freq, minconf).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
