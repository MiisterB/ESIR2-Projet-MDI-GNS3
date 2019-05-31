package lib;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class CmdHelper {

    private static int deleteLastLines = 2;
    private static int timeToSleep = 1000;


    private static String read(InputStream inputStream) throws IOException, InterruptedException {
         byte[] buffer = new byte[4096];
        int i = 0;

        while (inputStream.available() > 0){
            buffer[i] = (byte) inputStream.read();
            i++;
        }
        return new String(buffer, StandardCharsets.UTF_8);
    }

    private static void write(OutputStream outputStream, String cmd) throws InterruptedException, IOException {
        outputStream.write((cmd + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }


    public static void write(String ip, int port, String cmd){
        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect(ip, port);
            OutputStream outputStream = telnet.getOutputStream();
            InputStream inputStream   = telnet.getInputStream();

            write(outputStream, cmd);

            inputStream.read();
            telnet.disconnect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String writeAndRead(String ip, int port, String cmd) {
        String result = " > ";

        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect(ip, port);
            InputStream inputStream = telnet.getInputStream();
            OutputStream outputStream = telnet.getOutputStream();

            Thread.sleep(timeToSleep);
            write(outputStream, cmd);
            Thread.sleep(timeToSleep);
            result += CmdHelper.read(inputStream);


            if (deleteLastLines > 0){
                String[] lines = result.split("\r");
                result = "";
                for (int j = 0; j < (lines.length - deleteLastLines); j++) {
                    result += lines[j];
                }
            }

            telnet.disconnect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String writeAndReadUntil(String ip, int port, String cmd, String text, int timeOut) {
        String result = " > ";

        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect(ip, port);
            InputStream inputStream = telnet.getInputStream();
            OutputStream outputStream = telnet.getOutputStream();

            write(outputStream, cmd);

            int counter = 0;
            while(!result.contains(text)){
                result += CmdHelper.read(inputStream);
                Thread.sleep(100);
                counter += 100;
                if (counter >= timeOut){
                    break;
                }
            }

            if (deleteLastLines > 0){
                String[] lines = result.split("\r");
                result = "";
                for (int j = 0; j < (lines.length - deleteLastLines); j++) {
                    result += lines[j];
                }
            }

            telnet.disconnect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String readUntil(String ip, int port, String text, int timeOut) {
        String result = " > ";

        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect(ip, port);
            InputStream inputStream = telnet.getInputStream();
            OutputStream outputStream = telnet.getOutputStream();

            int counter = 0;
            while(!result.contains(text)){
                result += CmdHelper.read(inputStream);
                Thread.sleep(100);
                counter += 100;
                if (counter >= timeOut){
                    break;
                }
            }

            if (deleteLastLines > 0){
                String[] lines = result.split("\r");
                result = "";
                for (int j = 0; j < (lines.length - deleteLastLines); j++) {
                    result += lines[j];
                }
            }

            telnet.disconnect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}