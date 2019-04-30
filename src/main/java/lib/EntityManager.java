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
    private T m_entity;

    EntityManager(String base_url, T entity) {
        restTemplate = new RestTemplate();
        entities = new ArrayList<>();
        m_base_url = base_url;
        m_entity = entity;
    }

    List<T> getEntities(){
        entities.clear();
        String jsonResult = restTemplate.getForObject(m_base_url, String.class);
        JSONArray res = new JSONArray(jsonResult);
        for (int i = 0; i < res.length(); i++) {
            JSONObject e = res.getJSONObject(i);
            entities.add((T) m_entity.getInstance(e));
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

        T result = (T) m_entity.createInstance(params);
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
