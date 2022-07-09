package Logs;

import Login.Login;

import java.io.FileWriter;

public class EventLogs {
    /*
     ** This method will insert the failed validations as event logs into event log files
     */
    public static void enterIntoEventLogs(String databaseName, String type, String message){

        System.out.println("DB "+ databaseName);
        System.out.println("Type "+ type);
        System.out.println("Msg "+ message);
        try {
            FileWriter myWriter = new FileWriter("eventLogs.txt",true);
            if(databaseName==null){
                myWriter.write("Database is null for this query!");
                myWriter.close();
                return;
            }
            String[] dbName = databaseName.split(" ");
            String databaseNameFinal;
            if(dbName.length==1){
                databaseNameFinal = databaseName;
            }
            else{
                databaseNameFinal = dbName[1].substring(0,dbName[1].length()-1);
            }
            myWriter.write("\n");
            myWriter.write("user: "+ Login.getUserName()+" * table/database name: "+ databaseNameFinal+" * Type: "+type+ " * Message: "+message);
            myWriter.close();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
