
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */
public class Q3 {

    // Phương thức sắp xếp mảng bằng thuật toán quick sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Phương thức phân đoạn mảng và trả về chỉ số phân đoạn
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Phương thức hoán đổi vị trí giữa hai phần tử trong mảng
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Phương thức đọc mảng số nguyên từ file
    public static int[] readIntArrayFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(" ");
        }
        reader.close();

        // Chuyển dữ liệu thành mảng số nguyên
        String[] numbers = sb.toString().trim().split("\\s+");
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }

    // Phương thức in ra mảng số nguyên đã sắp xếp trên console
    public static void printSortedArray(int[] arr) {
        System.out.println("Mảng đã sắp xếp:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        String inputFile = "/home/lamloc/Desktop/input.txt";

        try {
            // Đọc mảng từ file
            int[] arr = Q3.readIntArrayFromFile(inputFile);

            // Sắp xếp mảng bằng quick sort
            Q3.quickSort(arr, 0, arr.length - 1);

            // In ra mảng đã sắp xếp trên console
            Q3.printSortedArray(arr);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}

