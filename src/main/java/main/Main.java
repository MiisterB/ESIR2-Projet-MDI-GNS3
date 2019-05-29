
package main;

import lib.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");


        String project_name = "Test_commandes";

        String result = controller
                .getProject(project_name)
                .getNode("V1")
                .sendCmdAndReadUntil("?", "lorem ipsum ...", 4000);
        System.out.println(result);
    }
}