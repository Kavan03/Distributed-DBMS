package QueryProcessor;

import Logs.EventLogs;

import java.io.File;
import java.io.IOException;

import static QueryProcessor.validation_use_database.validation_using_database;

public class validating_update_table {
    public static Boolean validating_update_table(String update_table) throws IOException {
        update_table=update_table.replaceAll("[^a-zA-Z,0-9_*=]", " ");
        String first_database[]= update_table.split(" ");
        System.out.println(first_database.length);
        if(first_database.length==10){
            File f = new File(validation_using_database+"/"+first_database[1]+".txt");
            if(f.exists()){
                System.out.println("Table exist now updating elements");
                return true;
            }
            else {
                EventLogs.enterIntoEventLogs(first_database[1],"Update table","Table doesn't exist please create a table");
                System.out.println("Table doesn't exist please create a table");
                return false;
            }
        }
        else {
            System.out.println("Please enter the correct command");
            return false;
        }
    }
    public static void main(String args[]) throws IOException {
        validating_update_table("update abc set LastName = Purwar123 where StudentID = 2;");
    }
}
