import lib.Controller;

public class Test1 {

    public static void main(String[] args) {
        Controller controller = new Controller("148.60.11.161");

        controller.getProjects().forEach(p -> System.out.println(p.getName() + " : " + p.getId()));

        controller.addProject("test").delete();

        controller.addProject("test");
        System.out.println(controller.getProject("test").getId());
        controller.deleteProject("test");

        controller.getProjects().forEach(p -> System.out.println(p.getName() + " : " + p.getId()));
    }
}
