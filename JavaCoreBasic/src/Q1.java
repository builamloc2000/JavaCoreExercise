
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */
public class Q1 {
    private int correctNumber;
    private int maxAttempts;
    private Scanner scanner;

    public Q1(int correctNumber, int maxAttempts) {
        this.correctNumber = correctNumber;
        this.maxAttempts = maxAttempts;
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        System.out.println("Hãy nhập số đúng:");

        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            System.out.print("Lần nhập thứ " + attempts + ": ");
            int userInput = scanner.nextInt();

            if (userInput == correctNumber) {
                System.out.println("Nhập thành công!");
                return;
            } else {
                System.out.println("Nhập sai!");
            }
        }

        System.out.println("Bạn đã nhập sai quá 5 lần. Chương trình dừng lại.");
    }
    
    public static void main(String[] args) {
        Q1 newgame = new Q1(2,5);
        newgame.playGame();
    }

    
}
