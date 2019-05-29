package main;

import lib.Controller;
import lib.Node;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("148.60.11.161");

        String project_name = "Test_commandes";

        Node n = controller
                .getProject(project_name)
                .openProject()
                .getNode("N")
                .startNode();

        System.out.println(n
                .sendCmd("mkdir besma")
                .sendCmdAndGetResp("ls"));
    }
}
