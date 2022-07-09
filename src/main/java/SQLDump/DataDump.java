package SQLDump;

import Login.DatabaseOperations;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataDump {

    List<String> readDB(String dbName) throws Exception {

        List<String> dbDataList = new ArrayList<String>();

//        String path = dbName + "/metadata.txt";
//        System.out.println(path);

        File dbFile = new File(dbName);
        Scanner dbScanner = new Scanner(dbFile);

        while (dbScanner.hasNextLine()) {
            dbDataList.add(dbScanner.nextLine());
        }

        return dbDataList;

    }

    List<String> createColList(List<String> dbDataList) {

        List<String> colList = new ArrayList<String>();
        for (String rec : dbDataList) {
            List<String> dbRec = Arrays.asList(rec.split("\\s*~\\s*"));

            colList.add(dbRec.get(0));

        }
        List<String> colListNew = new ArrayList<String>(
                new HashSet<>(colList));
        return colListNew;

    }

    String createQuery(List<String> dbRecords, String tbName) {

        List<String> colArray = new ArrayList<String>();
        List<String> colType = new ArrayList<String>();
        List<String> currCol = new ArrayList<String>();
        List<String> fkTable = new ArrayList<String>();
        List<String> fkCol = new ArrayList<String>();

        boolean pkExist = false;
        boolean fkExist = false;
        String pkCol = "";

        for (String tbRec : dbRecords) {

            List<String> dbRec = Arrays.asList(tbRec.split("\\s*~\\s*"));
            if (Objects.equals(dbRec.get(0), tbName)) {
                colArray.add(dbRec.get(1));
                if (Objects.equals(dbRec.get(2), "int")) {
                    colType.add("INT");
                } else {
                    colType.add("VARCHAR(" + dbRec.get(3) + ")");
                }

                if (dbRec.size() >= 4) {
                    if (Objects.equals(dbRec.get(3), "pk")) {
                        pkCol = dbRec.get(1);
                        pkExist = true;
                    } else if (Objects.equals(dbRec.get(3), "fk")) {

                        currCol.add(dbRec.get(1));
                        fkTable.add(dbRec.get(4));
                        fkCol.add(dbRec.get(5));
                        fkExist = true;

                    }
                }

            }
        }

        String query = "CREATE TABLE " + tbName + " (";
        for (int j = 0; j < colArray.size(); j++) {
            query = query + colArray.get(j) + " " + colType.get(j) + " ";
            if (j < (colArray.size() - 1)) {
                query = query + ",";
            }
        }

        if (pkExist) {
            query = query + ", PRIMARY KEY (" + pkCol + ")";
        }
        if (fkExist) {

            for (int k = 0; k < currCol.size(); k++) {
                query = query + ", FOREIGN KEY (" + currCol.get(k) + ")" + " REFERENCES " + fkTable.get(k) + "(" + fkCol.get(k) + ")";
            }
        }
        query = query + ");";
        return query;

    }


    List<String> sortReference(List<String> queries) {

        List<String> createQueryArray = new ArrayList<String>();
        List<String> refQueryArray = new ArrayList<String>();

        for (String query : queries) {

            if (query.contains("REFERENCES")) {
                refQueryArray.add(query);
            } else {
                createQueryArray.add(query);
            }
        }

        List<String> finalQueryArray = Stream.concat(createQueryArray.stream(), refQueryArray.stream()).collect(Collectors.toList());

        return finalQueryArray;
    }

    List<String> sortRefForInsert(List<String> queries) {

        List<String> ColArr1 = new ArrayList<String>();
        List<String> ColArr2 = new ArrayList<String>();

        for (String query : queries) {

            String[] strList = query.split("[~]");

            if (query.contains("fk")) {

                if (!ColArr2.contains(strList[0])) {
                    System.out.println("INSERTED");
                    ColArr2.add(strList[0]);
                }

            } else  {

                if (!ColArr1.contains(strList[0])) {
                    ColArr1.add(strList[0]);
                }
            }
        }

        List<String> finalQueryArray = Stream.concat(ColArr1.stream(), ColArr2.stream()).collect(Collectors.toList());
        System.out.println("******"+finalQueryArray);
        List<String> colListNew = new ArrayList<>();
        for(String s: finalQueryArray){

            if(!colListNew.contains(s)){
                colListNew.add(s);
            }
        }
        return colListNew;


    }

    List<String> createInsert(List<String> tableRecords, String tbName) {

        List<String> insertQue = new ArrayList<String>();

        for (int k = 1; k < tableRecords.size(); k++) {

            String str1 = "INSERT INTO " + tbName + " VALUES (";
            String temp = "";
            List<String> commonCols = Arrays.asList(tableRecords.get(k).split("\\s*~\\s*"));
            for (int m = 0; m < commonCols.size(); m++) {

                temp = temp + "'" + commonCols.get(m) + "'";
                if (m != commonCols.size() - 1) {
                    temp = temp + ",";
                }

            }

            str1 = str1 + temp + ");";
            insertQue.add(str1);

        }

        return insertQue;
    }

    public void DBDumpMain(String dbName) throws Exception {

        System.out.println(dbName);

        List<String> dbDataList = readDB(dbName + "/metadata.txt");

        List<String> dbColList = createColList(dbDataList);
        List<String> queries = new ArrayList<String>();


        for (String rec : dbColList) {

            queries.add(createQuery(dbDataList, rec));
        }

        List<String> finalArray = new ArrayList<>();
        String dropDataBase = "DROP DATABASE IF EXISTS " + dbName + ";";
        String createDatabase = "CREATE DATABASE " + dbName + ";";
        String useDataBase = "USE " + dbName + ";";

        finalArray.add(dropDataBase);
        finalArray.add(createDatabase);
        finalArray.add(useDataBase);
        finalArray.addAll(sortReference(queries));


        List<String> insertList = sortRefForInsert(dbDataList);


        /* To Add For Loop and Save to .sql File */
        for (String col : insertList) {

            List<String> dbCols = readDB(dbName + "/" + col + ".txt");
            List<String> rec = createInsert(dbCols, col);

//            System.out.println(rec);
////        System.out.println("4");

            finalArray.addAll(rec);


        }

        FileWriter writer = new FileWriter("DB_DUMP.sql");
        for (String strs : finalArray) {
            writer.write(strs + System.lineSeparator());
        }
        writer.close();

        System.out.println("DB Dump Created Successfully!");

        DatabaseOperations dops = new DatabaseOperations();
        dops.databaseOperations();

//
//        for (String s : finalArray) {
//
//            System.out.println(s);
//        }

    }
}
