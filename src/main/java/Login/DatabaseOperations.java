package Login;

import java.io.IOException;
import java.util.Scanner;

//import Analytics.BasicAnalytics;
import Analytics.DataAnalytics;
import ERD.ERDiagram;
import SQLDump.DataDump;
import TransactionProcessing.TransactionProcessing;
//import QueryProcessor.QueryProcessor;
//import SqlDump.SqlDump;

public class DatabaseOperations {

    public void databaseOperations() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the operation you need to perform");
        System.out.println("1. Write Queries");
        System.out.println("2. Export");
        System.out.println("3. Data Model");
        System.out.println("4. Analytics");
        System.out.println("5. Exit");
        int input = sc.nextInt();
        switch (input) {
            case 1:
                TransactionProcessing tp = new TransactionProcessing();
                tp.TransactionManager();
                break;
            case 2:
                DataDump datadump = new DataDump();
                Scanner ddSc = new Scanner(System.in);
                System.out.print("Enter Database Name to Export: ");
                String dbName = ddSc.nextLine();
                TransactionProcessing tpdb = new TransactionProcessing();
                tpdb.setDbName(dbName);
                datadump.DBDumpMain(tpdb.getDbName());
                break;
            case 3:
                ERDiagram erd = new ERDiagram();
                erd.createERD();
                break;
            case 4:
                DataAnalytics.doAnalysis();

        	break;
            case 5:
                System.exit(1);

            default:
                System.out.println("Please provide valid input");

        }
    }
}
