import lib.Controller;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCmd {

    @Test
    public void testLauchCmds(){
        String project_name = "test_cmd";

        Controller controller = new Controller("148.60.11.161");

        String result = controller
                .addProject(project_name)
                .getProject(project_name)
                .addNode("N", "vpcs")
                .getNode("N")
                .sendCmd("!mkdir test")
                .sendCmdAndGetResp("!ls");

        assertTrue(result.contains("test"));
    }
}
