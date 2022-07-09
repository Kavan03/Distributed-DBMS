package QueryProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static QueryProcessor.validation_use_database.validation_using_database;

public class update_query {
    public static void update_query(String update_query) throws IOException {
        update_query=update_query.replaceAll("[^a-zA-Z,0-9_*=]", " ");

        System.out.println(update_query);
        String[] sp = update_query.split(" ");
        String folder = System.getProperty("user.dir");
        String filename = folder + "/" + validation_using_database + "/" + sp[1] + ".txt";
        if (sp[0].equals("update")) {
            List<String> list = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String text;
            String line = br.readLine();
            while ((text = br.readLine()) != null) {
                list.add(text.replace("~", " "));
            }
            Integer position = 0;
            String sp5[] = line.split("~");
            for (int i = 0; i < sp5.length; i++) {
                if (sp[7].equals(sp5[i])) {
                    position = i;
                }
            }
            String result="";
            StringBuffer sb = new StringBuffer();
            result=String.valueOf(sb.append(line+"\n"));
            Integer position_1=0;
            for (int i = 0; i < sp5.length; i++) {
                if (sp[3].equals(sp5[i])) {
                    position_1 = i;
                }
            }
//            System.out.println(position);
//            System.out.println(position_1);
//            System.out.println(line);
//            System.out.println(list);
            for (int i = 0; i < list.size(); i++) {
                String sp6[] = list.get(i).split(" ");
//                System.out.println(sp6[position]);
//                System.out.println(sp[9]);
                if (sp6[position].equals(sp[9])) {
                    String sp7[] = list.get(i).split(" ");
//                    System.out.println(sp7[position_1]);
//                    System.out.println(list.get(i).replace(sp7[position_1],sp[5]));
                    result=String.valueOf(sb.append(list.get(i).replace(sp7[position_1],sp[5])+"\n"));
                }
                else {
                    result=String.valueOf(sb.append(list.get(i)+"\n"));
                }
            }
            System.out.println(result);
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.append(result);
            writer.flush();
        }
    }

    public static void main(String args[]) throws IOException {
        update_query("update abc set LastName = Purwar123 where StudentID = 2;");
    }
}
