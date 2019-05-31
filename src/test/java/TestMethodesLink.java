
import lib.Controller;
import lib.Link;
import lib.Project;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMethodesLink {
    /*
    Test pour la classe link

     */

    @Test
    public void testGetFirstNode() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        List<Link> l= controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 10, 10)
                .addLink(project.getNode("n1"), project.getNode("n2"))
                .getLinks();

        assertEquals("n1", l.get(0).getFirstNode().getName());
        project.delete();
    }


    @Test
    public void testGetSecondNode() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        List<Link> l= controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 10, 10)
                .addLink(project.getNode("n1"), project.getNode("n2"))
                .getLinks();

        assertEquals("n2", l.get(0).getSecondNode().getName());
       project.delete();
    }

    @Test
    public void testGetFirstPort() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        List<Link> l= controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 10, 10)
                .addLink(project.getNode("n1"), project.getNode("n2"))
                .getLinks();

        assertEquals(0, l.get(0).getFirstNodePort());
        project.delete();
    }

    @Test
    public void testFilterAvailable() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        List<Link> l= controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 10, 10)
                .addLink(project.getNode("n1"), project.getNode("n2"))
                .getLinks();

        List<String> lo = l.get(0).available_filters();
        while(!lo.isEmpty()){
            System.out.print(lo.get(lo.size()-1));
            lo.remove(lo.get(lo.size()-1));
        }

    }

    @Test
    public void testGetSecondPort() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        List<Link> l= controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 10, 10)
                .addLink(project.getNode("n1"), project.getNode("n2"))
                .getLinks();

        assertEquals(0, l.get(0).getFirstNodePort());
        //project.delete();
    }




}
