import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Transcoder {
    private int minConf;
    private BufferedReader out;
    private FileWriter freq;

    Transcoder(BufferedReader out, FileWriter freq, int minConf){
        this.out = out;
        this.freq = freq;
        this.minConf = minConf;
    }

    void run(){
        String row;
        try{
            while ((row = out.readLine())!= null) {
                String newRow;
                newRow = row.replace("(", "");
                newRow = newRow.replace(")", "");
                String[] data = newRow.split(" ");
                int val = Integer.parseInt(data[data.length-1]);
                if (val >= minConf){
                    System.out.println(row);
                    freq.write(row + "\n");
                }
            }
            freq.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
