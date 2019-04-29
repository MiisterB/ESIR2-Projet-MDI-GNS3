package lib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class ControlEntity<T extends RestEntity> {
    private List<T> entities;
    protected String m_base_url;

    public ControlEntity(String base_url) {
        m_base_url = base_url;
        entities = new ArrayList<>();
    }

    abstract T getInstance(JSONObject e);
    abstract T createInstance(String name, String type);

    List<T> getEntities(){
        entities.clear();
        String jsonResult = Controller.restTemplate.getForObject(m_base_url, String.class);
        JSONArray res = new JSONArray(jsonResult);
        for (int i = 0; i < res.length(); i++) {
            JSONObject e = res.getJSONObject(i);
            entities.add(getInstance(e));
        }
        return entities;
    }

    T addEntity(String name){
        for (T e : getEntities()){
            if (e.getName().equals(name)){
                System.err.println("L'entité " + name + " existe déjà !");
                return null;
            }
        }
        T result = createInstance(name, "");
        entities.add(result);
        return result;
    }

    T getEntity(String name){
        for (T e : getEntities()){
            if (e.getName().equals(name)){
                return e;
            }
        }
        System.err.println("L'entité " + name + " n'existe pas !");
        return null;
    }

    void deleteEntity(String name){
        for (T e : getEntities()){
            if (e.getName().equals(name)){
                entities.remove(e);
                e.delete();
                return;
            }
        }
        System.err.println("L'entité " + name + " n'existe pas !");
    }
}
