package lib;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Controller extends  ControlEntity{

    private List<Project> projects;

    final static RestTemplate restTemplate = new RestTemplate();

    public Controller(String ip)
    {
        super("http://" + ip + ":3080/v2/projects");
        projects = new ArrayList<>();
    }

    public List<Project> getProjects(){
        return super.getEntities();
    }

    public Project addProject(String name){
        return (Project) super.addEntity(name);
    }

    public Project getProject(String name){
        return (Project) super.getEntity(name);
    }

    public void deleteProject(String name){
        super.deleteEntity(name);
    }

    @Override
    RestEntity getInstance(JSONObject p) {
        return new Project(m_base_url, p.getString("name"), p.getString("project_id"));
    }

    @Override
    RestEntity createInstance(String name, String type) {
        return new Project(m_base_url, name);
    }
}