//Phan Thi Hai Yen
//202419124
package payroll;

public class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double workedHours;

    public HourlyEmployee(
        String employeeId,
        String fullName,
        double hourlyRate
    ) {
        this(
            employeeId,
            fullName,
            "Unassigned",
            hourlyRate,
            0
        );
    }

    public HourlyEmployee(
        String employeeId,
        String fullName,
        String department,
        double hourlyRate,
        double workedHours
    ) {
        super(employeeId, fullName, department);

        if (hourlyRate < 0) {
            throw new IllegalArgumentException(
                "Hourly rate must not be negative."
            );
        }

        if (workedHours < 0 || workedHours > 250) {
            throw new IllegalArgumentException(
                "Worked hours must be between 0 and 250."
            );
        }

        this.hourlyRate = hourlyRate;
        this.workedHours = workedHours;
    }

    @Override
    public double calculateGrossPay() {
        double regularHours = Math.min(workedHours, 160);
        double overtimeHours = Math.max(workedHours - 160, 0);

        double basePay = regularHours * hourlyRate
            + overtimeHours * hourlyRate * 1.5;

        return basePay + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "Hourly Employee";
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
