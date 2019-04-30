package main;

import lib.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

        controller.getProject("testAuto").getNodes().forEach(n -> System.out.println(n.getName() + " : " + n.getTrueId()));
        controller.getProject("testAuto").getLinks().forEach(l -> System.out.println(l.getId()));
    }
}
