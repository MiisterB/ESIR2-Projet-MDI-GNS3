package main;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class TelnetClientExample {

    public static void main(String[] args) {

        TelnetClient telnet = new TelnetClient();
        try {
            telnet.connect("148.60.11.161", 5027);

            InputStream inputStream = telnet.getInputStream();
            OutputStream outputStream = telnet.getOutputStream();

            String cmd = "ls";
            outputStream.write(cmd.getBytes(StandardCharsets.UTF_8));

            String result = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);

    }


//    try {
//        telnet.connect("rainmaker.wunderground.com", 3000);
//    }
//    catch (IOException e) {
//        e.printStackTrace();
//        System.exit(1);
//    }
//
//    IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),
//    System.in, System.out);
//
//        try
//    {
//        telnet.disconnect();
//    }
//        catch (IOException e)
//    {
//        e.printStackTrace();
//        System.exit(1);
//    }
//
//        System.exit(0);
}