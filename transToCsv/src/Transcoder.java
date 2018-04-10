import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import twitter4j.Status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Transcoder {
    private CSVPrinter csv;
    private BufferedReader tra, dic;

    Transcoder(CSVPrinter csv, BufferedReader tra, BufferedReader dic) {
        this.csv = csv;
        this.tra = tra;
        this.dic = dic;
    }

    void exec() {
        try{
            Map<Integer, String> dicoList = new HashMap<>();
            String row = "";
            while ((row = dic.readLine()) != null){
                String[] data = row.split(":");
                data[0] = data[0].trim();
                if (data[1].trim().length() != 0){
                    data[1] = data[1].trim();
                } else
                    data[1] = " ";

                dicoList.put(Integer.parseInt(data[0]), data[1]);
            }


            while ((row = tra.readLine()) != null){

                row = row.replace("(", "");
                row = row.replace(")", "");
                String[] data = row.split(" ");
                List<String> tweetData = new ArrayList<>();
                for (String key : data) {
                    tweetData.add(dicoList.get(Integer.parseInt(key)));
                }
                System.out.println(tweetData);
                this.csv.printRecord(tweetData);
                this.csv.flush();
            }

            tra.close();
            dic.close();
            csv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
