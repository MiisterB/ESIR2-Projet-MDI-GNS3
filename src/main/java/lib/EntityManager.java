package lib;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

class EntityManager<T extends RestEntity> {
    private RestTemplate restTemplate;
    private List<T> entities;
    private String m_base_url;
    private String m_entity_type;

    EntityManager(String base_url, String type) {
        restTemplate = new RestTemplate();
        entities = new ArrayList<>();
        m_base_url = base_url;
        m_entity_type = type;
    }

    List<T> getEntities(){
        entities.clear();
        String jsonResult = restTemplate.getForObject(m_base_url, String.class);
        JSONArray res = new JSONArray(jsonResult);
        for (int i = 0; i < res.length(); i++) {
            JSONObject e = res.getJSONObject(i);
            T result;
            switch (m_entity_type) {
                case "Project":
                    result = (T) new Project(m_base_url, e.getString("name"), e.getString("project_id"));
                    break;
                case "Node":
                    result = (T) new Node(m_base_url, e.getString("name"), e.getString("node_type"), e.getString("node_id"));
                    break;
                case "Link" :
                    result = (T) new Link(m_base_url, e.getString("link_id"));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + m_entity_type);
            }
            entities.add(result);
        }
        return entities;
    }

    T addEntity(List<String> params){
        for (T e : getEntities()){
            if (e.getId().equals(params.get(0))){
                System.err.println("L'entité " + params.get(0) + " existe déjà !");
                return null;
            }
        }

        T result;
        switch (m_entity_type) {
            case "Project":
                result = (T) new Project(m_base_url, params.get(0));
                break;
            case "Node":
                result = (T) new Node(m_base_url, params.get(0), params.get(1));
                break;
            case "Link" :
                result = (T) new Link(m_base_url, params.get(0), params.get(1));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + m_entity_type);
        }
        entities.add(result);
        return result;
    }

    T getEntity(String id){
        for (T e : getEntities()){
            if (e.getId().equals(id)){
                return e;
            }
        }
        System.err.println("L'entité " + id + " n'existe pas !");
        return null;
    }

    void deleteEntity(String id){
        for (T e : getEntities()){
            if (e.getId().equals(id)){
                entities.remove(e);
                e.delete();
                return;
            }
        }
        System.err.println("L'entité " + id + " n'existe pas !");
    }
}
