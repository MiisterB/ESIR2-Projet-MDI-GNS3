package lib;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Project extends RestEntity{

    private EntityManager<Node> nodes;
    private EntityManager<Link> links;

    @Override
    RestEntity createInstance(List<String> params) {
        return new Project(m_base_url, params.get(0));
    }
    @Override
    RestEntity getInstance(JSONObject e) {
        return new Project(m_base_url, e.getString("name"), e.getString("project_id"));
    }

    Project(String base_url){
        super(base_url);
        String base_url_node = base_url + "/" + getId() + "/nodes";
        nodes = new EntityManager<>(base_url_node, new Node(base_url_node));
    }

    private Project(String base_url, String name) {
        super(base_url, name);
        String base_url_node = base_url + "/" + getId() + "/nodes";
        nodes = new EntityManager<>(base_url_node, new Node(base_url_node));
        JSONObject req = new JSONObject().put("name",getName());
        JSONObject res = super.create(req);
        m_entity_id = res.getString("project_id");
    }

    private Project(String base_url, String name, String entity_id){
        super(base_url, name, entity_id);
        String base_url_node = base_url + "/" + getId() + "/nodes";
        nodes = new EntityManager<>(base_url_node, new Node(base_url_node));
    }

    public List<Node> getNodes(){
        return nodes.getEntities();
    }

    public Node addNode(String name, String type){
        List<String> params = new ArrayList();
        params.add(name);
        params.add(type);
        return nodes.addEntity(params);
}

    public Node getNode(String name){
        return nodes.getEntity(name);
    }

    public void deleteNode(String name){
        nodes.deleteEntity(name);
    }

    public List<Link> getLinks(){
        return links.getEntities();
    }

    public Link addLink(String name, String type){
        List<String> params = new ArrayList();
        params.add(name);
        params.add(type);
        return links.addEntity(params);
    }

    public Link getLink(String name){
        return links.getEntity(name);
    }

    public void deleteLink(String name){
        links.deleteEntity(name);
    }
}
