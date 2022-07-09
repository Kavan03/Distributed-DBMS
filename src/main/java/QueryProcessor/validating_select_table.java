package QueryProcessor;

import Logs.EventLogs;

import java.io.File;
import java.io.IOException;

import static QueryProcessor.validation_use_database.validation_using_database;

public class validating_select_table {
    public static Boolean validating_select_table(String select_table) throws IOException {
        try{
            select_table=select_table.replaceAll("[^a-zA-Z,0-9_*=]", " ");
            String first_database[]= select_table.split(" ");
//        System.out.println(first_database.length);
//        System.out.println(first_database[3]);
            if(first_database.length>=4){
                File f = new File(validation_using_database+"/"+first_database[3]+".txt");
                if(f.exists()){
                    System.out.println("Table exist now selecting elements");
                    return true;
                }
                else {
                    EventLogs.enterIntoEventLogs(first_database[3],"Select table","Table doesn't exist please create a table");
                    System.out.println("Table doesn't exist please create a table");
                    return true;
                }
            }
            else {
                System.out.println("Please enter the correct command");
                return false;
            }
        }catch (Exception e){
            System.out.println("Issue in selecting table");
        }

        return null;
    }
    public static void main(String args[]) throws IOException {
        validating_select_table("select * from abc;");
////        select_query("select * from Student where LastName = 'Purwar' ;");
//        validating_select_table("select StudentID from Student where FirstName = 'Sparsh1' ;");
    }
}
