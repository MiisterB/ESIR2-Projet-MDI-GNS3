import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Node extends Entity
{
    //attributs
    String m_node_type;
    String m_compute_id;

    public void Node(String name, String project_id, String node_type, String compute_id)
    {
        this.m_name = name;
        this.m_project_id = project_id;
        this.m_node_type = node_type;
        this.m_compute_id = compute_id;
        create();
    }

    void create()
    {

    }

    void delete()
    {

    }

    String makeUrl(String path)
    {
        return null;
    }
}
