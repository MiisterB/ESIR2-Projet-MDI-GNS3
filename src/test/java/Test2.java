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
        Project projet = controller.addProject("testAuto");

        List<Node> nodes = projet.getNodes();
        List<Link> links = projet.getLinks();


        Node n1 = projet.addNode("N1", "vpcs");
        Node n2 = projet.addNode("N2", "vpcs");
        Node n3 =  projet.addNode("N3", "vpcs");;
        Node n4 = projet.addNode("N4", "vpcs");

        projet.addLink(n1, n3);
        projet.addLink(n2, n4);

        projet.getLinks().forEach(l -> l.delete());
        projet.getNodes().forEach(n -> n.delete());

        assertEquals(nodes, projet.getNodes());
        assertEquals(links, projet.getLinks());
    }
}
