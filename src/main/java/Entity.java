import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class Entity
{
    //Attributs
    String m_name;
    String m_project_id;

    public void Entity(String name)
    {
        m_name = name;
    }

    abstract void create();

    abstract void delete();

    abstract String makeUrl(String path);

    public String getName() {
        return m_name;
    }

    public String getProjectId() {
        return m_project_id;
    }
}