package lib;

import org.json.JSONObject;

import java.util.List;

public class Node extends RestEntity{
    private String m_node_type;

    @Override
    RestEntity createInstance(List<String> params) {
        return new Node(m_base_url, params.get(0), params.get(1));
    }

    @Override
    RestEntity getInstance(JSONObject e) {
        return new Node(m_base_url, e.getString("name"), e.getString("node_type"), e.getString("node_id"));
    }

    Node(String base_url){
        super(base_url);
    }

    private Node(String base_url, String name, String node_type){
        super(base_url, name);
        m_node_type = node_type;
        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local");
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
    }

    private Node(String base_url, String name, String node_type, String entity_id){
        super(base_url, name, entity_id);
        m_node_type = node_type;
    }
}
