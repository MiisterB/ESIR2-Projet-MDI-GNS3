
import lib.Controller;
import lib.Link;
import lib.Project;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        if( l.isEmpty()){
            assertNull(l);
        }
        else {
            assertEquals(project.getNode("n1"), l.get(0).getFirstNode());

        }
        project.delete();
    }

}
