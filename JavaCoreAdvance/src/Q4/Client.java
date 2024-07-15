/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Q4;

/**
 *
 * @author lamloc
 */
import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Random;

public class Client {
    private static String generateRandomString(int length) {
        int leftLimit = 97; // from letter 'a'
        int rightLimit = 122; // to letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        //read file config
        try (InputStream input = new FileInputStream("config.properties")) {
            //load file config
            config.load(input);
            //catch error read file
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        String ip = config.getProperty("ip");
        int port = Integer.parseInt(config.getProperty("port"));
        int connectionTimeout = Integer.parseInt(config.getProperty("connectionTimeout"));
        int sendTimeout = Integer.parseInt(config.getProperty("sendTimeout"));

        try (Socket socket = new Socket()) {
            //connect to server
            socket.connect(new java.net.InetSocketAddress(ip, port), connectionTimeout);
            //set time out
            socket.setSoTimeout(sendTimeout);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            //send mess to server
            while (true) {
                String message = generateRandomString(10);
                out.writeUTF(message);
                out.flush();
                System.out.println("Sent: " + message);
                //delay 1 sec
                Thread.sleep(1000); 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //log error
            try (FileWriter fw = new FileWriter("client_errors.log", true)) {
                fw.write(ex.getMessage() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

