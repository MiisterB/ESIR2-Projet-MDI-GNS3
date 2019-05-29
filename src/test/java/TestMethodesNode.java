import lib.Controller;
import lib.Link;
import lib.Node;
import lib.Project;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestMethodesNode {


    /*
    Test méthode de la classe noeud
     */

    @Test
    public void testErrorWrongNodeType() throws InterruptedException {
        try
        {
            Controller controller = new Controller("148.60.11.161");
            controller.deleteProject("testAuto");
            Project project = controller.addProject("testAuto")
                    .getProject("testAuto");

            controller
                    .getProject("testAuto")
                    .addNode("n1", "azeadazda", 0, 0);


            project.delete();
        }
        catch(Exception e)
        {
            assertEquals("org.springframework.web.client.HttpClientErrorException: 400 Bad Request",e);
            //assertThat(e).isIn("org.springframework.web.client.HttpClientErrorException: 400 Bad Request","org.springframework.web.client.HttpClientErrorException<org.springframework.web.client.HttpClientErrorException: 400 Bad Request>");
        }
    }

    @Test
    public void testGetName_positif() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0);

        assertEquals("n1", project.getNode("n1").getName());
        project.delete();
    }

    @Test
    public void testGetName_negatif() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        String name = controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .getNode("n1")
                .getName();

        assertNotEquals("ZAZA", name);
        project.delete();

    }

    @Test
    public void testGetId_positif() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0);

        assertEquals("n1", project.getNode("n1").getId());
        project.delete();

    }

    @Test
    public void testGetId_negatif() {
        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0);

        assertNotEquals("kjfv", project.getNode("n1").getId());
        project.delete();
    }


    @Test
    public void testDuplicateNodes() {

        Controller controller = new Controller("148.60.11.161");


        controller.deleteProject("TestDuplicateNode");
        Node node = controller
                .addProject("TestDuplicateNode")
                .getProject("TestDuplicateNode")
                .addNode("V", "vpcs", 0, 0)
                .getNode("V")
                .duplicateNode(200,200);

        Project project = controller
                .getProject("TestDuplicateNode");
        project.getNode("V1").duplicateNode(400,400);


        assertEquals("V1", project.getNode("V1").getName());
        assertEquals("V2", project.getNode("V2").getName());

        project.delete();
    }

    @Test
    public void testReloadNode() {

        Controller controller = new Controller("148.60.11.161");


        controller.deleteProject("TestReloadNode");
        Node node = controller
                .addProject("TestReloadNode")
                .getProject("TestReloadNode")
                .addNode("V", "vpcs", 0, 0)
                .getNode("V")
                .reloadNode();

        Project project = controller
                .getProject("TestReloadNode");

        assertEquals("V", project.getNode("V").getName());
        project.delete();
    }


    @Test
    public void testStartStop() throws InterruptedException {

        Controller controller = new Controller("148.60.11.161");


        controller.deleteProject("TestStartStop");
        Node node = controller
                .addProject("TestStartStop")
                .getProject("TestStartStop")
                .addNode("V", "vpcs", 100, 0)
                .getNode("V")
                .startNode();

        controller.getProject("TestStartStop").getNode("V").stopNode();

        Project project = controller
                .getProject("TestStartStop");

        assertEquals("V", project.getNode("V").getName());
        project.delete();
    }

    @Test
    public void testSendCmdAndWaitResp() {

        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testCmd");

        String result = controller
                .addProject("testCmd")
                .getProject("testCmd")
                .addNode("N", "vpcs")
                .getNode("N")
                .sendCmd("mkdir heyheyhey")
                .sendCmdAndWaitResp("ls");

        assertTrue(result.contains("heyheyhey"));

    }
}
