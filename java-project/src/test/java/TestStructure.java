import lib.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestStructure
{

    //Test with the default constructor, structure with maximum bumber of node (8), we tested the total number of node present in the structure
    @Test
    public void testCreateBasicStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Structure testStructure1 = new Structure("A",controller, project_name,8);

        assertEquals(9, testStructure1.getTotalNumberOfNode());
    }

    //Test with the default constructor, structure but with more than 8 nodes
    @Test
    public void testCreateBasicStructureTooMuchNodes()
    {
        try
        {
            Controller controller = new Controller("148.60.11.161");

            String project_name = "testStructure";
            controller.deleteProject(project_name);

            controller.addProject(project_name);

            Structure testStructure1 = new Structure("A",controller, project_name,10);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Error : too much nodes in the constructor (8 is the maximum)");
        }

    }

    //Test the creation of a structure oriented to the right
    @Test
    public void testCreateRightOrientedStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Structure testStructure1 = new Structure("A",controller, project_name,8, "vpcs",0,0,"Right");

        int[] xPos = new int[]{100,100,100,100,100,100,100,100};
        int[] yPos = new int[]{-350,-250,-150,-50,50,150,250,350};

        assertTrue(Arrays.equals(xPos,testStructure1.getxPosNodes()));
        assertTrue(Arrays.equals(yPos,testStructure1.getyPosNodes()));
    }

    //Test the creation of a structure oriented to the left
    @Test
    public void testCreateLeftOrientedStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Structure testStructure1 = new Structure("A",controller, project_name,8, "vpcs",0,0,"Left");

        int[] xPos = new int[]{-100,-100,-100,-100,-100,-100,-100,-100};
        int[] yPos = new int[]{-350,-250,-150,-50,50,150,250,350};;

        assertTrue(Arrays.equals(xPos,testStructure1.getxPosNodes()));
        assertTrue(Arrays.equals(yPos,testStructure1.getyPosNodes()));
    }

    //Test the creation of a structure oriented to the up
    @Test
    public void testCreateUpOrientedStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Structure testStructure1 = new Structure("A",controller, project_name,8, "vpcs",0,0,"Up");

        int[] xPos = new int[]{-350,-250,-150,-50,50,150,250,350};
        int[] yPos = new int[]{100,100,100,100,100,100,100,100};

        assertTrue(Arrays.equals(xPos,testStructure1.getxPosNodes()));
        assertTrue(Arrays.equals(yPos,testStructure1.getyPosNodes()));
    }

    //Test the creation of a structure oriented to the down
    @Test
    public void testCreateDownOrientedStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Structure testStructure1 = new Structure("A",controller, project_name,8, "vpcs",0,0,"Down");

        int[] xPos = new int[]{-350,-250,-150,-50,50,150,250,350};
        int[] yPos = new int[]{-100,-100,-100,-100,-100,-100,-100,-100};

        assertTrue(Arrays.equals(xPos,testStructure1.getxPosNodes()));
        assertTrue(Arrays.equals(yPos,testStructure1.getyPosNodes()));
    }

    //Test the creation of a structure oriented like a star (both cases)
    @Test
    public void testCreateStarOrientedStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        //First case when nbrNodes <= 5
        Structure testStructure1 = new Structure("A",controller, project_name,4, "vpcs",-200,0,"Star");
        int[] xPos1 = new int[]{100,200,100,-200,-100,-200,200,-100};
        int[] yPos1 = new int[]{200,-100,-200,-100,200,100,100,-200};
        assertTrue(Arrays.equals(xPos1,testStructure1.getxPosNodes()));
        assertTrue(Arrays.equals(yPos1,testStructure1.getyPosNodes()));

        //Second case when nbrNodes > 5
        Structure testStructure2 = new Structure("B",controller, project_name,8, "vpcs",200,0,"Star");
        int[] xPos2 = new int[]{100,200,200,100,-100,-200,-200,-100};
        int[] yPos2 = new int[]{200,100,-100,-200,-200,-100,100,200};
        assertTrue(Arrays.equals(xPos2,testStructure2.getxPosNodes()));
        assertTrue(Arrays.equals(yPos2,testStructure2.getyPosNodes()));
    }

    //Test the creation of a structure star oriented with ethernet_switch as nodes, it should creates links between nodes (node1 to node2, node2 to node3, etc...)
    @Test
    public void testCreateStructureWithLinkedNodes()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Structure testStructure1 = new Structure("A",controller, project_name,8, "ethernet_switch",0,0,"Star");

        assertEquals("ethernet_switch", testStructure1.getNodeType());
        assertNotEquals("Right",testStructure1.getOrientation());
        assertNotEquals("Left",testStructure1.getOrientation());
        assertNotEquals("Up",testStructure1.getOrientation());
        assertNotEquals("Down",testStructure1.getOrientation());
    }

    //Test the creation of a new link between a structure and an external node
    @Test
    public void testLinkAnExternalNodeToAStructure()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1","vpcs",-200,0).getNode("N1");
        Structure testStructure1 = new Structure("A",controller, project_name,7, "vpcs",0,0,"Down");

        testStructure1.connectNode(n1,0);

        assertEquals(9,testStructure1.getTotalNumberOfNode());
    }

    //Test the creation of a new link between a structure and an external node but there is no free port on the junction node
    @Test
    public void testLinkAnExternalNodeToAStructureNegatif()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testStructure";
        controller.deleteProject(project_name);

        controller.addProject(project_name);

        Node n1 = controller.getProject(project_name).addNode("N1","vpcs",-200,0).getNode("N1");
        Structure testStructure1 = new Structure("A",controller, project_name,8, "vpcs",0,0,"Down");

        testStructure1.connectNode(n1,0);

        assertEquals(9,testStructure1.getTotalNumberOfNode());
    }

}
