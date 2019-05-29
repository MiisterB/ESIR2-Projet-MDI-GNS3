
package main;

import lib.Controller;
import lib.Node;
import lib.Structure;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "Structure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1","vpcs",-200,0).getNode("N1");

        Structure structure1 = new Structure("A",controller, project_name,7, "ethernet_switch", 100,200,"Up");
        structure1.connectNode(n1,0);
    }
}

