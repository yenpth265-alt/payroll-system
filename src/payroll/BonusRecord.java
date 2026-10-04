//Phan Thi Hai Yen
//202419124
package payroll;

public class BonusRecord {
    private final double amount;
    private final String reason;

    public BonusRecord(double amount, String reason) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Bonus amount must be greater than 0."
            );
        }

        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Bonus reason must not be empty."
            );
        }

        this.amount = amount;
        this.reason = reason.trim();
    }

    public double getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return String.format(
            "%,.0f VND - %s",
            amount,
            reason
        );
    }
}
