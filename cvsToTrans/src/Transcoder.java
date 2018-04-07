import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Transcoder {
    private BufferedReader csv;
    private FileWriter out, dic;
    Transcoder(BufferedReader csv, FileWriter out, FileWriter dic){
        this.csv = csv;
        this.out = out;
        this.dic = dic;
    }
    void exec(){
        try {
            Map<String, Integer> dicoList = new HashMap<>();
            String row;
            int i = 0;
            System.out.println("Ecriture de la sortie...");
            while ((row = csv.readLine()) != null){
                for (String val :row.split(",")) {
                    if (!dicoList.containsKey(val)){
                        dicoList.put(val, i);
                        ++i;
                    }

                        out.write(dicoList.get(val) + " ");

                }
                out.write("\n");
            }
            System.out.println("OK!");
            for (String key : dicoList.keySet()) {
                dic.write(dicoList.get(key) + " : " + key + "\n");
            }
            csv.close();
            out.close();
            dic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
