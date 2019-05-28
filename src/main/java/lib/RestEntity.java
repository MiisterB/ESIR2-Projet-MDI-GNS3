package lib;

public abstract class RestEntity {

    String m_entity_id;
    String m_base_url;

    RestEntity(String base_url){
        m_base_url = base_url;
    }

    public void delete(){
        RequestHelper.delete(m_base_url + "/" + m_entity_id);
    }

    public String getId() {
        return m_entity_id;
    }
}