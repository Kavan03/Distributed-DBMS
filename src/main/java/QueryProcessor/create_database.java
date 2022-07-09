package QueryProcessor;

import java.io.File;

public class create_database {
    public static void create_database(String database_name) {
        database_name=database_name.replaceAll("[^a-zA-Z,0-9_]"," ");
        System.out.println(database_name);
        String database_name_split[] = database_name.split(" ");
        try {
            new File(database_name_split[2]).mkdirs();

        } catch (Exception e) {
            System.out.println("Database cannot be created");
        }
    }

    public static void main(String args[]) {
        create_database("create database students;");

    }
}