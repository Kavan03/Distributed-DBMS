package QueryProcessor;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static QueryProcessor.connection.listFolderStructure;
import static QueryProcessor.connection.making_files;
import static QueryProcessor.validation_use_database.validation_using_database;

public class create_table {
    public static void create_table(String table) {
        table = table.replaceAll("primary key", "pk");
        table = table.replaceAll("foreign key", "fk");
        try {
            String folder = System.getProperty("user.dir");
            File file1= new File(folder+"/"+validation_using_database);
//            System.out.println(file1);
            if(file1.exists()){
                FileWriter fileWriter = new FileWriter(folder + "/" + validation_using_database + "/" + "metadata.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                String[] sp = table.split(" ");
                FileWriter fileWriter1 = new FileWriter(folder + "/" + validation_using_database + "/" + sp[2] + ".txt");
                PrintWriter printWriter2 = new PrintWriter(fileWriter1);
                String new_file = "";
                for (int i = 3; i < sp.length; i++) {
                    String file = sp[i].replaceAll("[^a-zA-Z,0-9_]", " ");
                    new_file = new_file + " " + file;
                }
                String meta_data = new_file.trim();
                meta_data = meta_data.replaceAll("int", "");
                meta_data = meta_data.replaceAll("varchar", "");
                meta_data = meta_data.replaceAll("pk", "");
                meta_data = meta_data.replaceAll("[^a-zA-Z,_]", " ");
                String[] sp2 = meta_data.split(",");
                String file_table = "";
                for (int k = 0; k < sp2.length; k++) {
                    String[] split = sp2[k].split("fk");
                    file_table = file_table + "~" + split[0].trim();
                }
                StringBuilder builder = new StringBuilder(file_table.trim());
                builder = builder.deleteCharAt(0);
//                System.out.println(builder);
                printWriter2.printf(String.valueOf(builder));
                String[] sp1 = new_file.split(",");
                for (int j = 0; j < sp1.length; j++) {
                    printWriter.append((sp[2] + " " + sp1[j].trim()).replaceAll(" ", "~")+"~vm1");
//                    System.out.println((sp[2] + " " + sp1[j].trim()).replaceAll(" ", "~")+"~vm1");
                    printWriter.append("\n");
                }
                printWriter2.append("\n");

                printWriter.flush();
                printWriter2.close();
            }
            else {
                String folder1="cat /home/ubuntu/project";
                if (listFolderStructure("cat /home/ubuntu/project/" + validation_using_database + "/metadata.txt ")) {
//                    System.out.println("Testing123");
                    String[] sp = table.split(" ");

                    making_files("touch "+folder1 + "/" + validation_using_database + "/" + "meta.txt");
                    making_files("touch "+folder1 + "/" + validation_using_database + "/" + sp[2]+".txt");

//                    FileWriter fileWriter = new FileWriter(folder1 + "/" + validation_using_database + "/" + "metadata.txt", true);
//                    PrintWriter printWriter = new PrintWriter(fileWriter);
//                    FileWriter fileWriter1 = new FileWriter(folder1 + "/" + validation_using_database + "/" + sp[2] + ".txt");
//                    PrintWriter printWriter2 = new PrintWriter(fileWriter1);
                    String new_file = "";
                    for (int i = 3; i < sp.length; i++) {
                        String file = sp[i].replaceAll("[^a-zA-Z,0-9_]", " ");
                        new_file = new_file + " " + file;
                    }
                    String meta_data = new_file.trim();
                    meta_data = meta_data.replaceAll("int", "");
                    meta_data = meta_data.replaceAll("varchar", "");
                    meta_data = meta_data.replaceAll("pk", "");
                    meta_data = meta_data.replaceAll("[^a-zA-Z,_]", " ");
                    String[] sp2 = meta_data.split(",");
                    String file_table = "";
                    for (int k = 0; k < sp2.length; k++) {
                        String[] split = sp2[k].split("fk");
                        file_table = file_table + "~" + split[0].trim();
                    }
                    StringBuilder builder = new StringBuilder(file_table.trim());
                    builder = builder.deleteCharAt(0);
                    String[] sp1 = new_file.split(",");
                    for (int j = 0; j < sp1.length; j++) {
                        making_files("echo "+((sp[2] + " " + sp1[j].trim()).replaceAll(" ", "~") + "~vm1")+">>"+"/home/ubuntu/project/"+validation_using_database+"/"+"meta.txt");
//                        System.out.println("echo " +((sp[2] + " " + sp1[j].trim()).replaceAll(" ", "~") + "~vm1")+">"+sp[2]+".txt");

                    }
                    making_files("echo "+String.valueOf(builder)+">>"+"/home/ubuntu/project/"+validation_using_database+"/"+sp[2]+".txt");
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
//        create_table("CREATE TABLE Student (StudentID int primary key, LastName varchar(255), FirstName varchar(255), s_courseid int foreign key courses(course_id);");
        create_table("create table courses (course_id int primary key, coursename varchar(255), courseprof varchar(255));");

    }
}
