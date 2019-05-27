package main;

import lib.BuiltInNodes;
import lib.Controller;
import lib.Node;

public class Main {

    public static void main(String[] args) {

        String project_name = "Demo_test";

        Controller controller = new Controller("148.60.11.161");


        Node S = controller
                .deleteProject(project_name)
                .addProject(project_name)
                .getProject(project_name)
                .addNode("N1", "Alpine Linux", 200, 200)
                .addNode("N2", "vpcs", -200, 200)
                .addNode("N3", "docker_appliance", 200, 200)
                .addNode("N4", "AAA", -200, -200)
                .addNode("N5", "AAA", 200, -200)
                .addNode("S", BuiltInNodes.ethernet_switch.toString(), 0, 0)
                .getNode("S");

        int i = 0;
        for (Node n : controller.getProject(project_name).getNodes()){
            if (!n.getName().equals("S")){
                controller.getProject(project_name).addLink(n, S, 0, i);
                i++;
            }
        }
    }
}
