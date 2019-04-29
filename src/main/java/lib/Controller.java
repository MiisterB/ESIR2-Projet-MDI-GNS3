package lib;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Controller{

    private static String m_base_url = "";
    private List<Project> projects;

    final static RestTemplate restTemplate = new RestTemplate();

    public Controller(String ip)
    {
        m_base_url = "http://" + ip + ":3080";
        projects = new ArrayList<>();
    }

    public List<Project> getProjects(){
        projects.clear();
        String jsonResult = restTemplate.getForObject(m_base_url + "/v2/projects", String.class);
        JSONArray res = new JSONArray(jsonResult);
        for (int i = 0; i < res.length(); i++) {
            JSONObject p = res.getJSONObject(i);
            projects.add(new Project(m_base_url, p.getString("name"), p.getString("project_id")));
        }
        return projects;
    }

    public Project addProject(String name){
        for (Project p : getProjects()){
            if (p.getName().equals(name)){
                System.err.println("Le projet " + name + " existe déjà !");
                return null;
            }
        }
        Project result = new Project(m_base_url, name);
        projects.add(result);
        return result;
    }

    public Project getProject(String name){
        for (Project p : getProjects()){
            if (p.getName().equals(name)){
                return p;
            }
        }
        System.err.println("Le projet " + name + " n'existe pas !");
        return null;
    }

    public void deleteProject(String name){
        for (Project p : getProjects()){
            if (p.getName().equals(name)){
                projects.remove(p);
                p.delete();
                return;
            }
        }
        System.err.println("Le projet " + name + " n'existe pas !");
    }
}