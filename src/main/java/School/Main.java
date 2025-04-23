package School;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static StudentDAO studentDAO = new StudentDAO();
    private static TeacherDAO teacherDAO = new TeacherDAO();
    private static CourseDAO courseDAO = new CourseDAO();

    private static List<Student> students = new ArrayList<>();
    static List<Teacher> teachers = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    public static void exportStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            // Loop through all students and write their details to the file
            for (Student student : students) {
                // Write each student's details (ID, Name, Age, Email, Grade) in CSV format
                writer.write(student.getStudentId() + "," + student.getName() + "," + student.getAge() + "," +
                        student.getEmail() + "," + student.getGrade() + "\n");
            }
            System.out.println("Students exported successfully to students.txt.");
        } catch (IOException e) {
            System.out.println("Error exporting students: " + e.getMessage());
        }
    }

    public static void exportTeachersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("teachers.txt"))) {
            // Loop through all teachers and write their details to the file
            for (Teacher teacher : teachers) {
                writer.write(teacher.getTeacherId() + "," + teacher.getName() + "," + teacher.getAge() + "," +
                        teacher.getEmail() + "," + teacher.getSubject() + "\n");
            }
            System.out.println("Teachers exported successfully to teachers.txt.");
        } catch (IOException e) {
            System.out.println("Error exporting teachers: " + e.getMessage());
        }
    }

    public static void importStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] data = line.split(",");

                // Convert string data to appropriate data types
                int studentId = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String email = data[3];
                int grade = Integer.parseInt(data[4]);

                // Create a new Student object using the parsed data
                Student student = new Student(name, age, email, grade, studentId);
                // Add the student to the students list
                students.add(student);
            }
            System.out.println("Students imported successfully from students.txt.");
        } catch (IOException e) {
            System.out.println("Error importing students: " + e.getMessage());
        }
    }

    public static void importTeachersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int teacherId = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String email = data[3];
                String subject = data[4];

                // Create a new Teacher object using the parsed data
                Teacher teacher = new Teacher(name, age, email, teacherId, subject);
                // Add the teacher to the teachers list
                teachers.add(teacher);
            }
            System.out.println("Teachers imported successfully from teachers.txt.");
        } catch (IOException e) {
            System.out.println("Error importing teachers: " + e.getMessage());
        }
    }


    private static int studentIdCounter = 1;
    private static int teacherIdCounter = 1;
    private static int courseIdCounter = 100;

    // Method to get all students from the database
    public static void getAllStudents() {
        List<Student> allStudents = studentDAO.getAllStudents(); // Fetch all students from the DAO
        for (Student student : allStudents) {
            System.out.println(student); // Display each student
        }
    }

    // Method to get a teacher by ID from the database
    public static void getTeacherById(Scanner scanner) {
        System.out.print("Enter Teacher ID: ");
        int teacherId = scanner.nextInt();
        Teacher teacher = teacherDAO.getTeacherById(teacherId); // Fetch teacher from the DAO by ID
        if (teacher != null) {
            System.out.println(teacher); // Display the teacher details
        } else {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }

    // Method to get a course by ID
    public static void getCourseById(Scanner scanner) {
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        Course course = courseDAO.getCourseById(courseId); // Fetch course from the DAO by ID
        if (course != null) {
            System.out.println(course); // Display the course details
        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    // Method to get all courses from the database
    public static void getAllCourses() {
        List<Course> allCourses = courseDAO.getAllCourses(); // Fetch all courses from the DAO
        for (Course course : allCourses) {
            System.out.println(course); // Display each course
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- School Management System Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Export Students to File");
            System.out.println("5. Export Teachers to File");
            System.out.println("6. Import Students from File");
            System.out.println("7. Import Teachers from File");
            System.out.println("8. Assign Course to Student");
            System.out.println("9. Assign Course to Teacher");
            System.out.println("10. View Student by ID");
            System.out.println("11. View All Students and Teachers");
            System.out.println("12. View All Courses");
            System.out.println("13. Update Student");
            System.out.println("14. Delete Student");
            System.out.println("15. Filter Students by Grade");
            System.out.println("16. Count Students in Grade");
            System.out.println("17. Sort Students Alphabetically");
            System.out.println("18. Process Students Concurrently");
            System.out.println("19. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addTeacher(scanner);
                    break;
                case 3:
                    addCourse(scanner);
                    break;
                case 4:
                    exportStudentsToFile();
                    break;
                case 5:
                    exportTeachersToFile();
                    break;
                case 6:
                    importStudentsFromFile();
                    break;
                case 7:
                    importTeachersFromFile();
                    break;
                case 8:
                    assignCourseToStudent(scanner);
                    break;
                case 9:
                    assignCourseToTeacher(scanner);
                    break;
                case 10:
                    viewStudentById(scanner);
                    break;
                case 11:
                    viewAllStudentsAndTeachers();
                    break;
                case 12:
                    viewAllCourses();
                    break;
                case 13:
                    updateStudent(scanner);
                    break;
                case 14:
                    deleteStudent(scanner);
                    break;
                case 15:
                    System.out.print("Enter grade to filter: ");
                    int gradeToFilter = scanner.nextInt();
                    scanner.nextLine();
                    filterStudentsByGrade(gradeToFilter);
                    break;

                case 16:
                    System.out.print("Enter grade to count: ");
                    int gradeToCount = scanner.nextInt();
                    scanner.nextLine();
                    countStudentsInGrade(gradeToCount);
                    break;

                case 17:
                    sortStudentsAlphabetically();
                    break;

                case 18:
                    processStudentsConcurrently();
                    break;
                case 19:
                    System.out.println("Exiting Program.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            // Validate age
            int age;
            while (true) {
                System.out.println("Enter student age: ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    scanner.nextLine(); // Remember to buffer
                    if (age >= 0) { // Fixing issue grayson pointed out about being able to enter negative
                        break;
                    } else {
                        System.out.println("Age cannot be negative. Please try again.");
                    }
                }else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter student email: ");
            String email = scanner.nextLine();

            System.out.println("Enter grade (1-12): ");
            int grade = scanner.nextInt();
            scanner.nextLine();

            int studentId = studentIdCounter++;

            Student student = new Student(name, age, email, grade, studentId);
            students.add(student);
            studentDAO.insertStudent(student);

            System.out.println("Student added Successfully! ID: " + studentId);
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void addTeacher(Scanner scanner) {
        try {
            System.out.println("Enter teacher name: ");
            String name = scanner.nextLine();

            System.out.println("Enter teacher age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter teacher email: ");
            String email = scanner.nextLine();

            System.out.println("Enter subject: ");
            String subject = scanner.nextLine();

            int teacherId = teacherIdCounter++;

            Teacher teacher = new Teacher(name, age, email, teacherId, subject);
            teachers.add(teacher);
            teacherDAO.insertTeacher(teacher);

            System.out.println("Teacher added successfully! ID: " + teacherId);
        } catch (Exception e) {
            System.out.println("Error adding teacher: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void addCourse(Scanner scanner) {
        try {
            System.out.println("Enter course name: ");
            String courseName = scanner.nextLine();

            int courseId = courseIdCounter++;

            Course course = new Course(courseName, courseId);
            courses.add(course);
            courseDAO.insertCourse(course);

            System.out.println("Course added successfully! ID: " + courseId);
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private static void assignCourseToStudent(Scanner scanner) {
        System.out.println("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = null;
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ": " + course.getCourseName());
        }

        System.out.println("Enter course ID to assign: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        Course courseToAssign = null;
        for (Course c : courses) {
            if (c.getCourseId() == courseId) {
                courseToAssign = c;
                break;
            }
        }

        if (courseToAssign == null) {
            System.out.println("Course not found.");
            return;
        }

        boolean success = student.addCourse(courseToAssign);
        if (success) {
            System.out.println("Course assigned to student.");
        }
    }

    private static void assignCourseToTeacher (Scanner scanner) {
        System.out.println("Enter teacher ID: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        Teacher teacher = null;
        for (Teacher t : teachers) {
            if (t.getTeacherId() == teacherId) {
                teacher = t;
                break;
            }
        }

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ": " + course.getCourseName());
        }

        System.out.println("Enter course ID to assign: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        Course courseToAssign = null;
        for (Course c : courses) {
            if (c.getCourseId() == courseId) {
                courseToAssign = c;
                break;
            }
        }

        if (courseToAssign == null) {
            System.out.println("Course could not be found.");
            return;
        }

        boolean success = teacher.addCourse(courseToAssign);
        if (success) {
            System.out.println("Course assigned to teacher.");
        }
    }

    private static void viewStudentById (Scanner scanner){
        System.out.println("Enter student ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Fetch student from the database using DAO
        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n--- Student Info ---");
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Grade: " + student.getGrade());
        System.out.println("ID: " + student.getStudentId());

        System.out.println("Courses:");
        if (student.getCourses().isEmpty()) {
            System.out.println("  No courses assigned.");
        } else {
            for (Course c : student.getCourses()) {
                System.out.println(" " + c.getCourseName() + " (ID: " + c.getCourseId() + ")");
            }
        }
    }

    private static void viewAllStudentsAndTeachers () {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }

        System.out.println("\n--- All Teachers ---");
        if (teachers.isEmpty()) {
            System.out.println("No teachers available.");
        } else {
            for (Teacher t : teachers) {
                System.out.println(t);
            }
        }
    }

    private static void viewAllCourses () {
        System.out.println("\n--- All Courses ----");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course c : courses) {
                System.out.println("Course ID: " + c.getCourseId() + ", Name: " + c.getCourseName());
            }
        }
    }

    private static void updateStudent (Scanner scanner){
        System.out.println("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = null;
        for (Student s : students) {
            if (s.getStudentId() == id) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        try {
            System.out.print("Enter new name (current: " + student.getName() + "): ");
            String name = scanner.nextLine();
            student.setName(name);

            System.out.print("Enter new age (current: " + student.getAge() + "): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            student.setAge(age);

            System.out.print("Enter new email (current: " + student.getEmail() + "): ");
            String email = scanner.nextLine();
            student.setEmail(email);

            System.out.print("Enter new grade (current: " + student.getGrade() + "): ");
            int grade = scanner.nextInt();
            scanner.nextLine();
            student.setGrade(grade);

            // Calling the DAO method to update student in the database
            studentDAO.updateStudent(student);

            System.out.println("Student updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student studentToRemove = null;
        for (Student s : students) {
            if (s.getStudentId() == id) {
                studentToRemove = s;
                break;
            }
        }

        if (studentToRemove == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(studentToRemove);
        studentDAO.deleteStudent(id); // Deletes from DB
        System.out.println("Student removed successfully.");
    }

    // Filter students by grade using Streams
    public static void filterStudentsByGrade(int grade) {
        System.out.println("\n--- Students in Grade " + grade + " ---");
        students.stream()
                .filter(student -> student.getGrade() == grade)
                .forEach(System.out::println);
    }

    // Count how many students are in the given grade
    public static void countStudentsInGrade(int grade) {
        long count = students.stream()
                .filter(student -> student.getGrade() == grade)
                .count();
        System.out.println("Number of students in grade " + grade + ": " + count);
    }

    // Sort students alphabetically by name using Streams
    public static void sortStudentsAlphabetically() {
        System.out.println("\n--- Students Sorted Alphabetically ---");
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(System.out::println);
    }

    // Use two threads to process the student list concurrently
    public static void processStudentsConcurrently() {
        int mid = students.size() / 2;

        Runnable task1 = () -> {
            System.out.println("\nThread 1: First Half of Students");
            for (int i = 0; i < mid; i++) {
                System.out.println(students.get(i));
            }
        };

        Runnable task2 = () -> {
            System.out.println("\nThread 2: Second Half of Students");
            for (int i = mid; i < students.size(); i++) {
                System.out.println(students.get(i));
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted: " + e.getMessage());
        }

        System.out.println("\n--- Both threads completed ---");
    }
}