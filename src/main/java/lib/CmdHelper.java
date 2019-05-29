package lib;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class CmdHelper {

    private static int deleteLastLines = 0;

    private static int timeToSleep = 1000;

/*    private static String read(InputStream inputStream, boolean wait) throws IOException, InterruptedException {
        byte[] buffer = new byte[4096];
        int i = 0;

        int stop = 5_000;
        int total = 0;
        int step = 100;
        while (inputStream.available() == 0 && wait){
            total += step;
            Thread.sleep(step);
            if (total > stop){
                System.err.println("Nothing to read on that node ...");
                break;
            }
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


    public static String read(String ip, int port, boolean wait) throws IOException, InterruptedException {
        TelnetClient telnet = new TelnetClient();

        telnet.connect(ip, port);
        InputStream inputStream = telnet.getInputStream();

        Thread.sleep(timeToSleep);
        telnet.disconnect();
        return read(inputStream, wait);
    }

    public static void write(String ip, int port, String cmd) throws IOException, InterruptedException {

        TelnetClient telnet = new TelnetClient();

        telnet.connect(ip, port);
        InputStream inputStream = telnet.getInputStream();
        OutputStream outputStream = telnet.getOutputStream();

        Thread.sleep(timeToSleep);

        outputStream.write((cmd + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        inputStream.read();
        telnet.disconnect();
    }

    public static String writeAndRead(String ip, int port, String cmd) throws IOException, InterruptedException {

        TelnetClient telnet = new TelnetClient();

        telnet.connect(ip, port);
        InputStream inputStream = telnet.getInputStream();
        OutputStream outputStream = telnet.getOutputStream();

        Thread.sleep(timeToSleep);

        outputStream.write((cmd + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        String result = read(inputStream, true);

        return result;
    }*/



    private static String read(InputStream inputStream) throws IOException, InterruptedException {
        Thread.sleep(timeToSleep);
        byte[] buffer = new byte[4096];
        int i = 0;
        while (inputStream.available() > 0){
            buffer[i] = (byte) inputStream.read();
            i++;
        }
        return new String(buffer, StandardCharsets.UTF_8);
    }

    private static void write(OutputStream outputStream, String s) throws IOException, InterruptedException {
        Thread.sleep(timeToSleep);
        outputStream.write((s + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}