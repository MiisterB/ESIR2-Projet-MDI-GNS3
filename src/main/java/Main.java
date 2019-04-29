import org.springframework.web.client.RestTemplate;

public class Main {

    protected final static String m_base_URL = "http://148.60.11.161:3080";
    private final static RestTemplate restTemplate = new RestTemplate();
    public static RestTemplate getRestTemplate(){
        return restTemplate;
    }

    public static void main(String[] args) {
//        try {
//            Project myProject = new Project("testNodes3");
//            System.out.println("Project id : " + myProject.getEntityId());
//            System.out.println("Nodes ids : ");
//            System.out.println(" 1 -> " + myProject.addNode("N1", "vpcs").getEntityId());
//            System.out.println(" 2 -> " + myProject.addNode("N2", "vpcs").getEntityId());
//            myProject.addNode("N3", "vpcs").delete();
//            myProject.addNode("N4", "vpcs").delete();
//            myProject.delete();
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        Controller c = new Controller("148.60.11.161");
        for (Project p : c.getProjects()) {
            System.out.println(p.getName() + " : " + p.getEntityId());
        }
    }
}
