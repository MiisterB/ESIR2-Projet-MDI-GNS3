import org.json.JSONObject;

public class Project extends Entity{

    protected String makeUrl(String path){
        return super.makeUrl("/v2/projects") + path;
    }

    public Project(String name) {
        super(name);
        create();
    }

    public void create() {
        JSONObject req = new JSONObject().put("name",getName());
        JSONObject res = super.create(req);
        m_entity_id = res.getString("project_id");
    }

    public void delete()
    {
        super.delete();
    }

    public Node addNode(String name, String node_type){
        return new Node(getEntityId(), name, node_type);
    }
}
