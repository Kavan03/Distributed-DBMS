package Login;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class ResetPassword {

    Actions act = new Actions();

    boolean resetPassword() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username:");
        String userName = sc.nextLine();
        if (act.checkUserName(userName)) {
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
                if (Objects.equals(name, act.hashString(userName))) {

                    System.out.println("Enter Answer for Security Question");
                    System.out.print("What is Your School Name: ");

                    String answer = sc.nextLine();

                    if (Objects.equals(answer, items.get(2))) {
                        System.out.print("Enter New Password: ");
                        String newPassword = sc.nextLine();

                        copyList.remove(i);

                        String updateString = act.hashString(userName) + "~" + act.hashString(newPassword) + "~" + answer;
                        copyList.add(updateString);
                        updateUser(copyList);
                        System.out.println("Password Successfully Reset");
                        act.onboardUser();

                    } else {
                        System.out.println("Incorrect Answer");
                        act.onboardUser();
                    }

                }
            }
            return false;

        } else {
            System.out.println("User Does Not Exist\n");
            act.onboardUser();
        }

        return false;
    }

    static void updateUser(List<String> copyList) throws Exception {


        FileWriter writer = new FileWriter("users.txt");
        for (String user : copyList) {
            writer.write(user + System.lineSeparator());
        }
        writer.close();
    }
}
