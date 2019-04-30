package lib;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private EntityManager<Project> projects;

    public Controller(String ip)
    {
        String base_url = "http://" + ip + ":3080/v2/projects";
        projects = new EntityManager<>(base_url, new Project(base_url));
    }

    public List<Project> getProjects(){
        return projects.getEntities();
    }

    public Project addProject(String name){
        List<String> params = new ArrayList();
        params.add(name);
        return projects.addEntity(params);
    }

    public Project getProject(String name){
        return projects.getEntity(name);
    }

    public void deleteProject(String name){
        projects.deleteEntity(name);
    }
}