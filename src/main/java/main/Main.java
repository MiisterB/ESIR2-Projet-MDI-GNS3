package main;

import lib.BuiltInNodes;
import lib.Controller;
import lib.Node;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("192.168.56.103");

        String project_name = "Demo_test";

        Node S = controller
                .deleteProject(project_name)
                .addProject(project_name)
                .getProject(project_name)
                .addNode("N1", "vpcs", 200, 200)
                .addNode("N2", "vpcs", -200, 200)
                .addNode("N3", "docker_appliance", 200, 200)
                .addNode("N4", "vpcs", -200, -200)
                .addNode("N5", "vpcs", 200, -200)
                .addNode("S", BuiltInNodes.ethernet_switch.toString(), 0, 0)
                .getNode("S");

        int i = 0;
        for (Node n : controller.getProject(project_name).getNodes()){
            if (!n.getName().equals("S")){
                controller.getProject(project_name).addLink(n, S, 0, i);
                i++;
            }
        }

/*        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1","vpcs",-500,0).getNode("N1");

        Structure structure1 = new Structure("A",controller, project_name, 5, 200,200, n1);
        Node n2 = structure1.generateStructure();
        Structure structure2 = new Structure("B",controller, project_name, 5, 400,200);
        structure2.generateStructure();*/
    }
}
