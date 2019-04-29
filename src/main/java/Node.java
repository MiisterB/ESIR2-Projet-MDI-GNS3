import org.json.JSONObject;

public class Node extends Entity
{
    String m_project_id;
    String m_node_type;

    protected String makeUrl(String path){
        return super.makeUrl("/v2/projects/") + m_project_id + "/nodes" + path;
    }

    public Node(String project_id, String name, String node_type){
        super(name);
        m_project_id = project_id;
        m_node_type = node_type;
        create();
    }

    public void create() {
        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local");
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
    }

    public void delete()
    {
        super.delete();
    }
}
