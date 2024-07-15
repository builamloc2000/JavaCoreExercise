/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */


import java.util.LinkedList;
import java.util.Queue;

class Producer implements Runnable {
    private final Queue<String> queue;
    private int messageCount = 1;
    private final int MAX_QUEUE_SIZE;
    //generate constructor
    public Producer(Queue<String> queue, int maxSize) {
        //one is queue to store message
        this.queue = queue;
        //one is size of queue
        this.MAX_QUEUE_SIZE = maxSize;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == MAX_QUEUE_SIZE) {
                        System.out.println("Queue is full, producer is waiting...");
                        queue.wait();
                    }
                    //get message
                    String message = "Message-" + messageCount++;
                    //add message
                    queue.add(message);
                    //print out message
                    System.out.println("Produced: " + message);
                    //awake thread
                    queue.notify();
                }

                Thread.sleep(1000); // Adjust here to fit require
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final Queue<String> queue;
    //constructor
    public Consumer(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty, consumer is waiting...");
                        queue.wait();
                    }

                    String message = queue.poll();
                    System.out.println("Consumed: " + message);
                    //awake thread
                    queue.notify();
                }

                Thread.sleep(4000); // Adjust here to fit require
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Q3 {
    public static void main(String[] args) {
        //initial size
        final int MAX_QUEUE_SIZE = 5;
        //declare queue
        Queue<String> queue = new LinkedList<>();
        //declare producer
        Producer producer = new Producer(queue, MAX_QUEUE_SIZE);
        //declare comsumer
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        //start thread
        producerThread.start();
        consumerThread.start();
    }
}
