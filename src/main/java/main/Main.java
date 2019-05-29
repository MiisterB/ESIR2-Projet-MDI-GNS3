package main;

import lib.Controller;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller("192.168.56.103");

        String project_name = "Test_commandes";

//        controller
//                .getProject(project_name)
//                .openProject()
//                .getNode("V")
//                .startNode()
//                .sendCmdAndWaitResp("?");

        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect("192.168.56.103", 5004);

            InputStream inputStream = telnet.getInputStream();
            OutputStream outputStream = telnet.getOutputStream();

            System.out.println(read(inputStream));

            write(outputStream, "?");
            System.out.println(read(inputStream));

            telnet.disconnect();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }



    }
}
