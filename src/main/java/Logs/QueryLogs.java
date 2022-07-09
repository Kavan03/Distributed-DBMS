package Logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryLogs {
    /*
     ** This method will insert the query information based on the queries inserted by the user on a database
     */
    public void writeQueryLogs(String userID, String database, String query, long startTime, long executionTime) throws Exception {

        List<String> dataList = new ArrayList<>();

        File dbFile = new File("queryLogs.txt");
        Scanner fileScanner = new Scanner(dbFile);

        while (fileScanner.hasNextLine()) {
            dataList.add(fileScanner.nextLine());
        }
        database = database.substring(0, database.length() - 1);
        String[] queryCommand = query.split(" ");

        String queryString = "user~" + userID + "~database~" + database + "~query~" + queryCommand[0] + "~startTime~" + startTime + "~executionTime~" + executionTime + "~VM1";

        dataList.add(queryString);

        FileWriter queryLogWriter = new FileWriter("queryLogs.txt");
        for (String log : dataList) {
            queryLogWriter.write(log + System.lineSeparator());
        }
        queryLogWriter.close();

    }

    public void writeDBLock(String userID, String database, long timing, String status) throws Exception {

        List<String> dataList = new ArrayList<>();

        File dbFile = new File("queryLogs.txt");
        Scanner fileScanner = new Scanner(dbFile);

        while (fileScanner.hasNextLine()) {
            dataList.add(fileScanner.nextLine());
        }

        String queryString = "user~" + userID + "~database~" + database + "~msg~" + status + "~time~" + timing + "~VM1";

        dataList.add(queryString);

        FileWriter queryLogWriter = new FileWriter("queryLogs.txt");
        for (String log : dataList) {
            queryLogWriter.write(log + System.lineSeparator());
        }
        queryLogWriter.close();
    }


}
