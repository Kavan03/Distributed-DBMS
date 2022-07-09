package Login;

import java.util.Scanner;

public class Registration {

    Actions act = new Actions();

    void userNameLoop() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter UserName: ");
        String userName = sc.nextLine();



        if (act.checkUserName(userName)) {
            System.out.println("Username Already Exist, Choose A Unique Username");
            act.onboardUser();
        } else {
            RegisterMethod(userName);
        }

    }

    void RegisterMethod(String userName) throws Exception {


        Scanner registerScanner = new Scanner(System.in);

        System.out.println("Set Password");
        String userPassword = registerScanner.nextLine();

        System.out.println("Set Answer to Security Question");
        System.out.print("What is Your School Name: ");
        String securityQueAns = registerScanner.nextLine();

        String regString = act.hashString(userName) + "~" + act.hashString(userPassword) + "~" + securityQueAns;
        act.saveUser(regString);
        System.out.println("User Saved Successfully");
        act.onboardUser();


    }
}
