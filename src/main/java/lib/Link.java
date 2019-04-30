package lib;

import org.json.JSONArray;
import org.json.JSONObject;

public class Link extends RestEntity{
    private String m_id_node1;
    private String m_id_node2;

    Link(String base_url, String id1, String id2){
        super(base_url);
        m_id_node1 = id1;
        m_id_node2 = id2;
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_id_node1)
                                .put("port_number", 0))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_id_node2)
                                .put("port_number", 0)));
        JSONObject res = super.create(req);
        m_entity_id = res.getString("link_id");
    }

    Link(String base_url, String entity_id){
        super(base_url);
        m_entity_id = entity_id;
    }
}
