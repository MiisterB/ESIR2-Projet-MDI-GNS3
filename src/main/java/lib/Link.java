package lib;

import org.json.JSONArray;
import org.json.JSONObject;

public class Link extends RestEntity{
    private Node m_n1;
    private Node m_n2;
    private int m_port1, m_port2;

    Link(String base_url, String entity_id){
        super(base_url);
        m_entity_id = entity_id;
    }

    Link(String base_url, Node n1, Node n2){
        super(base_url);
        m_n1 = n1;
        m_n2 = n2;
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n1.getTrueId())
                                .put("port_number", 0))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n2.getTrueId())
                                .put("port_number", 0)));
        JSONObject res = super.create(req);
        m_entity_id = res.getString("link_id");
    }

    Link(String base_url, Node n1, Node n2, int p1, int p2){
        super(base_url);
        m_n1 = n1;
        m_n2 = n2;
        m_port1 = p1;
        m_port2 = p2;
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n1.getTrueId())
                                .put("port_number", m_port1))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n2.getTrueId())
                                .put("port_number", m_port2)));
        JSONObject res = super.create(req);
        m_entity_id = res.getString("link_id");
    }

    public Node getFirstNode() {
        return m_n1;
    }

    public Node getSecondNode() {
        return m_n2;
    }

    public int getFirstNodePort() {
        return m_port1;
    }

    public int getSecondNodePort() {
        return m_port2;
    }

    //Return the list of filters available for this link
    public int available_filters(){
        return 0;
    }

    //Start capture on a link instance. By default we consider it as an Ethernet link
    public int start_capture(){
        return 0;
    }

    //Stop capture on a link instance
    public int stop_capture(){
        return 0;
    }
}
