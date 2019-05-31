
import lib.Controller;
import lib.Link;
import lib.Project;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMethodesLink {

    /*
    Test pour la classe link

     */

    @Test
    //tout tester d'un coup
    public void main(){
        testGetFirstNode();
        testGetSecondNode();
        testGetFirstPort();
        testGetSecondPort();
        testFilterAvailable();
        testGetFirstNode2();
        testGetFirstPort2();
        testGetSecondNode2();
        testGetSecondPort2();
    }

    Controller controller = new Controller("148.60.11.161");
    Project project = creationProject();
    List<Link> l = getLinks(project);

    public Project creationProject(){

        controller.deleteProject("testLink");
        Project project = controller.addProject("testLink")
                .getProject("testLink");
        return project;
    }
    public List<Link> getLinks(Project project){
        List<Link> l= controller
                .getProject("testLink")
                .addNode("n1", "vpcs", 0, 0)
                .addNode("n2", "vpcs", 10, 10)
                .addLink(project.getNode("n1"), project.getNode("n2"))
                .getLinks();
        return l;
    }

    @Test
    public void testGetFirstNode() {

        assertEquals("n1", l.get(0).getFirstNode().getName());
        //project.delete();
    }

    @Test
    public void testGetFirstNode2() {
        assertNotEquals("n2", l.get(0).getFirstNode().getName());
        //project.delete();
    }


    @Test
    public void testGetSecondNode() {

        assertEquals("n2", l.get(0).getSecondNode().getName());
       //project.delete();
    }

    @Test
    public void testGetSecondNode2() {
        assertNotEquals("n1", l.get(0).getSecondNode().getName());
        //project.delete();
    }

    @Test
    public void testGetFirstPort() {
        assertEquals(0, l.get(0).getFirstNodePort());
        //project.delete();
    }

    @Test
    public void testGetFirstPort2() {
        assertNotEquals(1, l.get(0).getFirstNodePort());
        //project.delete();
    }

    @Test
    public void testGetSecondPort() {
        assertEquals(0, l.get(0).getFirstNodePort());
        //project.delete();
    }

    @Test
    public void testGetSecondPort2() {

        assertNotEquals(1, l.get(0).getFirstNodePort());
        //project.delete();
    }

    @Test
    public void testFilterAvailable() {

        List<String> lo = l.get(0).available_filters();
        while(!lo.isEmpty()){
            System.out.print(lo.get(lo.size()-1));
            lo.remove(lo.get(lo.size()-1));
        }

    }








}
