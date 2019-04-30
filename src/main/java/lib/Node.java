package lib;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Node extends RestEntity{

    private String m_name;
    private String m_node_type;

    Node(String base_url){
        restTemplate = new RestTemplate();
        m_base_url = base_url;
    }

    private Node(String base_url, String name, String node_type){
        restTemplate = new RestTemplate();
        m_base_url = base_url;
        m_name = name;
        m_node_type = node_type;

        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local");
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
    }

    private Node(String base_url, String name, String node_type, String entity_id){
        restTemplate = new RestTemplate();
        m_base_url = base_url;
        m_name = name;
        m_node_type = node_type;
        m_entity_id = entity_id;
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

    @Override
    RestEntity createInstance(List<String> params) {
        return new Node(m_base_url, params.get(0), params.get(1));
    }

    @Override
    RestEntity getInstance(JSONObject e) {
        return new Node(m_base_url, e.getString("name"), e.getString("node_type"), e.getString("node_id"));
    }
}
