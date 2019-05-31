package lib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Link extends RestEntity{

    Link(String base_url, String entity_id){
        super(base_url);
        m_entity_id = entity_id;
    }

    Link(String base_url, Node n1, Node n2, int p1, int p2){
        super(base_url);
        JSONObject req = new JSONObject()
                .put("nodes", new JSONArray()
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", n1.getTrueId())
                                .put("port_number", p1))
                        .put(new JSONObject()
                                .put("adapter_number", 0)
                                .put("node_id", n2.getTrueId())
                                .put("port_number", p2)));
        JSONObject res = RequestHelper.post(base_url, req);
        m_entity_id = res.getString("link_id");
    }


    public Node getFirstNode() {
        String firstNodeId = this.get()
                .getJSONArray("nodes")
                .getJSONObject(0)
                .getString("node_id");
        String projectId = this.get()
                .getString("project_id");
        return new Controller(m_base_url.split(":3080")[0].split("//")[1])
                .getProjectById(projectId)
                .getNodeByID(firstNodeId);
    }

    public Node getSecondNode() {
        String secondNodeId = this.get()
                .getJSONArray("nodes")
                .getJSONObject(1)
                .getString("node_id");
        String projectId = this.get()
                .getString("project_id");
        return new Controller(m_base_url.split(":3080")[0].split("//")[1])
                .getProjectById(projectId)
                .getNodeByID(secondNodeId);
    }

    public int getFirstNodePort() {
        int firstPort = this.get()
                .getJSONArray("nodes")
                .getJSONObject(0)
                .getInt("port_number");

        return firstPort;
    }

    public int getSecondNodePort() {
        int firstPort = this.get()
                .getJSONArray("nodes")
                .getJSONObject(1)
                .getInt("port_number");

        return firstPort;
    }

    public String getLinkID(){
        return m_entity_id;
    }


    // Return information about a link in JSON format
    // ex : GET /v2/projects/7ab38231-b22e-4fd0-aee1-cb41c268c55f/links/ HTTP/1.1
    public JSONObject LinkID(Link l)
    {
        JSONObject jsonResult = RequestHelper.get(m_base_url + "/" + l.getLinkID());
        return jsonResult;
    }

    //Return the list of filters available for a specific link in JSON format
    // ex : GET /v2/projects/101dc781-9625-4f4d-8dfc-68b50b9f5936/links/f7746aee-b800-4e61-937f-ef07a81384c4/available_filters HTTP/1.1
    public List<String> available_filters() {

        List<String> name =new ArrayList<String>();
        int i=0;
        boolean b = true;
        while (b){
            try {
                name.add( RequestHelper.getArray(m_base_url + "/" + this.m_entity_id + "/available_filters")
                        .getJSONObject(i)
                        .getString("name"));
                i++;
            } catch (JSONException e) {
                b=false;
            }
        }
            return name;
        }



    //Start capture on a link instance. By default we consider it as an Ethernet link
    // ex : POST /v2/projects/405691bc-6538-4f45-b65f-e3309c3543ea/links/4fc4bbd0-38a2-4360-82ad-658636472f8a/start_capture HTTP/1.1
    public JSONObject start_capture(){
        JSONObject jsonResult = RequestHelper.post(m_base_url + "/" + m_entity_id + "/start_capture");
        return jsonResult;
    }

    //Stop capture on a link instance
    // ex : POST /v2/projects/405691bc-6538-4f45-b65f-e3309c3543ea/links/4fc4bbd0-38a2-4360-82ad-658636472f8a/start_capture HTTP/1.1
    public JSONObject stop_capture(){
        JSONObject jsonResult = RequestHelper.post(m_base_url + "/" + m_entity_id + "/stop_capture");
        return jsonResult;
    }
}
