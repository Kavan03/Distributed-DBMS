package Logs;

import Login.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralLogs {

    /*
     ** This method will insert the start time, execution time, user and database details with the query information in the general logs.
     */
    public static void enterIntoGeneralLogs(long startTime,long executionTime, String databaseName){
        try {
            //FileReader reader = new FileReader("");

            FileWriter myWriter = new FileWriter("generalLogs.txt",true);

            String[] dbName = databaseName.split(" ");
            String databaseNameFinal = dbName[1].substring(0,dbName[1].length()-1);
            myWriter.write("\nuser: "+Login.getUserName()+"* startTime: "+startTime+"* executionTime: "+executionTime + "* database name: "+ databaseNameFinal);
            myWriter.write("\n");
            File directoryPath = new File("./" + databaseNameFinal);
            if(!directoryPath.exists()){
                EventLogs.enterIntoEventLogs(databaseNameFinal,"use issue","No database found");
                return;
            }
            File filesList[] = directoryPath.listFiles();
            if(filesList.length==0){
                myWriter.close();
                return;
            }
            for (File file : filesList) {
                String filename = file.getName();
                if (filename.equals("metadata.txt")) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    String[] strArr = line.split("[~]");
                    String strTable = strArr[0];
                    List<String> tableNames = new ArrayList<>();

                    while (line != null) {
                        String[] strArray = line.split("[~]");


                            if(!tableNames.contains(strArray[0])){
                                String tableName = "./"+databaseNameFinal+"/"+strArray[0]+".txt";

                                BufferedReader readTable = new BufferedReader(new FileReader("./"+databaseNameFinal+"/"+strArray[0]+".txt"));
                                String rows = readTable.readLine();
                                int count=0;
                                while(rows!=null) {

                                    count++;
                                    rows = readTable.readLine();
                                }
                                int numberOfRecords = count - 1;
                                myWriter.write("------- Table name : " + strArray[0] + " * numberOfRecords: " + numberOfRecords);
                                tableNames.add(strArray[0]);
                                //line = br.readLine();
                            }




                        line = br.readLine();

                    }
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Login.getUserName());
    }
}
