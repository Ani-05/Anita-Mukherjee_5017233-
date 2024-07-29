public class EmployeeManager {

    // Inner Employee class
    public static class Employee {
        private int employeeId;
        private String name;
        private String position;
        private double salary;

        // Constructor
        public Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        // Getters and Setters
        public int getEmployeeId() { 
            return employeeId; 
        }
        public void setEmployeeId(int employeeId) { 
            this.employeeId = employeeId; 
        }

        public String getName() { 
            return name; 
        }
        public void setName(String name) { 
            this.name = name; 
        }

        public String getPosition() { 
            return position; 
        }
        public void setPosition(String position) {
             this.position = position; 
        }

        public double getSalary() {
             return salary; 
        }
        public void setSalary(double salary) {
             this.salary = salary; 
        }

        @Override
        public String toString() {
            return "Employee ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary;
        }
    }

    // EmployeeManager class
    private Employee[] employees;
    private int size;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add an employee
    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size++] = employee;
        } else {
            System.out.println("Employee array is full.");
        }
    }

    // Search for an employee by ID
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse all employees
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete an employee by ID
    public void deleteEmployee(int employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                employees[i] = employees[i + 1];
            }
            employees[--size] = null;
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(10);

        // Adding employees
        manager.addEmployee(new Employee(1, "John Doe", "Developer", 50000));
        manager.addEmployee(new Employee(2, "Jane Smith", "Manager", 60000));
        manager.addEmployee(new Employee(3, "Emily Davis", "Designer", 55000));

        // Traversing employees
        System.out.println("All Employees:");
        manager.traverseEmployees();

        // Searching for an employee
        System.out.println("\nSearching for employee with ID 2:");
        Employee employee = manager.searchEmployee(2);
        System.out.println(employee != null ? employee : "Employee not found");

        // Deleting an employee
        System.out.println("\nDeleting employee with ID 2");
        manager.deleteEmployee(2);

        // Traversing employees after deletion
        System.out.println("\nAll Employees after deletion:");
        manager.traverseEmployees();
    }
}
