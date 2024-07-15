
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */
public class Q2 {
    private int electricUsage;
    private Scanner scanner;
    // Constructor để nhận số điện đã sử dụng
    public Q2() {
        
        this.scanner = new Scanner(System.in);
    }

    // Phương thức tính tiền điện dựa trên số điện đã sử dụng
    public void calculate() {
        int price = 0;
        System.out.println("Hãy nhập số điện sử dụng:");
        int userInput = scanner.nextInt();

        if (userInput <= 100) {
            price = userInput * 1000;
        } else if (userInput > 100 && userInput <= 150) {
            price = (100 * 1000) + ((userInput - 100) * 1500);
        } else if (userInput > 150) {
            price = (100 * 1000) + (50 * 1500) + ((userInput - 150) * 2000);
        }

        System.out.println(price + " VND");
    }
    public static void main(String[] args) {
        Q2 cal = new Q2();
        cal.calculate();
    }
}
