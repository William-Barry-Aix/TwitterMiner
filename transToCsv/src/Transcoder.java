import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Transcoder {
    private FileWriter csv;
    private BufferedReader tra, dic;
    public Transcoder(FileWriter csv, BufferedReader tra, BufferedReader dic) {
        this.csv = csv;
        this.tra = tra;
        this.dic = dic;
    }

    public void exec() {
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
                List<String> list = Arrays.asList(data);
                Stack<String> stackTmp = new Stack<String>();
                Stack<String> stack = new Stack<String>();
                stackTmp.addAll(list);
                while (!stackTmp.empty()) {
                    stack.push(stackTmp.pop());
                }
                for (int i = 0; !stack.empty(); ++i) {
                    if (i > 0)
                        csv.write(",");
                    csv.write(dicoList.get(Integer.parseInt(stack.pop())));
                }
                csv.write("\n");

            }

            tra.close();
            dic.close();
            csv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
