package lib;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Link extends RestEntity{
    private Node m_n1;
    private Node m_n2;
    private int m_port1, m_port2;

    Link(String base_url, String entity_id){
        super(base_url);
        m_entity_id = entity_id;
    }

    Link(String base_url, Node n1, Node n2){
        super(base_url);
        m_n1 = n1;
        m_n2 = n2;
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n1.getTrueId())
                                .put("port_number", 0))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n2.getTrueId())
                                .put("port_number", 0)));
        JSONObject res = super.create(req);
        m_entity_id = res.getString("link_id");
    }

    Link(String base_url, Node n1, Node n2, int p1, int p2){
        super(base_url);
        m_n1 = n1;
        m_n2 = n2;
        m_port1 = p1;
        m_port2 = p2;
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n1.getTrueId())
                                .put("port_number", m_port1))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", m_n2.getTrueId())
                                .put("port_number", m_port2)));
        JSONObject res = super.create(req);
        m_entity_id = res.getString("link_id");
    }

    /*
    Nouvelle partie implémentée
     */


    public Node getFirstNode() {
        return m_n1;
    }

    public Node getSecondNode() {
        return m_n2;
    }

    public int getFirstNodePort() {
        return m_port1;
    }

    public int getSecondNodePort() {
        return m_port2;
    }

    public String getLinkID(){return m_entity_id;}


    // Return thr list of link of the current projet
    // ex : GET /v2/projects/7ab38231-b22e-4fd0-aee1-cb41c268c55f/links HTTP/1.1
    public JSONObject Link()
    {
        String jsonResult = restTemplate.getForObject(m_base_url , String.class);
        return new JSONObject(jsonResult);
    }

    // Return information of a link
    // ex : GET /v2/projects/7ab38231-b22e-4fd0-aee1-cb41c268c55f/links HTTP/1.1
    public JSONObject LinkID(Link l)
    {
        String jsonResult = restTemplate.getForObject(m_base_url + "/" + l.getLinkID() , String.class);
        return new JSONObject(jsonResult);
    }

    //Return the list of filters available for this link in format JSON
    // ex : GET /v2/projects/101dc781-9625-4f4d-8dfc-68b50b9f5936/links/f7746aee-b800-4e61-937f-ef07a81384c4/available_filters HTTP/1.1
    public JSONObject available_filters()
    {
        String jsonResult = restTemplate.getForObject(m_base_url + "/" + m_entity_id + "/available_filters",String.class);
        return new JSONObject(jsonResult);
    }

    //Start capture on a link instance. By default we consider it as an Ethernet link
    // ex : POST /v2/projects/405691bc-6538-4f45-b65f-e3309c3543ea/links/4fc4bbd0-38a2-4360-82ad-658636472f8a/start_capture HTTP/1.1
    //{}
    public JSONObject start_capture(){
        String req = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(req,  headers);

        String jsonResult = restTemplate.postForObject(m_base_url + "/" + m_entity_id + "/start_capture",entity, String.class);
        return new JSONObject(jsonResult);
    }

    //Stop capture on a link instance
    // ex : POST /v2/projects/405691bc-6538-4f45-b65f-e3309c3543ea/links/4fc4bbd0-38a2-4360-82ad-658636472f8a/start_capture HTTP/1.1
    public JSONObject stop_capture(){
        String req = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(req,  headers);

        String jsonResult = restTemplate.postForObject(m_base_url + "/" + m_entity_id + "/stop_capture",entity, String.class);
        return new JSONObject(jsonResult);
    }

}
