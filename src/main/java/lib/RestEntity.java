package lib;

import org.json.JSONObject;

public abstract class RestEntity {

    String m_entity_id;
    String m_base_url;

    RestEntity(String base_url){
        m_base_url = base_url;
    }

    JSONObject create(JSONObject req){
        JSONObject jsonResult = RequestHelper.post(m_base_url, req);
        return new JSONObject(jsonResult);
    }

    public void delete(){
        RequestHelper.delete(m_base_url + "/" + m_entity_id);
    }

    public String getId() {
        return m_entity_id;
    }
}