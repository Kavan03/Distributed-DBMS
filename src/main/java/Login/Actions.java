package Login;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Actions {

    boolean checkUserName(String userName) throws FileNotFoundException {

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

    String hashString(String stringToHash) {

        return DigestUtils.sha256Hex(stringToHash);
    }

    void saveUser(String regString) throws Exception {

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

    void onboardUser() throws Exception {

        System.out.println("Select Onboarding Type:");
        System.out.println("Enter the Number to Select");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Reset/Forget Password");
        System.out.print("Enter Input: ");

        Scanner sc = new Scanner(System.in);
        String onboardInput = sc.nextLine();

        if (Objects.equals(onboardInput, "1")) {
            Login lg = new Login();
            lg.LoginMethod();
        } else if (Objects.equals(onboardInput, "2")) {
            Registration reg = new Registration();
            reg.userNameLoop();
        } else if (Objects.equals(onboardInput, "3")) {
            ResetPassword rspwd = new ResetPassword();
            rspwd.resetPassword();
        } else {
            System.out.println("Incorrect Input, Try Again\n");
            onboardUser();
        }

    }
}
