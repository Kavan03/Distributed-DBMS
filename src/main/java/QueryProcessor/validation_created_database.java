package QueryProcessor;

import Logs.EventLogs;

import java.io.File;
import java.io.IOException;

public class validation_created_database {
    public static Boolean validation_created_database_method(String database_name) throws IOException {
        String first_database[]= database_name.split(" ");
        if(first_database.length<=4){
            File f = new File(first_database[2]);
            if(!f.exists()){
                System.out.println("New Database created successfully");
                return true;
            }
            else {
                EventLogs.enterIntoEventLogs(first_database[2],"Create database","Database exist cannot create database");
                System.out.println("Database exist cannot create database");
                return false;
            }
        }
        else {
            System.out.println("Please enter the correct command");
            return false;
        }
    }
//    public static void main(String args[]) throws IOException {
//        validation_created_database_method("create database DW");
//    }

}
