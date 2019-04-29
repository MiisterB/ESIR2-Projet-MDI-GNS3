import org.springframework.web.client.RestTemplate;

public class Main {

    private final static String  baseURL = "http://148.60.11.161:3080";
    private final static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        try {
            Project myProject = new Project("test4");
            System.out.println(myProject.getProjectId());
            myProject.deleteRequest();
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
