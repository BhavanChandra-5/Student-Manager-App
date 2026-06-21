import java.util.Scanner;
import java.util.InputMismatchException;
 
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentManager manager = new StudentManager();
		boolean running = true;
 
		System.out.println("--- Welcome to Student Manager ---");
 
		while (running) {
			System.out.println("\n1. Add Student");
			System.out.println("2. List All Students");
			System.out.println("3. Search Student by ID");
			System.out.println("4. Update Student");
			System.out.println("5. Delete Student");
			System.out.println("6. Save & Exit");
			System.out.print("Choose an option: ");
 
			try {
				int choice = scanner.nextInt();
				scanner.nextLine();
 
				switch (choice) {
				case 1:
					System.out.print("Enter Student ID: ");
					int id = scanner.nextInt();
					scanner.nextLine();
 
					System.out.print("Enter Student Name: ");
					String name = scanner.nextLine();
 
					if (name.trim().isEmpty()) {
						System.out.println("Name cannot be empty.");
						break;
					}
 
					System.out.print("Enter Course: ");
					String course = scanner.nextLine();
 
					manager.addStudent(new Student(id, name, course));
					break;
 
				case 2:
					System.out.println("\n--- Student List ---");
					manager.listAllStudents();
					break;
 
				case 3:
					System.out.print("Enter ID to search: ");
					int searchId = scanner.nextInt();
					manager.searchStudent(searchId);
					break;
 
				case 4:
					System.out.print("Enter Student ID to update: ");
					int updateId = scanner.nextInt();
					scanner.nextLine();
 
					System.out.print("Enter New Name: ");
					String newName = scanner.nextLine();
 
					if (newName.trim().isEmpty()) {
						System.out.println("Name cannot be empty, update cancelled.");
						break;
					}
 
					System.out.print("Enter New Course: ");
					String newCourse = scanner.nextLine();
 
					manager.updateStudent(updateId, newName, newCourse);
					break;
 
				case 5:
					System.out.print("Enter Student ID to delete: ");
					int deleteId = scanner.nextInt();
					manager.deleteStudent(deleteId);
					break;
 
				case 6:
					running = false;
					System.out.println("Exiting application. Goodbye!");
					break;
 
				default:
					System.out.println("Invalid choice, pick between 1-6.");
				}
 
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Enter a number, not text.");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Something went wrong: " + e.getMessage());
			}
		}
		scanner.close();
	}
}