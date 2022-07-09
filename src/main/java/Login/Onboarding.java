package Login;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class Onboarding {

    static void updateUser(List<String> copyList) throws Exception {


        FileWriter writer = new FileWriter("users.txt");
        for (String user : copyList) {
            writer.write(user + System.lineSeparator());
        }
        writer.close();
    }

    static boolean resetPassword() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username:");
        String userName = sc.nextLine();
        if (checkUserName(userName)) {
            List<String> usersList = new ArrayList<String>();
            File usersFile = new File("users.txt");
            Scanner users = new Scanner(usersFile);

            while (users.hasNextLine()) {
                usersList.add(users.nextLine());
            }

            List<String> copyList = new ArrayList<String>(usersList);

            for (int i = 0; i < usersList.size(); i++) {
                String uName = usersList.get(i);
                List<String> items = Arrays.asList(uName.split("\\s*~\\s*"));
                String name = items.get(0);
                if (Objects.equals(name, hashString(userName))) {

                    System.out.println("Enter Answer for Security Question");
                    System.out.print("What is Your School Name: ");

                    String answer = sc.nextLine();

                    if (Objects.equals(answer, items.get(2))) {
                        System.out.print("Enter New Password: ");
                        String newPassword = sc.nextLine();

                        copyList.remove(i);

                        String updateString = hashString(userName) + "~" + hashString(newPassword) + "~" + answer;
                        copyList.add(updateString);
                        updateUser(copyList);
                        System.out.println("Password Successfully Reset");
                        onboardUser();

                    } else {
                        System.out.println("Incorrect Answer");
                        resetPassword();
                    }

                }
            }
            return false;

        } else {
            System.out.println("User Does Not Exist\n");
            onboardUser();
        }

        return false;
    }

    static void userNameLoop() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter UserName: ");
        String userName = sc.nextLine();

        if (checkUserName(userName)) {
            System.out.println("Username Already Exist, Choose A Unique Username");
            userNameLoop();
        } else {
            RegisterMethod(userName);
        }

    }

    static void saveUser(String regString) throws Exception {

        List<String> usersList = new ArrayList<String>();
        File usersFile = new File("users.txt");
        Scanner users = new Scanner(usersFile);

        while (users.hasNextLine()) {

            usersList.add(users.nextLine());
        }

        usersList.add(regString);

        FileWriter writer = new FileWriter("users.txt");
        for (String user : usersList) {
            writer.write(user + System.lineSeparator());
        }
        writer.close();
    }

    static String hashString(String stringToHash) {

        return DigestUtils.sha256Hex(stringToHash);
    }


    static void onboardUser() throws Exception {

        System.out.println("Select Onboarding Type:");
        System.out.println("Enter the Number to Select");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Reset/Forget Password");
        System.out.print("Enter Input: ");

        Scanner sc = new Scanner(System.in);
        String onboardInput = sc.nextLine();

        if (Objects.equals(onboardInput, "1")) {
            LoginMethod();
        } else if (Objects.equals(onboardInput, "2")) {
            userNameLoop();
        } else if (Objects.equals(onboardInput, "3")) {
            resetPassword();
        } else {
            System.out.println("Incorrect Input, Try Again\n");
            onboardUser();
        }

    }

    static void LoginMethod() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter UserId: ");
        String userId = sc.nextLine();

        if (checkUserName(userId)) {
            System.out.println("Enter Password");
            String userPassword = sc.nextLine();

            System.out.println("Enter Answer to Security Question");
            System.out.print("What is Your School Name: ");
            String securityAnswer = sc.nextLine();

            List<String> usersList = new ArrayList<String>();
            File usersFile = new File("users.txt");
            Scanner users = new Scanner(usersFile);

            while (users.hasNextLine()) {
                usersList.add(users.nextLine());
            }

            for (String uName : usersList) {
                List<String> items = Arrays.asList(uName.split("\\s*~\\s*"));
                if (Objects.equals(items.get(0), hashString(userId))) {
                    if (Objects.equals(items.get(1), hashString(userPassword))) {
                        if (Objects.equals(items.get(2), securityAnswer)) {

                            DatabaseOperations db = new DatabaseOperations();
                            db.databaseOperations();

                        } else {
                            System.out.println("Incorrect Security Answer, Try Again");
                            onboardUser();
                        }
                    } else {
                        System.out.println("Incorrect Password, Try Again");
                        onboardUser();
                    }
                }
            }
        } else {
            System.out.println("User Does Not Exist");
            onboardUser();
        }
    }

    static void RegisterMethod(String userName) throws Exception {

        Scanner registerScanner = new Scanner(System.in);

        System.out.println("Set Password");
        String userPassword = registerScanner.nextLine();

        System.out.println("Set Answer to Security Question");
        System.out.print("What is Your School Name: ");
        String securityQueAns = registerScanner.nextLine();

        String regString = hashString(userName) + "~" + hashString(userPassword) + "~" + securityQueAns;
        saveUser(regString);
        System.out.println("User Saved Successfully");
        onboardUser();

    }

    static boolean checkUserName(String userName) throws FileNotFoundException {

        List<String> userNameList = new ArrayList<String>();


        File usersFile = new File("users.txt");
        Scanner userNameScanner = new Scanner(usersFile);
        while (userNameScanner.hasNextLine()) {
            String fileUserName = userNameScanner.nextLine();
            userNameList.add(fileUserName.split("\\s*~\\s*")[0]);
        }

        boolean checkList = userNameList.isEmpty();

        if (checkList) {
            return false;
        } else {
            for (String user : userNameList) {

                String hashUser = hashString(userName);
                if (Objects.equals(user, hashUser)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        onboardUser();

    }

}
