import lib.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class TestMethodeProjet
{

    @Test
    public void testCloseProject ()
    {
        Controller controller = new Controller("148.60.11.161");

        String project_name = "testCloseProject";
        controller.deleteProject(project_name);
        controller.deleteProject(project_name);
        Project project = controller
                .addProject(project_name)
                .getProject(project_name)
                .closeProject();
    }




}
