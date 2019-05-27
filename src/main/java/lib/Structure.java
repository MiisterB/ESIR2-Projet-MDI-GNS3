package lib;
import java.lang.Math.*;

public class Structure
{
    private Controller m_controller;
    private String m_projectName;
    private int m_nbrNode;
    private Node m_nodeJunction;

    private int m_xPosCenter;
    private int m_yPosCenter;

    private int[] m_xPosNodes;
    private int[] m_yPosNodes;

    public Structure(Controller controller, String projectName, int nbrNode, int xPos, int yPos)
    {
        m_controller = controller;
        m_projectName = projectName;
        m_nbrNode = nbrNode;

        m_xPosCenter = xPos;
        m_yPosCenter = yPos;

        m_xPosNodes = new int[]{0,200,200,200,0,-200,-200,-200};
        m_yPosNodes = new int[]{200,200,0,-200,-200,-200,0,200};
    }

    public Structure(Controller controller, String projectName, int nbrNode, Node nodeJunction)
    {
        m_controller = controller;
        m_projectName = projectName;
        m_nbrNode = nbrNode;

        m_xPosNodes = new int[]{0,200,200,200,0,-200,-200,-200};
        m_yPosNodes = new int[]{200,200,0,-200,-200,-200,0,200};
        m_nodeJunction = nodeJunction;
    }

    public Node generateStructure()
    {
        //Create junction node
        m_controller.getProject(m_projectName)
                .addNode("S", "ethernet_switch", 0, 0);

        Node junctionNode = m_controller.getProject(m_projectName).getNode("S");

        for(int j = 0;j<m_nbrNode;j++)
        {
            double degree = (j+1)*(360/m_nbrNode);
            double radiant = Math.toRadians(degree);
            double xPos = Math.cos(radiant);
            double yPos = Math.sin(radiant);
            m_controller.getProject(m_projectName).addNode("V"+j,"vpcs",m_xPosNodes[j], m_yPosNodes[j]);
        }

        int i = 0;

        //Create links
        for (Node n : m_controller.getProject(m_projectName).getNodes()) {
            if (!n.getName().equals("S")) {
                m_controller
                        .getProject(m_projectName)
                        .addLink(n, junctionNode, 0, i);
                i++;
            }
        }

        return junctionNode;
    }
}
