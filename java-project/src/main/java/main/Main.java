package main;

import lib.Controller;
import lib.Node;
import lib.Structure;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("192.168.56.103");

        String project_name = "Test_cmds";

        Node n = controller
                .addProject(project_name)
                .getProject(project_name)
                .openProject()
                .addNode("N", "vpcs")
                .getNode("N")
                .startNode();

        String result = n
                .sendCmd("!mkdir test")
                .sendCmdAndReadUntil("!ls", "root@N:~#", 2000);

        System.out.println(result);


   /*     String networkConfig = controller
                .addProject(project_name)
                .getProject(project_name)
                .openProject()
                .addNode("V", "vpcs")
                .getNode("V")
                .startNode()
                .setNetworkConfig(
                        "# This is a custom network config set by GNS3 fluent API\\n" +
                                "ip 192.168.1.1 255.0.0.0\\n" +
                                "set pcname V2\\n")
                .getNetworkConfig();
        System.out.println(networkConfig);

        networkConfig = controller
                .getProject(project_name)
                .openProject()
                .addNode("N", "Ubuntu Docker Guest")
                .getNode("N")
                .startNode()
                .setNetworkConfig(
                        "# This is a custom network config set by GNS3 fluent API\n" +
                                "auto eth0\n" +
                                "iface eth0 inet static\n" +
                                "\taddress 192.168.0.2\n" +
                                "\tnetmask 255.255.255.0\n" +
                                "\tgateway 192.168.0.1\n" +
                                "\tup echo nameserver 192.168.0.1 > /etc/resolv.conf\n")
                .getNetworkConfig();
        System.out.println(networkConfig);*/
    }

    public static void demoStructure() {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "Structure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1", "vpcs", -600, 0).getNode("N1");

        Structure structure1 = new Structure("A", controller, project_name, 7, "ethernet_switch", -500, 0, "Right");
        structure1.connectNode(n1, 0);

        Structure structure2 = new Structure("B", controller, project_name, 4, "ethernet_switch", -200, 0, "Star");
        Structure structure3 = new Structure("C", controller, project_name, 3, "ethernet_switch", 200, 0, "Left");
        Structure structure4 = new Structure("D", controller, project_name, 5, "ethernet_switch", 400, 0, "Right");
        structure3.connectNode(structure4.getJunctionNode(), 7);
    }
}
