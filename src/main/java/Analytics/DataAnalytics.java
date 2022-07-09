package Analytics;

import Login.DatabaseOperations;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DataAnalytics {

    /**
     *  Function to Read Query Logs and Return Array of Logs
     */
    static List<String> readQueryLogs() throws Exception {

        List<String> dbDataList = new ArrayList<String>();


        File dbFile = new File("queryLogs.txt");
        Scanner dbScanner = new Scanner(dbFile);

        while (dbScanner.hasNextLine()) {
            dbDataList.add(dbScanner.nextLine());
        }

        return dbDataList;
    }

    /**
     *  Function to Read Username from Query Logs and Return Array of Usernames
     */
    static List<String> getUserNames(List<String> logs) {

        List<String> usernames = new ArrayList<>();

        for (String s : logs) {

            String[] loglist = s.split("[~]");
            if (!usernames.contains(loglist[1])) {
                usernames.add(loglist[1].toLowerCase());
            }
        }

        return usernames;

    }

    /**
     *  Function to Read Query Logs and Return Array of Databases Available
     */
    static List<String> getDatabases(List<String> logs) {

        List<String> dbnames = new ArrayList<>();

        for (String s : logs) {

            String[] loglist = s.split("[~]");
            if (!dbnames.contains(loglist[3].toLowerCase())) {
                if (!dbnames.contains(loglist[3].toLowerCase() + ";")) {
                    dbnames.add(loglist[3].toLowerCase());
                }

            }

        }

        return dbnames;

    }

    /**
     * Function to perform analytics on the number of queries fired by each user on a particular DB
     * @throws Exception
     */

    public static void doAnalysis() throws Exception {

        List<String> queryLogs = readQueryLogs();
        List<String> username = getUserNames(queryLogs);
        List<String> dbnames = getDatabases(queryLogs);
        List<String> Analytics = new ArrayList<>();


        for (String un : username) {
            for (String db : dbnames) {
                int counter = 0;
                for (String ql : queryLogs) {
                    if ((ql.contains(un)) && (ql.contains(db))) {
                        counter = counter + 1;
                    }
                }
                if (counter != 0) {
                    Analytics.add("User " + un.toUpperCase() + " Performed " + counter + " Operations On " + db + " On VM 1");
                }
            }
        }

        FileWriter writer = new FileWriter("Analytics.txt");
        for (String strs : Analytics) {
            writer.write(strs + System.lineSeparator());
        }
        writer.close();

        System.out.println("Analytics File Saved Successfully!");

        DatabaseOperations dops = new DatabaseOperations();
        dops.databaseOperations();

    }

}
