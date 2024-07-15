/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Q1 {
    private static volatile boolean stopFlag = false;

    public static void main(String[] args) {
        //initial thread
        Thread writerThread = new Thread(new Runnable() {
            @Override
            //create random integer
            public void run() {
                writeRandomIntegers();
            }
        });
        //initial thread
        Thread listenerThread = new Thread(new Runnable() {
            @Override
            //stop running
            public void run() {
                listenForStop();
            }
        });
        //start
        writerThread.start();
        listenerThread.start();

        try {
            writerThread.join();//wait until writer finish
            listenerThread.join();//wait until listener finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void writeRandomIntegers() {
        Random random = new Random();
        //write out in output
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
            while (!stopFlag) {
                int randomInteger = random.nextInt(100);
                writer.write(randomInteger + "\n");
                writer.flush();
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenForStop() {
        //get input in console
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.trim().equalsIgnoreCase("stop")) {
                stopFlag = true;
                break;
            }
        }
        scanner.close();
    }
}

