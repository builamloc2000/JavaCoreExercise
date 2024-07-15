/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lamloc
 */
import java.sql.*;
import java.util.Scanner;

public class Q4 {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle JDBC URL
    private static final String DB_USER = "sys as sysdba"; // Oracle username
    private static final String DB_PASSWORD = "2802"; // Oracle password
    private static final String INSERT_SQL = "INSERT INTO students (NAME, GENDER, BIRTHPLACE, AGE) VALUES (?, ?, ?, ?)";
    private static final String SEARCH_SQL = "select NAME from STUDENTS";

    public static void main(String[] args) {
       try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter the number of students:");
                int n = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < n; i++) {
                    String name;
                    while (true) {
                        System.out.println("Enter name:");
                        name = scanner.nextLine();

                        if (!isNameExists(conn, name)) {
                            break; // Break the loop if the name doesn't exist
                        } else {
                            System.out.println("Error: A student with the name '" + name + "' already exists. Please enter a different name.");
                        }
                    }

                    System.out.println("Enter gender:");
                    String gender = scanner.nextLine();

                    System.out.println("Enter birthplace:");
                    String birthplace = scanner.nextLine();

                    System.out.println("Enter age:");
                    int age = Integer.parseInt(scanner.nextLine());

                    insertStudent(conn, name, gender, birthplace, age);
                }

                System.out.println("Do you want to enter more students? (yes/no):");
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("yes")) {
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection conn, String name, String gender, String birthplace, int age) {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setString(3, birthplace);
            pstmt.setInt(4, age);
            pstmt.executeUpdate();
            System.out.println("Inserted student: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     private static boolean isNameExists(Connection conn, String name) throws SQLException {
        String checkNameQuery = "SELECT COUNT(*) FROM students WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(checkNameQuery)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
