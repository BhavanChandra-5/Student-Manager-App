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
            System.out.println("4. Save & Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID (Number only): ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        
                        // Basic Validation: Prevent empty names
                        if (name.trim().isEmpty()) {
                            System.out.println("Error: Name cannot be empty. Please try again.");
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
                        System.out.print("Enter ID to search (Number only): ");
                        int searchId = scanner.nextInt();
                        manager.searchStudent(searchId);
                        break;
                        
                    case 4:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
                
            } catch (InputMismatchException e) {
                // This catches the error if they type letters instead of numbers
                System.out.println("❌ ERROR: Invalid input! You must enter a number, not text.");
                
                // CRITICAL: We must clear the bad input out of the scanner, otherwise it loops forever!
                scanner.nextLine(); 
                
            } catch (Exception e) {
                // This catches any other random errors so the app never crashes
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}