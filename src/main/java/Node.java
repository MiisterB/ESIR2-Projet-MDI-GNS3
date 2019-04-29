import org.json.JSONObject;

public class Node extends Entity
{
    private String m_node_type;

    public Node(String base_url, String name, String node_type){
        super(name);
        m_node_type = node_type;
        m_base_url  = base_url + "/nodes";
        create();
    }

    protected void create() {
        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local");
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
    }
}
