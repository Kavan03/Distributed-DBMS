package ERD;

import Login.DatabaseOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ERDiagram {
    //method to create ERD
    public void createERD() throws Exception {
        // take database name from user
        System.out.println("Please provide database name");
        Scanner sc = new Scanner(System.in);
        String dbName = sc.nextLine();
        File f = new File(dbName);
        if (f.exists()) {
            // if database exist, fetch metadata.txt
            String targetFile = dbName + "/metadata.txt";
            PrintWriter pw = new PrintWriter("ERDdemo.txt");
            FileReader file = new FileReader(targetFile);
            BufferedReader br = new BufferedReader(file);
            String details = br.readLine();
            int count = 1;
            // storing tables, columns and relations in individual arraylist
            ArrayList<String> tables = new ArrayList<>();
            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> relations = new ArrayList<>();
            while (details != null) {
                // code for storing tables and columns in ERD text file
                details = details.replaceFirst(".{4}$", "");
                String[] strArray = details.split("[~]");
                List<String> data = Arrays.asList(strArray);
                String tableName = strArray[0];
                if (!tables.contains(tableName)) {
                    tables.add(tableName);
                }
                if (!strArray[0].equalsIgnoreCase(tables.get(count - 1))) {
                    String cols = columns.toString().replaceAll("(^\\[~\\]$)", "");
                    pw.println("Table - " + tables.get(count - 1));
                    pw.println("Columns - " + cols);
                    pw.println();
                    columns.clear();
                    count++;
                }
                if (data.contains("fk")) {
                    columns.add("(fk) " + strArray[1]);
                    // code for storing relations in ERD text file
                    String relation = "(N-1) relation between " + strArray[0] + " ((fk) " + strArray[1] + ") -> "
                            + strArray[strArray.length - 2] + " ((pk) " + strArray[strArray.length - 1] + ")";
                    relations.add(relation);
                } else if (data.contains("pk"))
                    columns.add("(pk) " + strArray[1]);
                else
                    columns.add(strArray[1]);
                details = br.readLine();
            }
            // printing final relations and cardinality in text file
            String cols = columns.toString().replaceAll("(^\\[~\\]$)", "");
            pw.println("Table - " + tables.get(count - 1));
            pw.println("Columns - " + cols);
            pw.println();
            pw.println("Relationships between tables");
            pw.println("-----------------------------------------");
            if (relations.size() == 0)
                pw.println("No relationships between tables");
            else {
                for (String relation : relations) {
                    pw.println(relation);
                    pw.println();
                }
            }
            System.out.println("ERD generated successfully");
            pw.close();

            DatabaseOperations dops = new DatabaseOperations();
            dops.databaseOperations();

        } else {
            // if user provides wrong database, throws error
            System.out.println("No such database present. Provide valid database name");
            DatabaseOperations dbops = new DatabaseOperations();
            dbops.databaseOperations();
        }
    }
}