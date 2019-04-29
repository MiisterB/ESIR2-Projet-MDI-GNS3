package lib;

import org.json.JSONObject;

class Node extends RestEntity
{
    private String m_node_type;

    Node(String base_url, String name, String node_type){
        super(base_url + "/nodes", name);
        m_node_type = node_type;
        create();
    }

    private void create() {
        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("node_type", m_node_type)
                .put("compute_id", "local");
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
    }
}
