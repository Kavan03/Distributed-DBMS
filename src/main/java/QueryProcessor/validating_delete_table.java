package QueryProcessor;

import Logs.EventLogs;

import java.io.File;
import java.io.IOException;

import static QueryProcessor.validation_use_database.validation_using_database;

public class validating_delete_table {
    public static Boolean validating_delete_table(String delete_table) throws IOException {
        delete_table=delete_table.replaceAll("[^a-zA-Z,0-9_*=]", " ");
        String first_database[]= delete_table.split(" ");
        System.out.println(first_database.length);
        if(first_database.length==7){
            File f = new File(validation_using_database+"/"+first_database[2]+".txt");
            if(f.exists()){
                System.out.println("Table exist now dropping elements");
                return true;
            }
            else {
                EventLogs.enterIntoEventLogs(first_database[2],"Delete table","Table doesn't exist please create a table");
//                System.out.println("Table doesn't exist please create a table");
                return false;
            }
        }
        else {
            System.out.println("Please enter the correct command");
            return false;
        }
    }
    public static void main(String args[]) throws IOException {
        validating_delete_table("delete from abc where FirstName = Sparsh1;");
    }
}
