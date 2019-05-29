package lib;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class Docker extends RestEntity{

    private String m_name;
    private String m_image;

    Docker(String base_url,String entity_id) {
        super(base_url);
        m_entity_id = entity_id;
    }

    Docker(String base_url, String name, String image){
        super(base_url);
        m_name = name;
        m_image = image;

        JSONObject req = new JSONObject()
                .put("name", getName())
                .put("m_image", m_image);
        JSONObject res = super.create(req);
        m_entity_id = res.getString("node_id");
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


   //Retourne toutes les images docker
    public JSONObject imagesDocker()
    {
        String jsonResult = restTemplate.getForObject(m_base_url , String.class);
        return new JSONObject(jsonResult);
    }

    //Fonction qui permet de dupliquer un docker
    public Docker duplicateDocker(String destination_node_id) {

        JSONObject req = new JSONObject().put("destination_node_id", destination_node_id);
        RequestHelper.post(m_base_url + "/" + getTrueId() + "/duplicate", req);
        return this;
    }

    //Fonction qui permet de mettre un docker en pause
    public Docker pauseDocker() {

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/pause");
        return this;
    }

    //Fonction qui permet de redemarrer un conteneur docker
    public Docker reloadDocker() {

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/reload");
        return this;
    }

    //Fonction qui permet de demarrer un conteneur docker
    public Docker startDocker() {

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/start");
        return this;
    }

    //Fonction qui permet de stopper un conteneur docker
    public Docker stopDocker() {

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/stop");
        return this;
    }
    //Fonction qui permet de suspendre un conteneur docker
    public Docker suspendDocker() {

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/suspend");
        return this;
    }

    //Unpause a Docker container
    public Docker unpauseDocker() {

        RequestHelper.post(m_base_url + "/" + getTrueId() + "/unpause");
        return this;
    }

}
