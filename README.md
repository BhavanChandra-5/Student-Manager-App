# Student Manager Application (Java + JDBC + MySQL)
A robust console-based CRUD application built to manage student records with real-time database persistence.

## 🚀 Features
- **Full CRUD Support**: Add, View, Search, Update, and Delete student records.
- **Database Persistence**: Integrated with MySQL using JDBC for permanent data storage.
- **Secure Queries**: Utilizes `PreparedStatement` to prevent SQL Injection.
- **Crash-Proof**: Implemented comprehensive `try-catch` exception handling for user input validation.
- **Clean Architecture**: Built using OOP principles for modularity and readability.

## 🛠 Tech Stack
- **Language**: Core Java
- **Database**: MySQL
- **Driver**: MySQL Connector/J
- **Tools**: Eclipse IDE

## ▶️ How to Run
1. Clone this repository.
2. Create a MySQL database named `student_db`.
3. Create the `students` table:
```sql
   CREATE TABLE students (
       id INT PRIMARY KEY,
       name VARCHAR(100),
       course VARCHAR(100)
   );
```
4. Open the project in Eclipse and update the `USER` and `PASSWORD` fields in `StudentManager.java` to match your local MySQL setup.
5. Run `Main.java`.
6. Use the console menu to Add, List, Search, Update, or Delete student records.

## 📋 Sample Menu
1. Add Student
2. List All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Save & Exit
