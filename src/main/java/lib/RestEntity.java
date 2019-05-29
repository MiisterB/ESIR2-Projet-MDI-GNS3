package lib;

import org.json.JSONObject;

public abstract class RestEntity {

    String m_entity_id;
    String m_base_url;

    RestEntity(String base_url){
        m_base_url = base_url;
    }

    public RestEntity update(JSONObject req){
        RequestHelper.put(m_base_url + "/" + m_entity_id, req);
        return this;
    }

    public RestEntity delete(){
        RequestHelper.delete(m_base_url + "/" + m_entity_id);
        return this;
    }

    public String getId() {
        return m_entity_id;
    }
}