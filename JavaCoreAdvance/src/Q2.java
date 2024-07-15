/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Q2 {

    public static void main(String[] args) {
        //initial time
        int time = 1; 
        
        //create timer instance
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask() {
            Random random = new Random();

            @Override
            public void run() {
                int randomNumber = random.nextInt();
                System.out.println("Random number: " + randomNumber);
            }
        };

        long delay = 0;
        long period = time * 1000;
        timer.scheduleAtFixedRate(task, delay, period);

        // Schedule a task to stop the timer after the specified total duration
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                System.out.println("Timer stopped.");
            }
        }, time * 60 * 1000);
    }
}

