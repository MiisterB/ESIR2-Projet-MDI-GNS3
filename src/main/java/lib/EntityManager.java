package lib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class EntityManager<T extends RestEntity> {
    private EntityTypes m_entity_type;
    private String m_base_url;

    private void createApplianceNode(List<Object> params){
        boolean applianceCreated = false;
        JSONArray res = RequestHelper.getArray(m_base_url.split("/v2")[0] + "/v2/appliances");
        for (int i = 0; i < res.length(); i++) {
            JSONObject e = res.getJSONObject(i);
            if (e.getString("name").equals(params.get(1))) {
                JSONObject req = new JSONObject()
                        .put("x", (params.size() == 2) ? 0 : (int) params.get(2))
                        .put("y", (params.size() == 2) ? 0 : (int) params.get(3));

                JSONObject response = RequestHelper.post(m_base_url.split("/nodes")[0] + "/appliances/" + e.getString("appliance_id"), req);
                String applianceID = response.getString("node_id");
                JSONArray nodes = RequestHelper.getArray(m_base_url);
                for (int j = 0; j < nodes.length(); j++) {
                    String nodeID = nodes.getJSONObject(j).getString("node_id");
                    if (nodeID.equals(applianceID)) {
                        RequestHelper.put(m_base_url + "/" + nodeID, new JSONObject().put("name", params.get(0)));
                    }
                }
                applianceCreated = true;
            }
        }
        if (!applianceCreated){
            System.out.println("The appliance '" + params.get(1) + "' do not exist or is not yet installed ...");
        }
    }


    EntityManager(String base_url, EntityTypes type) {
        m_base_url = base_url;
        m_entity_type = type;
    }

    List<T> getEntities() {
        List<T> entities = new ArrayList<>();
        JSONArray res = RequestHelper.getArray(m_base_url);
        for (int i = 0; i < res.length(); i++) {
            JSONObject e = res.getJSONObject(i);
            T result;
            switch (m_entity_type) {
                case Project:
                    result = (T) new Project(m_base_url, e.getString("name"), e.getString("project_id"));
                    break;
                case Node:
                    result = (T) new Node(m_base_url, e.getString("name"), e.getString("node_type"), e.getString("node_id"));
                    break;
                case Link:
                    result = (T) new Link(m_base_url, e.getString("link_id"));
                    break;
                default:
                    return null;
            }
            entities.add(result);
        }
        return entities;
    }

    void addEntity(List<Object> params) {
        for (T e : getEntities()) {
            if (e.getId().equals(params.get(0))) {
                System.err.println("L'entité " + params.get(0) + " existe déjà !");
                return;
            }
        }
        switch (m_entity_type) {
            case Project:
                new Project(m_base_url, (String) params.get(0));
                break;

            case Node:
                if (!Arrays.toString(BuiltInNodes.values()).contains((String) params.get(1))) {
                    createApplianceNode(params);
                }
                else {
                    new Node(m_base_url,
                            (String) params.get(0),
                            (String) params.get(1),
                            (params.size() == 2) ? 0 : (int) params.get(2),
                            (params.size() == 2) ? 0 : (int) params.get(3));
                }
                break;

            case Link:
                new Link(m_base_url,
                        (Node) params.get(0),
                        (Node) params.get(1),
                        (params.size() == 2) ? 0 : (int) params.get(2),
                        (params.size() == 2) ? 0 : (int) params.get(3));
                break;
        }
    }

    T getEntity(String id) {
        for (T e : getEntities()) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        System.err.println("L'entité " + id + " n'existe pas !");
        return null;
    }

    void deleteEntity(String id) {
        for (T e : getEntities()) {
            if (e.getId().equals(id)) {
                e.delete();
                return;
            }
        }
        System.err.println("L'entité " + id + " n'existe pas !");
    }
}
