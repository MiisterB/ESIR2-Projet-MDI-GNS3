public abstract class Entity
{
    //Attributs
    String m_name;
    String project_id;

    public void Entity(string name)
    {
        m_name = name;
    }

    abstract void createRequest()
    {

    }

    abstract void deleteRequest()
    {

    }

    public String getName() {
        return name;
    }

    public String getProjectId() {
        return projectId;
    }
}