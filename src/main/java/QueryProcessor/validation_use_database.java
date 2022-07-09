package QueryProcessor;

import Logs.EventLogs;

import java.io.File;

import static QueryProcessor.connection.listFolderStructure;

public class validation_use_database {
    static String validation_using_database = "";

    public static Boolean validation_use_database(String database_name) throws Exception {
        database_name=database_name.replaceAll("[^a-zA-Z,0-9_]"," ");
//        System.out.println(database_name);
        String first_database[] = database_name.split(" ");
        try{
            if (first_database.length == 2) {
                File f = new File(first_database[1]);
                if (f.exists()) {
                    validation_using_database = first_database[1];
//                System.out.println(validation_using_database);
//                System.out.println(first_database[1]);
                    return true;
                } else if (listFolderStructure("cat /home/ubuntu/project/"+first_database[1]+"/metadata.txt")){
                    EventLogs.enterIntoEventLogs(first_database[1],"use database issue","Database does not exist cannot use database");
                    System.out.println("Database exist on second server now using second sever");
                    validation_using_database = first_database[1];
//                System.out.println(validation_using_database);
                    return true;
                }
                else {
                    System.out.println("No database found");
                    return true;
                }
            } else {
                System.out.println("Please enter the correct command");
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }
    public static void main(String args[]) throws Exception {
        validation_use_database("Use students;");
    }
}
