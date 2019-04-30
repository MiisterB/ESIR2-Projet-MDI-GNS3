package lib;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Project extends RestEntity{

    private String m_name;
    private EntityManager<Node> nodes;
    private EntityManager<Link> links;

    private void initializeManagers(String base_url){
        String base_url_node = base_url + "/" + getTrueId() + "/nodes";
        String base_url_link = base_url + "/" + getTrueId() + "/links";
        nodes = new EntityManager<>(base_url_node, new Node(base_url_node));
        links = new EntityManager<>(base_url_link, new Link(base_url_link));
    }

    Project(String base_url){
        restTemplate = new RestTemplate();
        m_base_url = base_url;

        initializeManagers(base_url);
    }

    private Project(String base_url, String name) {
        restTemplate = new RestTemplate();
        m_base_url = base_url;
        m_name = name;

        JSONObject req = new JSONObject().put("name",getName());
        JSONObject res = super.create(req);
        m_entity_id = res.getString("project_id");

        initializeManagers(base_url);
    }

    private Project(String base_url, String name, String entity_id){
        restTemplate = new RestTemplate();
        m_base_url = base_url;
        m_name = name;
        m_entity_id = entity_id;

        initializeManagers(base_url);
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
        return new Project(m_base_url, params.get(0));
    }
    @Override
    RestEntity getInstance(JSONObject e) {
        return new Project(m_base_url, e.getString("name"), e.getString("project_id"));
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

    public Link addLink(Node n1, Node n2){
        List<String> params = new ArrayList();
        params.add(n1.getTrueId());
        params.add(n2.getTrueId());
        return links.addEntity(params);
    }

    public Link getLink(String id){
        return links.getEntity(id);
    }

    public void deleteLink(String id){
        links.deleteEntity(id);
    }
}
