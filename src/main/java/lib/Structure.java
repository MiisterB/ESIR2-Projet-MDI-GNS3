package lib;
import java.lang.Math.*;

public class Structure
{
    protected String m_name;
    protected Controller m_controller;
    protected String m_projectName;
    protected String m_nodeType;
    protected int m_nbrNode;
    protected Node[] m_nodeTab;

    protected Node m_junctionNode;
    protected int m_xPosCenter;
    protected int m_yPosCenter;

    protected int[] m_xPosNodes;
    protected int[] m_yPosNodes;
    protected String m_orientation;

    //Constructor
    public Structure(String name,Controller controller, String projectName, int nbrNode, String nodeType, int xPos, int yPos, String orientation)
    {
        m_controller = controller;
        m_projectName = projectName;
        m_nodeType = nodeType;
        m_nbrNode = nbrNode;
        m_name = name;
        m_nodeTab = new Node[m_nbrNode];

        m_xPosCenter = xPos;
        m_yPosCenter = yPos;

        m_orientation = orientation;

        switch(m_orientation)
        {
            case "Right" :
            {
                m_xPosNodes = new int[]{100,100,100,100,100,100,100,100};
                m_yPosNodes = new int[]{-350,-250,-150,-50,50,150,250,350};
                generateStructure();
                break;
            }
            case "Up" :
            {
                m_xPosNodes = new int[]{-350,-250,-150,-50,50,150,250,350};
                m_yPosNodes = new int[]{100,100,100,100,100,100,100,100};
                generateStructure();
                break;
            }
            case "Down":
            {
                m_xPosNodes = new int[]{-350,-250,-150,-50,50,150,250,350};
                m_yPosNodes = new int[]{-100,-100,-100,-100,-100,-100,-100,-100};
                generateStructure();
                break;
            }
            case "Left":
            {
                m_xPosNodes = new int[]{-100,-100,-100,-100,-100,-100,-100,-100};
                m_yPosNodes = new int[]{-350,-250,-150,-50,50,150,250,350};
                generateStructure();
                break;
            }
            default : //Star
            {
                System.out.println("Default orientation is Star (you can change it by choosing Left, Right, Up or Down)");
                generateStarOrientation();
                generateStructure();
            }
        }
    }

    //Constructor with default orientation of nodes (Left)
    public Structure(String name,Controller controller, String projectName, int nbrNode, String nodeType, int xPos, int yPos)
    {
        m_controller = controller;
        m_projectName = projectName;
        m_nodeType = nodeType;
        m_nbrNode = nbrNode;
        m_name = name;
        m_nodeTab = new Node[m_nbrNode];

        m_xPosCenter = xPos;
        m_yPosCenter = yPos;

        generateStarOrientation();

        generateStructure();
    }

    //Constructor with default position of the center of the structure and default orientation of nodes (Left) and default type of node (vpcs)
    public Structure(String name,Controller controller, String projectName, int nbrNode)
    {
        m_controller = controller;
        m_projectName = projectName;
        m_nodeType = "vpcs";
        m_nbrNode = nbrNode;
        m_name = name;
        m_nodeTab = new Node[m_nbrNode];

        m_xPosCenter = 0;
        m_yPosCenter = 0;

        generateStarOrientation();

        generateStructure();
    }
    protected void generateStructure()
    {
        //Create junction node
        m_controller.getProject(m_projectName)
                .addNode(m_name, "ethernet_switch", m_xPosCenter, m_yPosCenter);

        m_junctionNode = m_controller.getProject(m_projectName).getNode(m_name);

        //Create nodes
        createNodes();

        //Create links
        createLinks();
    }

    protected void createNodes()
    {
        for(int j = 0;j<m_nbrNode;j++)
        {
            m_nodeTab[j] = m_controller.
                    getProject(m_projectName).
                    addNode(m_name+j,m_nodeType,m_xPosCenter + m_xPosNodes[j], m_yPosCenter + m_yPosNodes[j]).
                    getNode(m_name+j);
        }
    }

    protected void createLinks()
    {
        int i = 0;
        for (Node n : m_nodeTab)
        {
            m_controller
                    .getProject(m_projectName)
                    .addLink(n, m_junctionNode, 0, i);
            i++;
        }

        //If we have a nodeType which can handle more than one port, we create additionnal links between each nodes (except the first one and the last one)
        if(m_nbrNode > 1 && m_nodeType != "vpcs")
        {
            int j;
            for(j = 0; j<m_nodeTab.length-1;j++)
            {
                m_controller.getProject(m_projectName).addLink(m_nodeTab[j],m_nodeTab[j+1],1,2);
            }
            j = m_nodeTab.length-1;

            //If we have an orientation like a star, we create a link between the first and the last node
            if(m_orientation != "Right" && m_orientation != "Left" & m_orientation != "Up" && m_orientation != "Down")
            {
                m_controller.getProject(m_projectName).addLink(m_nodeTab[j],m_nodeTab[0],1,2);
            }
        }
    }

    protected void generateStarOrientation()
    {
        if(m_nbrNode<=5)
        {
            m_xPosNodes = new int[]{100,200,100,-200,-100,-200,200,-100};
            m_yPosNodes = new int[]{200,-100,-200,-100,200,100,100,-200};
        }
        else
        {
            m_xPosNodes = new int[]{100,200,200,100,-100,-200,-200,-100};
            m_yPosNodes = new int[]{200,100,-100,-200,-200,-100,100,200};
        }
    }

    public void connectNode(Node node, int intPort)
    {
        if(m_nbrNode < 8)
        {
            m_nbrNode++;
            m_controller.getProject(m_projectName).addLink(node, m_junctionNode, intPort, m_nbrNode-1);
        }
        else
        {
            System.out.println("Erreur lors de la création de la liaison");
        }
    }


    public int getTotalNumberOfNode()
    {
        return m_nbrNode+1;
    }

    public int[] getxPosNodes()
    {
        return m_xPosNodes;
    }

    public int[] getyPosNodes()
    {
        return m_yPosNodes;
    }

    public String getNodeType()
    {
        return m_nodeType;
    }

    public String getOrientation()
    {
        return m_orientation;
    }
}
