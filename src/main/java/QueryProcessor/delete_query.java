package QueryProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static QueryProcessor.validation_use_database.validation_using_database;

public class delete_query {
    public static void delete_query(String delete_query) throws IOException {
        delete_query=delete_query.replaceAll("[^a-zA-Z,0-9_*=]", " ");
        String[] sp = delete_query.split(" ");
        String folder= System.getProperty("user.dir");
        String filename= folder+"/"+validation_using_database+"/"+sp[2]+".txt";
        if(sp[3].equals("where")){
            List<String> list=new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String text;
            String line = br.readLine();
            while ((text = br.readLine()) != null) {
                list.add(text.replace("~", " "));
            }
            Integer position=0;
            String sp5[]= line.split("~");
            for(int i=0;i< sp5.length;i++){
                if(sp[4].equals(sp5[i])){
                    position=i;
                }
            }
            String result="";
            StringBuffer sb = new StringBuffer();
            result=String.valueOf(sb.append(line+"\n"));

            for (int i = 0; i < list.size(); i++) {
                String sp6[] = list.get(i).split(" ");

                if(!sp6[position].equals(sp[6])){
                    String sp7[]=list.get(i).split(" ");
//                    System.out.println(list.get(i));
                    result=String.valueOf(sb.append(list.get(i)+"\n"));
                }
            }
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.append(result);
            writer.flush();

        }
    }

    public static void main(String args[]) throws IOException {
        delete_query("delete from abc where FirstName = Sparsh1;");
//        Edge cases failing
    }
}
