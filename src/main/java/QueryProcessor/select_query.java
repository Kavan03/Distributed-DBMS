package QueryProcessor;

import Logs.EventLogs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static QueryProcessor.connection.listFolderStructure;
import static QueryProcessor.validation_use_database.validation_using_database;


public class select_query {
    public static void select_query(String select_query) {
        try {
            select_query = select_query.replaceAll("[^a-zA-Z,0-9_*=]", " ");
            String[] sp = select_query.split(" ");
            String folder = System.getProperty("user.dir");
            String filename = folder + "/" + validation_using_database + "/" + sp[3] + ".txt";
            File file1 = new File(folder + "/" + validation_using_database);
            if(validation_using_database.isEmpty()){
                System.out.println("Table no database added");
                EventLogs.enterIntoEventLogs(null,"Database issue","Database not found");
            }
//            System.out.println(file1);
            else if (file1.exists()) {
                if (sp[1].equals("*")) {
//                System.out.println(sp[3]);
                    if (sp.length == 4) {
                        BufferedReader br = new BufferedReader(new FileReader(filename));
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();
                        while (line != null) {
                            sb.append(line + "    ");
                            sb.append("\n");
                            line = br.readLine();
                        }
                        System.out.println(sb.toString().replace("~", " "));
                    } else {
                        List<String> list = new ArrayList<String>();

                        if (sp[4].equals("where")) {
                            BufferedReader br = new BufferedReader(new FileReader(filename));
                            String text;
                            String line = br.readLine();
                            while ((text = br.readLine()) != null) {
                                list.add(text.replace("~", " "));
                            }
                            Integer position = 0;
                            String sp5[] = line.split("~");
                            for (int i = 0; i < sp5.length; i++) {
                                if (sp[5].equals(sp5[i])) {
                                    position = i;
                                }
                            }
//                        System.out.println(position);
//                        System.out.println(sp[7]);
                            for (int i = 0; i < list.size(); i++) {
                                String sp6[] = list.get(i).split(" ");
                                if (sp6[position].equals(sp[7])) {
                                    System.out.println(list.get(i));
                                }
                            }
                        }

                    }

                }

            } else if (!file1.exists()) {
                if (sp[1].equals("*")) {
                    String folder1 = "cat /home/ubuntu/project";
                    listFolderStructure("cat /home/ubuntu/project/" + validation_using_database + "/"+ sp[3]+".txt");
                }

            } else {
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
                    if (sp[5].equals(sp5[i])) {
                        position = i;
                    }
                }
                Integer position_1 = 0;
                for (int i = 0; i < sp5.length; i++) {
                    if (sp[1].equals(sp5[i])) {
                        position_1 = i;
                    }
                }
                for (int i = 0; i < list.size(); i++) {
//                            System.out.println(list.get(i));
                    String sp6[] = list.get(i).split(" ");
//                            System.out.println("Test "+sp6[position]);
//                            System.out.println("Testing "+sp[7]);
                    if (sp6[position].equals(sp[7])) {
                        String sp7[] = list.get(i).split(" ");
//                        System.out.println(list.get(i));
                        System.out.println(sp7[position_1]);
                    }
                }
            }

        } catch (Exception e){
            String a= String.valueOf(1);
        }

}
        public static void main(String args[]) {
        select_query("select * from abc where LastName = Purwar;");
        select_query("select * from abc;");
        select_query("select StudentID from abc where FirstName = Sparsh;");
    }
}
