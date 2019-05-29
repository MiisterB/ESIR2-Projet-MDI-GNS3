package lib;
import org.json.JSONObject;

import java.io.IOException;

public class Node extends RestEntity{

    private String m_name;
    private String m_node_type;
    private int m_x, m_y;

    Node(String base_url, String name, String node_type, String entity_id){
        super(base_url);
        m_name = name;
        m_node_type = node_type;
        m_entity_id = entity_id;
    }

    Node(String base_url, String name, String node_type, int x, int y){
        super(base_url);
        m_x = x;
        m_y = y;
        m_name = name;
        m_node_type = node_type;

        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local")
                .put("x", m_x)
                .put("y", m_y);
        JSONObject res = RequestHelper.post(base_url, req);
        m_entity_id = res.getString("node_id");
    }

    public String getName() {
        return m_name;
    }

    public String getId() {
        return m_name;
    }

    public String getTrueId() {
        return m_entity_id;
    }

    //Fonction qui duplique l'instance d'un noeud à l'emplacement (x,y)
    public Node duplicateNode(int x, int y)
    {
        JSONObject req = new JSONObject()
                .put("x", x)
                .put("y", y)
                .put("z",0);

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/duplicate", req);
        return this;
    }

    //Fonction qui recharge l'instance d'un noeud
    public Node reloadNode()
    {
        RequestHelper.post(m_base_url + "/" + getTrueId() + "/reload");
        return this;
    }

    //Fonction qui lance le noeud
    public Node startNode()
    {
        RequestHelper.post(m_base_url + "/" + getTrueId() + "/start");
        return this;
    }

    //Fonction qui arrête le noeud
    public Node stopNode()
    {
       RequestHelper.post(m_base_url + "/" + getTrueId() + "/stop");
        return this;
    }

    public Node sendCmd(String  cmd){
        String ip = m_base_url
                .split(":3080")[0]
                .split("//")[1];
        JSONObject n = RequestHelper.get(m_base_url + "/" + getTrueId());
        int port = n.getInt("console");

        try {
            CmdHelper.write(ip, port, cmd);
        } catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
        return this;
    }

    public String sendCmdAndWaitResp(String cmd){
        String ip = m_base_url
                .split(":3080")[0]
                .split("//")[1];
        JSONObject n = RequestHelper.get(m_base_url + "/" + getTrueId());
        int port = n.getInt("console");

        String result = "";

        try {
            CmdHelper.writeAndRead(ip, port, cmd);
        } catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
        return result;
    }

    public String readConsole (){
        String ip = m_base_url
                .split(":3080")[0]
                .split("//")[1];
        JSONObject n = RequestHelper.get(m_base_url + "/" + getTrueId());
        int port = n.getInt("console");
        String result = "";

        try {
            result = CmdHelper.read(ip, port, false);
        } catch (IOException | InterruptedException e ) {
            e.printStackTrace();
        }
        return result;

    }
}
