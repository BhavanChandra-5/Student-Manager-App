import java.sql.*;

public class StudentManager {
    // Database credentials - CHANGE THESE TO MATCH YOUR MYSQL SETUP!
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "tiger"; 

    public StudentManager() {
        // Test connection when the manager starts
        try {
            // WAKE UP THE DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL Database successfully!");
            conn.close(); // Close the test connection
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ERROR: Eclipse still can't find the .jar file!");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            System.out.println("Did you remember to create the student_db in MySQL?");
        }
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (id, name, course) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getCourse());
            
            pstmt.executeUpdate();
            System.out.println("✅ Student added successfully to the database!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding student (Does this ID already exist?): " + e.getMessage());
        }
    }

    public void listAllStudents() {
        String query = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            boolean hasStudents = false;
            while (rs.next()) {
                hasStudents = true;
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Name: " + rs.getString("name") + 
                                   " | Course: " + rs.getString("course"));
            }
            
            if (!hasStudents) {
                System.out.println("No students found in the database.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving students: " + e.getMessage());
        }
    }

    public void searchStudent(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("✅ Student Found:");
                    System.out.println("ID: " + rs.getInt("id") + 
                                       " | Name: " + rs.getString("name") + 
                                       " | Course: " + rs.getString("course"));
                } else {
                    System.out.println("Student with ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error searching for student: " + e.getMessage());
        }
    }
}