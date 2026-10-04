package payroll;

public class SalesEmployee extends Employee {
    private double baseSalary;
    private double salesRevenue;
    private double commissionRate;

    public SalesEmployee(
        String employeeId,
        String fullName,
        double baseSalary
    ) {
        this(
            employeeId,
            fullName,
            "Unassigned",
            baseSalary,
            0,
            0
        );
    }

    public SalesEmployee(
        String employeeId,
        String fullName,
        String department,
        double baseSalary,
        double salesRevenue,
        double commissionRate
    ) {
        super(employeeId, fullName, department);

        if (baseSalary < 0) {
            throw new IllegalArgumentException(
                "Base salary must not be negative."
            );
        }

        if (salesRevenue < 0) {
            throw new IllegalArgumentException(
                "Sales revenue must not be negative."
            );
        }

        if (commissionRate < 0 || commissionRate > 0.3) {
            throw new IllegalArgumentException(
                "Commission rate must be between 0 and 0.3."
            );
        }

        this.baseSalary = baseSalary;
        this.salesRevenue = salesRevenue;
        this.commissionRate = commissionRate;
    }

    public void updateSalesRevenue(double newSalesRevenue) {
        if (newSalesRevenue < 0) {
            throw new IllegalArgumentException(
                "Sales revenue must not be negative."
            );
        }

        this.salesRevenue = newSalesRevenue;
    }

    @Override
    public double calculateGrossPay() {
        return baseSalary
            + salesRevenue * commissionRate
            + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "Sales Employee";
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
