import java.sql.*;
 
public class StudentManager {
 
	//DataBase Configuration
	private static final String URL = "jdbc:mysql://localhost:3306/student_db";
	private static final String USER = "root";
	private static final String PASSWORD = "tiger";
 
	public StudentManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to MySQL Database successfully!");
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: driver not found, check jar file");
		} catch (SQLException e) {
			System.out.println("Database connection failed: " + e.getMessage());
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
			System.out.println("Student added successfully!");
 
		} catch (SQLException e) {
			System.out.println("Error adding student (id might already exist): " + e.getMessage());
		}
	}
 
	public void listAllStudents() {
		String query = "SELECT * FROM students";
 
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
 
			boolean found = false;
			while (rs.next()) {
				found = true;
				System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name")
						+ " | Course: " + rs.getString("course"));
			}
 
			if (!found)
				System.out.println("No students in the database yet.");
 
		} catch (SQLException e) {
			System.out.println("Error retrieving students: " + e.getMessage());
		}
	}
 
	public void searchStudent(int id) {
		String query = "SELECT * FROM students WHERE id = ?";
 
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query);) {
 
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
 
			if (rs.next()) {
				System.out.println("Student Found -> ID: " + rs.getInt("id") + " | Name: "
						+ rs.getString("name") + " | Course: " + rs.getString("course"));
			} else {
				System.out.println("No student with ID " + id);
			}
 
		} catch (SQLException e) {
			System.out.println("Error searching student: " + e.getMessage());
		}
	}
 
	// update name/course for an existing id
	public void updateStudent(int id, String newName, String newCourse) {
 
		String checkQuery = "SELECT * FROM students WHERE id = ?";
		String updateQuery = "UPDATE students SET name = ?, course = ? WHERE id = ?";
 
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
 
			PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
			checkStmt.setInt(1, id);
			ResultSet rs = checkStmt.executeQuery();
 
			if (!rs.next()) {
				System.out.println("Student with ID " + id + " not found, can't update.");
				conn.close();
				return;
			}
 
			PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
			updateStmt.setString(1, newName);
			updateStmt.setString(2, newCourse);
			updateStmt.setInt(3, id);
 
			int rows = updateStmt.executeUpdate();
			if (rows > 0) {
				System.out.println("Student updated successfully!");
			} else {
				System.out.println("Update didn't go through, try again.");
			}
 
			conn.close();
 
		} catch (SQLException e) {
			System.out.println("Error updating student: " + e.getMessage());
		}
	}
 
	public void deleteStudent(int id) {
		String query = "DELETE FROM students WHERE id = ?";
 
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(query)) {
 
			pstmt.setInt(1, id);
			int rows = pstmt.executeUpdate();
 
			if (rows > 0) {
				System.out.println("Student deleted.");
			} else {
				System.out.println("No student with ID " + id + ", nothing deleted.");
			}
 
		} catch (SQLException e) {
			System.out.println("Error deleting student: " + e.getMessage());
		}
	}
}