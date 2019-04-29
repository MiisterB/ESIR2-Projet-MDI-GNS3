package lib;

import org.json.JSONObject;

public class Project extends RestEntity {

    Project(String base_url, String name) {
        super(base_url + "/v2/projects", name);
        create();
    }

    Project(String base_url, String name, String entity_id){
        super(base_url + "/v2/projects", name, entity_id);
    }

    private void create() {
        JSONObject req = new JSONObject().put("name",getName());
        JSONObject res = super.create(req);
        m_entity_id = res.getString("project_id");
    }

    public Node addNode(String name, String node_type){
        return new Node(m_base_url + "/" + getId(), name, node_type);
    }
}
