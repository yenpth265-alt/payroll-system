//Phan Thi Hai Yen
//202419124
package payroll;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Payroll {
    private final String period;
    private final List<Employee> employees;

    public Payroll(String period) {
        if (period == null || period.isBlank()) {
            throw new IllegalArgumentException(
                "Payroll period must not be empty."
            );
        }

        this.period = period;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException(
                "Employee must not be null."
            );
        }

        if (findEmployee(employee.getEmployeeId()) != null) {
            throw new IllegalArgumentException(
                "Duplicate employee ID: "
                    + employee.getEmployeeId()
            );
        }

        employees.add(employee);
    }

    public Employee findEmployee(String employeeId) {
        return employees.stream()
            .filter(employee ->
                employee.getEmployeeId().equals(employeeId)
            )
            .findFirst()
            .orElse(null);
    }

    public double calculateTotalPayroll() {
        return employees.stream()
            .mapToDouble(Employee::calculateGrossPay)
            .sum();
    }

    public double calculatePayrollByDepartment(
        String department
    ) {
        return employees.stream()
            .filter(employee ->
                employee.getDepartment().equals(department)
            )
            .mapToDouble(Employee::calculateGrossPay)
            .sum();
    }

    public Employee findHighestPaidEmployee() {
        return employees.stream()
            .max(Comparator.comparingDouble(
                Employee::calculateGrossPay
            ))
            .orElse(null);
    }

    public void displayPayroll() {
        System.out.println("Payroll period: " + period);

        for (Employee employee : employees) {
            employee.displayPayrollInfo();
        }

        System.out.printf(
            "Total payroll: %.0f%n",
            calculateTotalPayroll()
        );
    }

    public String getPeriod() {
        return period;
    }

    public List<Employee> getEmployees() {
        return List.copyOf(employees);
    }
}
