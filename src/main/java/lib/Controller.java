package lib;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private EntityManager<Project> projects;

    public Controller(String ip)
    {
        projects = new EntityManager<>("http://" + ip + ":3080/v2/projects", EntityTypes.Project);
    }

    public List<Project> getProjects(){
        return projects.getEntities();
    }

   // public Controller deleteAllProjects

    public Controller addProject(String name){
        List<Object> params = new ArrayList();
        params.add(name);
        projects.addEntity(params);
        return this;
    }

    public Project getProject(String name){
        return projects.getEntity(name);
    }

    public Controller deleteProject(String name){
        projects.deleteEntity(name);
        return this;
    }
}