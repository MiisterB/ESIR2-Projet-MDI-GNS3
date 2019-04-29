import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

public class Controller{

    String m_ip;
    List<Project> projets;

    public Controller(String ip)
    {
        m_ip = ip;
        projets = new ArrayList<Project>();
    }

    public List<Project> getProjects(){
        String jsonResult = Main.getRestTemplate().getForObject(Main.m_base_URL + "/v2/projects", String.class);
        JSONArray res = new JSONArray(jsonResult);
        for (int i = 0; i < res.length(); i++) {
            JSONObject p = res.getJSONObject(i);
            projets.add(new Project(p.getString("name"), p.getString("project_id")));
        }
        return projets;
    }
}