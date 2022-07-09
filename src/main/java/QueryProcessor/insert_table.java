package QueryProcessor;

import java.io.*;
import java.util.*;

import static QueryProcessor.connection.listFolderStructure;
import static QueryProcessor.connection.making_files;
import static QueryProcessor.validation_use_database.validation_using_database;

public class insert_table {
    public static void insert_table(String table) {
        try {
            Map<String, String> myMap = new HashMap<String, String>();
            String table_name = "";
            String values_query = "";
            String values = "";
            String query = "";
            String[] sp = table.split(" ");
            for (int i = 0; i < sp.length; i++) {
                table_name = sp[2];
                values = sp[3];
                values_query = sp[4];
            }
            if (validation_using_database.isEmpty()) {
                System.out.println("Database not selected, Please select database");
            } else if (!validation_using_database.isEmpty()) {
                String folder = System.getProperty("user.dir");
                String filename = folder + "/" + validation_using_database + "/" + table_name + ".txt";
                File file1 = new File(folder + "/" + validation_using_database);
                if (file1.exists()) {
                    FileWriter fw = new FileWriter(filename, true);
                    if (values.equals("values")) {
                        fw.write(values_query.replaceAll("[^a-zA-Z,0-9_]", "").trim().replaceAll(",", "~"));
                        fw.write("\n");
                    } else {
                        BufferedReader brTest = new BufferedReader(new FileReader(filename));
                        String text = brTest.readLine();
                        List<String> myList = new ArrayList<String>(Arrays.asList(values.replaceAll("[^a-zA-Z,0-9_]", "").trim().replaceAll(",", "~").split("~")));
                        for (int i = 0; i < sp.length; i++) {
                            query = sp[5];
                        }
                        for (int i = 0; i < myList.size(); i++) {
                            String[] words = query.split(",");
                            myMap.put(myList.get(i), words[i].replaceAll("[^a-zA-Z,0-9_]", ""));
                        }
                        String values_file = "";
                        for (int j = 0; j < myMap.size(); j++) {
                            for (String key : myMap.keySet()) {
                                if (key.equals(text.replaceAll("~", ",").split(",")[j])) {
                                    values_file = (values_file + " " + myMap.get(key));
                                    values_file = values_file.trim();
//                                System.out.println(values_file);
                                }
                            }
                        }
                        fw.write(values_file.replaceAll(" ", "~"));
                        fw.write("\n");
                    }

                    fw.close();
                } else {
                    String folder1 = "cat /home/ubuntu/project";
                    if (listFolderStructure("cat /home/ubuntu/project/" + validation_using_database + "/metadata.txt ")) {
                        if (values.equals("values")) {
                            making_files(("echo " + values_query.replaceAll("[^a-zA-Z,0-9_]", "").trim().replaceAll(",", "~")) + ">>" + "/home/ubuntu/project/" + validation_using_database + "/" + sp[2] + ".txt");
                        } else {
                            BufferedReader brTest = new BufferedReader(new FileReader(folder1 + validation_using_database));
                            String text = brTest.readLine();
                            List<String> myList = new ArrayList<String>(Arrays.asList(values.replaceAll("[^a-zA-Z,0-9_]", "").trim().replaceAll(",", "~").split("~")));
                            for (int i = 0; i < sp.length; i++) {
                                query = sp[5];
                            }
                            for (int i = 0; i < myList.size(); i++) {
                                String[] words = query.split(",");
                                myMap.put(myList.get(i), words[i].replaceAll("[^a-zA-Z,0-9_]", ""));
                            }
                            String values_file = "";
                            for (int j = 0; j < myMap.size(); j++) {
                                for (String key : myMap.keySet()) {
                                    if (key.equals(text.replaceAll("~", ",").split(",")[j])) {
                                        values_file = (values_file + " " + myMap.get(key));
                                        values_file = values_file.trim();
//                                    System.out.println(values_file);
                                    }
                                }
                            }
                            making_files(("echo " + values_file.replaceAll(" ", "~") + ">>" + "/home/ubuntu/project/" + validation_using_database + "/" + sp[2] + ".txt"));
                        }
                    }
                }
            } else {
                System.out.println("Issue in the statement please see the command");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        insert_table("insert into courses values (1,Purwar,Sparsh123));");
//        insert_table("insert into students values (1,Purwar,Sparsh,2));");
//        insert_table("insert into courses (StudentID,LastName,FirstName) values (1,Purwar,Sparsh,2);");
//        insert_table("insert into courses (FirstName,s_courseid,StudentID) values (Sparsh1,3,4);");
//
    }
}
