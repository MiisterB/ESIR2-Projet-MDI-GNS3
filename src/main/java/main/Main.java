package main;

import lib.Controller;

public class Main {

    public static void main(String[] args)
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testMain";

        controller
                .deleteProject(project_name)
                .addProject(project_name)
                .getProject(project_name)
                .addNode("V1", "AAA", -200, 200)
                .addNode("V2", "vpcs", 0, 200)
                .addNode("V3", "Alpine Linux", 200, 200)
                .addNode("V4", "other", 200, 200);
    }

}