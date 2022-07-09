package QueryProcessor;

import Logs.EventLogs;

import java.io.File;
import java.io.IOException;

import static QueryProcessor.validation_use_database.validation_using_database;

//import static Module2.execute.validation_use_database.validation_using_database;

public class validation_created_table {
    public static Boolean validation_created_table(String database_name) throws IOException {
        String first_database[]= database_name.split(" ");
//        System.out.println(validation_using_database);
//        System.out.println(first_database[2]);
//        System.out.println("DW"+"/"+first_database[2]+".txt");
        File f = new File(validation_using_database+"/"+first_database[2]+".txt");
        if(validation_using_database.isEmpty()){
            System.out.println("Database not selected, Please use database");
            return true;
        }
        else if (!f.exists()){

            System.out.println("Table doesn't exist validation is successful");
            return true;
        }
        else {
            EventLogs.enterIntoEventLogs(first_database[2],"Table issue","Table exist cannot create a Table");
            System.out.println("Table exist cannot create a Table");
            return false;
        }
    }
//    public static void main(String args[]) throws IOException {
//        validation_created_table("CREATE TABLE Student (StudentID int primary key, LastName varchar(255), FirstName varchar(255), s_courseid int foreign key courses(course_id);");
//    }
}
