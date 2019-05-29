package main;

import lib.Controller;
import lib.Node;
import lib.Structure;

public class Main {

    public static void main(String[] args) {

        demoStructure();
    }

    public static void demoStructure() {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "Structure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1","vpcs",-600,0).getNode("N1");

        Structure structure1 = new Structure("A",controller, project_name,7, "ethernet_switch", -500,0,"Right");
        structure1.connectNode(n1,0);

        Structure structure2 = new Structure("B",controller, project_name,4, "ethernet_switch", -200,0,"Star");
        Structure structure3 = new Structure("C",controller, project_name,3, "ethernet_switch", 200,0,"Left");
        Structure structure4 = new Structure("D",controller, project_name,5, "ethernet_switch", 400,0,"Right");
        structure3.connectNode(structure4.getJunctionNode(),7);
    }
}
