package lib;
import org.json.JSONObject;

public class Node extends RestEntity{

    private String m_name;
    private String m_node_type;

    Node(String base_url, String name, String node_type){
        super(base_url);
        m_name = name;
        m_node_type = node_type;

        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local");
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
    }

    Node(String base_url, String name, String node_type, String entity_id){
        super(base_url);
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
}
