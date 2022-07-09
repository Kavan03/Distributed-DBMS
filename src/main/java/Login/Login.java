package Login;

import java.io.File;
import java.util.*;

public class Login {

    public static String userName;
    void LoginMethod() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter UserId: ");
        String userId = sc.nextLine();

        Actions act = new Actions();

        if (act.checkUserName(userId)) {
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
                if (Objects.equals(items.get(0), act.hashString(userId))) {
                    if (Objects.equals(items.get(1), act.hashString(userPassword))) {
                        if (Objects.equals(items.get(2), securityAnswer)) {

                            setUserName(userId);
                            DatabaseOperations db = new DatabaseOperations();
                            User user = new User();
                            user.setUserId(userId);
                            System.out.println(user.getUserID());
                            db.databaseOperations();


                        } else {
                            System.out.println("Incorrect Security Answer, Try Again");
                            act.onboardUser();
                        }
                    } else {
                        System.out.println("Incorrect Password, Try Again");
                        act.onboardUser();
                    }
                }
            }
        } else {
            System.out.println("User Does Not Exist");
            act.onboardUser();
        }
    }

    public void setUserName(String name){
        this.userName = name ;
    }


    public static String getUserName(){
        return userName;
    }
}
