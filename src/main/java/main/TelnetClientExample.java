package main;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class TelnetClientExample {

    private static int timeToSleep = 1000;

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

    private static readUntil

    private static void write(OutputStream outputStream, String s) throws IOException, InterruptedException {
        Thread.sleep(timeToSleep);
        outputStream.write((s + "\n").getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public static void main(String[] args) {

        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect("192.168.56.103", 5000);

            InputStream inputStream = telnet.getInputStream();
            OutputStream outputStream = telnet.getOutputStream();

            System.out.println(read(inputStream));

            write(outputStream, "?");
            System.out.println(read(inputStream));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);

    }

}