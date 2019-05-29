import lib.Controller;
import lib.Link;
import lib.Node;
import lib.Project;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertEquals;

public class Test2 {

    @Test
    public void testNodesAndLinksManagement() throws InterruptedException {

        Controller controller = new Controller("148.60.11.161");
        controller.deleteProject("testAuto");
        Project project = controller.addProject("testAuto")
                .getProject("testAuto");

        List<Node> nodes = project.getNodes();
        List<Link> links = project.getLinks();

        controller
                .getProject("testAuto")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 0, 0)
                .addNode("n3", "vpcs", 0, 0)
                .addNode("n4", "vpcs", 0, 0);

        Node n1 = project.getNode("n1");
        Node n2 = project.getNode("n2");
        Node n3 = project.getNode("n3");
        Node n4 = project.getNode("n4");

        project.addLink(n1, n3);
        project.addLink(n2, n4);

        project.getLinks().forEach(l -> l.delete());
        project.getNodes().forEach(n -> n.delete());

        assertEquals(nodes, project.getNodes());
        assertEquals(links, project.getLinks());

        project.delete();
    }

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
}
