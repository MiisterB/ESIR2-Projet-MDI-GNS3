package lib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Link extends RestEntity{
    private String m_id_node1;
    private String m_id_node2;

    @Override
    RestEntity createInstance(List<String> params) {
        return new Link(m_base_url, params.get(0), params.get(1));
    }

    @Override
    RestEntity getInstance(JSONObject e) {
        return new Link(m_base_url, e.getString("link_id"));
    }

    Link(String base_url){
        super(base_url);
    }

    private Link(String base_url, String id1, String id2){
        super(base_url, "");
        m_id_node1 = id1;
        m_id_node2 = id2;
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", id1)
                                .put("port_number", 0))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", id2)
                                .put("port_number", 0)));
        JSONObject res = super.create(req);
        m_entity_id = res.getString("link_id");
    }

    private Link(String base_url,  String entity_id){
        super(base_url, "", entity_id);
    }
}
