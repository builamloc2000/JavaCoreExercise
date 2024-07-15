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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
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

        int port = Integer.parseInt(config.getProperty("port"));
        int receiveTimeout = Integer.parseInt(config.getProperty("receiveTimeout"));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started, waiting for connection...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    clientSocket.setSoTimeout(receiveTimeout);
                    //read mess from client
                    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                    //read until end
                    while (true) {
                        String message = in.readUTF();
                        System.out.println("Received: " + message);
                    }
                    //catch error from client
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //log error
                    try (FileWriter fw = new FileWriter("server_errors.log", true)) {
                        fw.write(ex.getMessage() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            //error of create server
            ex.printStackTrace();
            //log error to server error file
            try (FileWriter fw = new FileWriter("server_errors.log", true)) {
                fw.write(ex.getMessage() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
