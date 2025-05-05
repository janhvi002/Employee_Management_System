import java.util.ArrayList;
import java.util.Scanner;

// Employee class to store employee details
class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;

    // Constructor to initialize employee attributes
    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Getter methods to access private fields
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }

    // Setter methods to update salary and department
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }

    // Method to display employee information
    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Department: " + department);
    }
}

// Main class to manage employees
public class EmployeeManagementSystem {

    // ArrayList to store multiple employee records
    private static ArrayList<Employee> employeeList = new ArrayList<>();

    // Scanner object to read user input
    private static Scanner scanner = new Scanner(System.in);

    // Main method - program execution starts here
    public static void main(String[] args) {
        int choice;

        System.out.println("=== Welcome to Employee Management System ===");

        // Loop to keep showing menu until user exits
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Validate input: check if user enters an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number!");
                scanner.next(); // clear invalid input
            }

            choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    addEmployee(); // Call method to add employee
                    break;
                case 2:
                    viewEmployees(); // Call method to view employees
                    break;
                case 3:
                    updateEmployee(); // Call method to update employee
                    break;
                case 4:
                    deleteEmployee(); // Call method to delete employee
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1-5.");
            }
        } while (choice != 5); // Continue loop until user chooses to exit

        scanner.close(); // Close scanner resource
    }

    // Method to add a new employee to the list
    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume leftover newline
        System.out.print("Enter Employee Department: ");
        String department = scanner.nextLine();

        // Create Employee object and add to list
        Employee emp = new Employee(id, name, salary, department);
        employeeList.add(emp);

        System.out.println("Employee added successfully!");
    }

    // Method to view all employees
    private static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n--- Employee List ---");

        // Loop through each employee and display their details
        for (Employee emp : employeeList) {
            emp.displayInfo();
            calculateNetSalary(emp); // Also show net salary after tax
        }
    }

    // Method to update an existing employee's details
    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        Employee emp = findEmployeeById(id);

        // Check if employee exists
        if (emp != null) {
            System.out.println("Employee found: ");
            emp.displayInfo();
            System.out.println("What do you want to update?");
            System.out.println("1. Salary");
            System.out.println("2. Department");
            int updateChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (updateChoice) {
                case 1:
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    emp.setSalary(newSalary); // Update salary
                    System.out.println("Salary updated successfully.");
                    break;
                case 2:
                    System.out.print("Enter new department: ");
                    String newDepartment = scanner.nextLine();
                    emp.setDepartment(newDepartment); // Update department
                    System.out.println("Department updated successfully.");
                    break;
                default:
                    System.out.println("Invalid update choice.");
            }
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // Method to delete an employee from the list
    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        Employee emp = findEmployeeById(id);

        // Check if employee exists before deleting
        if (emp != null) {
            employeeList.remove(emp); // Remove employee from list
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // Helper method to find an employee by ID
    private static Employee findEmployeeById(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null; // Return null if not found
    }

    // Method to calculate and display net salary after 10% tax deduction
    private static void calculateNetSalary(Employee emp) {
        double tax = 0.10 * emp.getSalary(); // 10% tax
        double netSalary = emp.getSalary() - tax;
        System.out.println("Net Salary after 10% Tax for " + emp.getName() + ": " + netSalary);
    }
}
