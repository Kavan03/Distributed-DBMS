package QueryProcessor;

import java.nio.file.Files;
import java.nio.file.Paths;

public class using_database {
    public static void using_database(String database_name) {
        try {
            database_name=database_name.replaceAll("[^a-zA-Z,0-9_]"," ");
            String database_split[] = database_name.split(" ");
            if (Files.exists(Paths.get(database_split[1]))) {
                System.out.println("Database is now available to use");
            } else {
                System.out.println("Do not Exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        using_database("use students;");
    }
}
