package main;

import lib.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

        controller.getProjects().forEach(p -> System.out.println(p.getName() + " : " + p.getId()));

        controller.deleteProject("test");

        controller.addProject("test").addNode("N1", "vpcs");
        controller.getProject("test").addNode("N2", "vpcs");
        controller.getProject("test").addNode("N3", "vpcs").delete();
        controller.getProject("test").getNode("N2").delete();
        controller.getProject("test").getNodes().forEach(n -> System.out.println(n.getName() + " : " + n.getId()));

        System.out.println();
        controller.getProjects().forEach(p -> System.out.println(p.getName() + " : " + p.getId()));
    }
}
