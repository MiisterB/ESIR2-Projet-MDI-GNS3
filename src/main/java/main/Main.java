
package main;

import lib.Controller;
import lib.Node;
import lib.Structure;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");
        /*
        String project_name = "testNodes1";

        controller.deleteProject(project_name);
        Node s = controller
                .addProject(project_name)
                .getProject(project_name)
                .addNode("V1", "vpcs", -200, -200)
                .addNode("V2", "vpcs", 200, 200)
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
        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1","vpcs",-500,0).getNode("N1");

        Structure structure1 = new Structure("A",controller, project_name, 5, 200,200, n1);
        Node n2 = structure1.generateStructure();
        Structure structure2 = new Structure("B",controller, project_name, 5, 400,200);
        structure2.generateStructure();
    }
}

