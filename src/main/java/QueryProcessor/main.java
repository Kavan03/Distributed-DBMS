package QueryProcessor;

import Login.DatabaseOperations;
import Login.Login;
import Logs.GeneralLogs;
import Logs.QueryLogs;

import static QueryProcessor.validation_created_database.validation_created_database_method;

//import static Module2.execute.validation_created_database.validation_created_database_method;

public class main {


    public static String dbname;
    static QueryLogs ql = new QueryLogs();

    public static void performOps(String onboarding, boolean dbLockCheck) throws Exception {
//        Scanner sc = new Scanner(System.in);
//        String onboarding = sc.nextLine();
        while (onboarding == "quit") {
            System.exit(1);
        }
        DatabaseOperations dops = new DatabaseOperations();


        String onboarding_split[] = onboarding.split(" ");
        for (String o : onboarding_split) {
            System.out.println(o);
        }
        if (onboarding_split[0].equals("create")) {
            if (onboarding_split[1].equals("database")) {
                if (validation_created_database_method(onboarding)) {


                    String usrname = Login.getUserName();
                    dbname = onboarding_split[2];
                    long startTime = System.nanoTime();
                    create_database.create_database(onboarding);
                    long endTime = System.nanoTime();
                    long executionTime = endTime - startTime;
                    ql.writeQueryLogs(usrname, dbname, onboarding, startTime, executionTime);

                    if (!dbLockCheck) {
                        dops.databaseOperations();

                    }
                }
            }
            if (onboarding_split[1].equals("table")) {
                System.out.println("Table ");
                if (validation_created_table.validation_created_table(onboarding)) {

//                    System.out.println("Table DB " + getDbname());
                    String usrname = Login.getUserName();
                    long startTime = System.nanoTime();
                    create_table.create_table(onboarding);
                    long endTime = System.nanoTime();
                    long executionTime = endTime - startTime;
                    ql.writeQueryLogs(usrname, dbname, onboarding, startTime, executionTime);
                    if (!dbLockCheck) {
                        dops.databaseOperations();

                    }

                }
            }
        }
        if (onboarding_split[0].equals("use")) {
            if (validation_use_database.validation_use_database(onboarding)) {

                dbname = onboarding_split[1];
                String usrname = Login.getUserName();
                long startTime = System.nanoTime();
                using_database.using_database(onboarding);
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                GeneralLogs.enterIntoGeneralLogs(startTime, executionTime, onboarding);
                ql.writeQueryLogs(usrname,dbname, onboarding, startTime, executionTime);
                if (!dbLockCheck) {
                    dops.databaseOperations();

                }
            }

        }
        if (onboarding_split[0].equals("insert")) {
            if (validating_insert_table.validating_insert_table(onboarding)) {

                String usrname = Login.getUserName();
                long startTime = System.nanoTime();
                insert_table.insert_table(onboarding);
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                ql.writeQueryLogs(usrname, dbname, onboarding, startTime, executionTime);
                if(!dbLockCheck){
                    dops.databaseOperations();

                }

            }
        }
        if (onboarding_split[0].equals("select")) {
            if (validating_select_table.validating_select_table(onboarding)) {

                String usrname = Login.getUserName();
                long startTime = System.nanoTime();
                select_query.select_query(onboarding);
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                ql.writeQueryLogs(usrname, dbname, onboarding, startTime, executionTime);
                if(!dbLockCheck){
                    dops.databaseOperations();

                }

            }
        }
        if (onboarding_split[0].equals("update")) {
            if (validating_update_table.validating_update_table(onboarding)) {

                String usrname = Login.getUserName();
                long startTime = System.nanoTime();
                update_query.update_query(onboarding);
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                ql.writeQueryLogs(usrname, dbname, onboarding, startTime, executionTime);
                if(!dbLockCheck){
                    dops.databaseOperations();

                }

            }
        }
        if (onboarding_split[0].equals("delete")) {
            if (validating_delete_table.validating_delete_table(onboarding)) {

                String usrname = Login.getUserName();
                long startTime = System.nanoTime();
                delete_query.delete_query(onboarding);
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                ql.writeQueryLogs(usrname, dbname, onboarding, startTime, executionTime);
                if(!dbLockCheck){
                    dops.databaseOperations();

                };

            }
        }


    }

//    public static void main(String args[]) {
//
//    }
}
