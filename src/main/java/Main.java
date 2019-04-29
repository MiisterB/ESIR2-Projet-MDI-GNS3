import org.springframework.web.client.RestTemplate;

public class Main {

    private final static String  baseURL = "http://148.60.11.161:3080";
    private final static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        try {
            Project myProject = new Project("testNodes");
            System.out.println("Project id : " + myProject.getEntityId());
            System.out.println("Nodes ids : ");
            System.out.println(" 1 -> " + myProject.addNode("N1", "vpcs").getEntityId());
            System.out.println(" 2 -> " + myProject.addNode("N2", "vpcs").getEntityId());
            System.out.println(" 3 -> " + myProject.addNode("N3", "vpcs").getEntityId());
            System.out.println(" 4 -> " + myProject.addNode("N4", "vpcs").getEntityId());
            // myProject.delete();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String makeUrl(String path){
        return baseURL + path;
    }
    public static RestTemplate getRestTemplate(){
        return restTemplate;
    }

}
