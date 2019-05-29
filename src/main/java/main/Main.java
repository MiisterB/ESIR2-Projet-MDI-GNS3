package main;

import lib.Controller;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("148.60.11.161");

        String project_name = "Test_commandes";

        controller
                .getProject(project_name)
                .openProject()
                .getLinks()
                .forEach(link -> {
                    System.out.println(link.getId());
                });
    }
}
