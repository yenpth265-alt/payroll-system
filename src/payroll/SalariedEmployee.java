//Phan Thi Hai Yen
//202419124
package payroll;

public class SalariedEmployee extends Employee {
    private final double monthlySalary;
    private double responsibilityAllowance;

    public SalariedEmployee(
        String employeeId,
        String fullName,
        double monthlySalary
    ) {
        this(employeeId, fullName, "Unassigned", monthlySalary, 0);
    }

    public SalariedEmployee(
        String employeeId,
        String fullName,
        String department,
        double monthlySalary,
        double responsibilityAllowance
    ) {
        super(employeeId, fullName, department);

        if (monthlySalary < 0) {
            throw new IllegalArgumentException(
                "Monthly salary must not be negative."
            );
        }

        if (responsibilityAllowance < 0) {
            throw new IllegalArgumentException(
                "Responsibility allowance must not be negative."
            );
        }

        this.monthlySalary = monthlySalary;
        this.responsibilityAllowance = responsibilityAllowance;
    }

    @Override
    public double calculateGrossPay() {
        return monthlySalary
            + responsibilityAllowance
            + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "Salaried Employee";
    }

    @Override
    public void displayPayrollInfo() {
        System.out.printf(
            "%s | %s | %s | Gross pay: %.0f%n",
            getEmployeeId(),
            getFullName(),
            getEmployeeType(),
            calculateGrossPay()
        );
    }
}
