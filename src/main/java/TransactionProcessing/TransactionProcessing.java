package TransactionProcessing;

import Login.Login;
import Logs.QueryLogs;
import QueryProcessor.*;
import Login.DatabaseOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TransactionProcessing {

    QueryLogs ql = new QueryLogs();


    public static String getTransDBName() {
        return transDBName;
    }

    public static void setTransDBName(String transDBName) {
        TransactionProcessing.transDBName = transDBName;
    }

    public static String transDBName;

    public List<String> transactionProcesing() {

        /**
         * Storing SQL Commands List in an Array
         */

        List<String> commandsList = new ArrayList<String>();
        commandsList.add("start");
        //("sql>");


        class Local {
            public void commandLoop() {

                Scanner sc2 = new Scanner(System.in);
                String line = sc2.nextLine();

                /**
                 * To check if string does not contain keyword end of transaction
                 */

                if (!line.contains("commit")) {
                    commandsList.add(line);
                    System.out.print("sql>");

                    /**
                     * Recursion for entering the next sql statements
                     */

                    commandLoop();
                } else {
                    //("\n");
                    commandsList.add(line);
                }
            }
        }


        new Local().commandLoop();
        return commandsList;
    }

    void performTransaction(String query, boolean tcheck) throws Exception {

        /**
         * String the list of transaction queries and splitting the query list with ""
         */


        /**
         * Switch case to perform the DDL and DML operations
         */

        if (!query.contains("start")) {
            if (!query.contains("commit")) {

                //("HERE");
                if (tcheck) {
                    main.performOps(query, true);
                } else {
                    main.performOps(query, false);
                }

            }

        }

    }

    String dbName;

    public void setDbName(String name) {
        this.dbName = name;
    }

    public String getDbName() {
        return this.dbName;
    }

    String readFile() throws Exception {

        List<String> dbDataList = new ArrayList<String>();


        File dbFile = new File("db_status.txt");
        Scanner dbScanner = new Scanner(dbFile);

        while (dbScanner.hasNextLine()) {
            dbDataList.add(dbScanner.nextLine());
        }

        return dbDataList.get(0);

    }

    public void TransactionManager() throws Exception {

        /**
         * Performing transaction queries if there is no transaction strat and end keywords
         */
        Scanner sc = new Scanner(System.in);
        System.out.print("sql>");
        String iniQue = sc.nextLine();
        DatabaseOperations db = new DatabaseOperations();

        if (iniQue.contains("start")) {

            List<String> tpList = transactionProcesing();
            String tempDBName = tpList.get(1).toLowerCase();
            String[] quList = tempDBName.split(" ");
            if (tempDBName.contains("use")) {
                setTransDBName(quList[1]);
            } else {
                setTransDBName(quList[2]);
            }
            String user_name = Login.getUserName();
            String db_name = getTransDBName();
            long startTime = System.nanoTime();
            ql.writeDBLock(user_name, db_name, startTime, "LOCKED");

            String db_status = readFile();
            if (Objects.equals(db_status, "true")) {
                System.out.println("Database Locked Cannot Perform Transaction, Try Again in Some Time");
                db.databaseOperations();

            } else {
                FileWriter writer = new FileWriter("db_status.txt");
                writer.write("true");
                writer.close();
            }

            System.out.println("Database Locked");
            System.out.println("Performing Transaction");

            for (String str : tpList) {


                performTransaction(str, true);
                if (str.contains("commit")) {

                    long endTime = System.nanoTime();
                    ql.writeDBLock(user_name, db_name, endTime, "UNLOCKED");
                    FileWriter writer = new FileWriter("db_status.txt");
                    writer.write("false");
                    writer.close();
                    System.out.println("Database Unlocked");
                    db.databaseOperations();

                }

            }
        } else {

            performTransaction(iniQue, false);

        }


    }

}
