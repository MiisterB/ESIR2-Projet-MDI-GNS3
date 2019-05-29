package main;

import lib.Controller;

public class Main {

    public static void main(String[] args) {
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


/*        Node s = controller
                .addProject(project_name)
                .getProject(project_name)
                .addNode("V1", "vpcs", -200, -200)
                .addNode("V2", "AAA", 200, 200)
                .addNode("V3", "vpcs", 200, -200)
                .addNode("V4", "vpcs", -200, 200)
                .addNode("S", "ethernet_switch", 0, 0)
                .getNode("S");
        int i = 0;
        for (Node n : controller.getProject(project_name).getNodes()) {
            if (!n.getName().equals("S")) {
                controller
                        .getProject(project_name)
                        .addLink(n, s, 0, i);
                i++;
            }
        }*/
    }
}