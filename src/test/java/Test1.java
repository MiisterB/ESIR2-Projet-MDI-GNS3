import lib.Controller;

public class Test1 {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

        controller.getProjects().forEach(p ->
                System.out.println(p.getName() + " : " + p.getId()));
        System.out.println();

        controller.addProject("test").delete();

        controller.addProject("test");
        System.out.println("Project id : " + controller.getProject("test").getId());
        controller.deleteProject("test");

        System.out.println();
        controller.getProjects().forEach(p ->
                System.out.println(p.getName() + " : " + p.getId()));
    }
}
