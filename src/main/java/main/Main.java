package main;

import lib.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

//        controller.getProject("test").addNode("N2", "vpcs");
//        controller.getProject("test").addNode("N3", "vpcs");
//        controller.getProject("test").addNode("N4", "vpcs");
//        controller.getProject("test").addNode("N5", "vpcs");

        controller.getProject("test").getNodes().forEach(n -> System.out.println(n.getName() + " : " + n.getTrueId()));

        controller.getProject("test").addLink(
                controller.getProject("test").getNode("N1"),
                controller.getProject("test").getNode("N3")
        );
        controller.getProject("test").addLink(
                controller.getProject("test").getNode("N2"),
                controller.getProject("test").getNode("N4")
        );

        System.out.println();
        controller.getProject("test").getLinks().forEach(l -> System.out.println(l.getId()));
    }
}
