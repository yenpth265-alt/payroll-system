//Phan Thi Hai Yen
//202419124
package payroll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Employee {
    private String employeeId;
    private String fullName;
    private String department;
    private double monthlyBonus;

    // Lịch sử từng khoản thưởng
    private final List<BonusRecord> bonusHistory;

    protected Employee(String employeeId, String fullName) {
        this(employeeId, fullName, "Unassigned");
    }

    protected Employee(
        String employeeId,
        String fullName,
        String department
    ) {
        this.employeeId = validateText(employeeId, "Employee ID");
        this.fullName = validateText(fullName, "Full name");
        this.department = validateText(department, "Department");
        this.monthlyBonus = 0.0;
        this.bonusHistory = new ArrayList<>();
    }

    public void addBonus(double amount) {
        addBonus(amount, "General bonus");
    }

    public void addBonus(double amount, String reason) {
        validateBonusAmount(amount);
        String validReason = validateText(reason, "Bonus reason");

        monthlyBonus += amount;
        bonusHistory.add(
            new BonusRecord(amount, validReason)
        );
    }

    public void addBonus(
        double rate,
        double referenceAmount,
        String reason
    ) {
        if (rate <= 0 || rate > 0.5) {
            throw new IllegalArgumentException(
                "Bonus rate must be greater than 0 and at most 0.5."
            );
        }

        if (referenceAmount <= 0) {
            throw new IllegalArgumentException(
                "Reference amount must be greater than 0."
            );
        }

        double bonusAmount = rate * referenceAmount;

        addBonus(
            bonusAmount,
            validateText(reason, "Bonus reason")
        );
    }

    public void resetMonthlyBonus() {
        monthlyBonus = 0.0;
        bonusHistory.clear();
    }

    private static void validateBonusAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Bonus amount must be greater than 0."
            );
        }
    }

    private static String validateText(
        String value,
        String fieldName
    ) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                fieldName + " must not be empty."
            );
        }

        return value.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public double getMonthlyBonus() {
        return monthlyBonus;
    }

    public List<BonusRecord> getBonusHistory() {
        return Collections.unmodifiableList(bonusHistory);
    }

    public abstract double calculateGrossPay();

    public abstract String getEmployeeType();

    public abstract void displayPayrollInfo();
}
