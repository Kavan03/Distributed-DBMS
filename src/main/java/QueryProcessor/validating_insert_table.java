package QueryProcessor;

import Logs.EventLogs;

import java.io.File;
import java.io.IOException;

import static QueryProcessor.validation_use_database.validation_using_database;

public class validating_insert_table {
    public static Boolean validating_insert_table(String insert_table) throws IOException {
        String first_database[]= insert_table.split(" ");
        if(first_database.length==6 || first_database.length==5){
            File f = new File(validation_using_database+"/"+first_database[2]+".txt");
            if(f.exists()){
                System.out.println("Table exist now inserting elements");
                return true;
            }
            else {
                EventLogs.enterIntoEventLogs(first_database[2],"Insert table","Table doesn't exist please create a table");
                System.out.println("Table doesn't exist please create a table");
                return true;
            }
        }
        else {
            System.out.println("Please enter the correct command");
            return false;
        }
    }
//    public static void main(String args[]) throws IOException {
//        validating_insert_table("insert into Student values (1,Purwar,Sparsh,2));");
//        validating_insert_table("insert into Student (FirstName,s_courseid,StudentID,LastName) values (Sparsh1,3,4,Purwar1);");
//    }


}
