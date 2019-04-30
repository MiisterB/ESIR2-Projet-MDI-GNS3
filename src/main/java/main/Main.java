package main;

import lib.Controller;
import lib.Node;
import lib.Project;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

        controller.deleteProject("testNodes1");
        Project p = controller.addProject("testNodes1");

        ArrayList<Node> terminals = new ArrayList<>();
        terminals.add(p.addNode("V1", "vpcs", -200, -200));
        terminals.add(p.addNode("V2", "vpcs", 200, 200));
        terminals.add(p.addNode("V3", "vpcs", 200, -200));
        terminals.add(p.addNode("V4", "vpcs", -200, 200));
        Node s = p.addNode("S", "ethernet_switch", 0, 0);

        for (int i = 0; i < 4; i++) {
            p.addLink(terminals.get(i), s, 0, i);
        }
    }
}
