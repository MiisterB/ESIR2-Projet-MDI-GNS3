import org.json.JSONObject;

public class Project extends Entity{

    public Project(String name) {
        super(name);
        m_base_url += "/v2/projects";
        create();
    }

    public Project(String name, String entity_id){
        super(name, entity_id);
        m_base_url += "/v2/projects";
    }

    protected void create() {
        JSONObject req = new JSONObject().put("name",getName());
        JSONObject res = super.create(req);
        m_entity_id = res.getString("project_id");
    }

    public Node addNode(String name, String node_type){
        return new Node(m_base_url + "/" + getEntityId(), name, node_type);
    }
}
