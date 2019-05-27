package lib;
import java.lang.Math.*;

public class Structure
{
    private Controller m_controller;
    private String m_projectName;
    private int m_nbrNode;
    private String m_name;
    private Node[] m_nodeTab;
    private Node m_junctionNode;

    private int m_xPosCenter;
    private int m_yPosCenter;

    private int[] m_xPosNodes;
    private int[] m_yPosNodes;

    public Structure(String name,Controller controller, String projectName, int nbrNode, int xPos, int yPos)
    {
        m_controller = controller;
        m_projectName = projectName;
        m_nbrNode = nbrNode;
        m_name = name;
        m_nodeTab = new Node[m_nbrNode];

        m_xPosCenter = xPos;
        m_yPosCenter = yPos;

        m_xPosNodes = new int[]{m_xPosCenter,m_xPosCenter+100,m_xPosCenter+100,m_xPosCenter+100,m_xPosCenter,m_xPosCenter-100,m_xPosCenter-100,m_xPosCenter-100};
        m_yPosNodes = new int[]{m_xPosCenter+100,m_xPosCenter+100,m_xPosCenter,m_xPosCenter-100,m_xPosCenter-100,m_xPosCenter-100,m_xPosCenter,m_xPosCenter+100};
    }

    public void generateStructure()
    {
        //Create junction node
        m_controller.getProject(m_projectName)
                .addNode(m_name, "ethernet_switch", m_xPosCenter, m_yPosCenter);

        m_junctionNode = m_controller.getProject(m_projectName).getNode(m_name);

        for(int j = 0;j<m_nbrNode;j++)
        {
            m_nodeTab[j] = m_controller.getProject(m_projectName).addNode(m_name+j,"vpcs",m_xPosNodes[j], m_yPosNodes[j]).getNode(m_name+j);
        }

        int i = 0;

        //Create links
        for (Node n : m_nodeTab)
        {
            m_controller
                    .getProject(m_projectName)
                    .addLink(n, m_junctionNode, 0, i);
            i++;
        }
    }

    public void connectNode(Node node, int intPort)
    {
        if(m_nbrNode + 1 < 8)
        {
            m_nbrNode++;
            m_controller.getProject(m_projectName).addLink(node, m_junctionNode, intPort, m_nbrNode-1);
        }
    }

    public Node getJunctionNode()
    {
        return m_controller.getProject(m_projectName).getNode(m_name);
    }
}
