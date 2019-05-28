package lib;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class CmdHelper {

    private static int deleteLastLines = 2;
    private static int timeToSleep = 500;
    private static TelnetClient telnet = new TelnetClient();


    public static void write(String ip, int port, String cmd) throws IOException, InterruptedException {

        telnet.connect(ip, port);
        OutputStream outputStream = telnet.getOutputStream();

        Thread.sleep(timeToSleep);
        outputStream.write((cmd + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public static String read(String ip, int port, boolean wait) throws IOException, InterruptedException {

        telnet.connect(ip, port);
        InputStream inputStream = telnet.getInputStream();

        Thread.sleep(timeToSleep);
        byte[] buffer = new byte[4096];
        int i = 0;

        while (inputStream.available() == 0 && wait){
            Thread.sleep(100);
        }

        while (inputStream.available() > 0){
            buffer[i] = (byte) inputStream.read();
            i++;
        }

        String result = new String(buffer, StandardCharsets.UTF_8);

        if (deleteLastLines > 0){
            String[] lines = result.split("\r");
            result = "";
            for (int j = 0; j < (lines.length - deleteLastLines); j++) {
                result += lines[j];
            }
        }

        return result;
    }

}