//Phan Thi Hai Yen
//202419124
package payroll;

public class Main {
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println(" HE THONG TINH LUONG VA THUONG NHAN SU");
        System.out.println(" KY LUONG: 2026-09");
        System.out.println("==============================================");

        Payroll payroll = createSamplePayroll();

        System.out.println("\n========== 1. BANG LUONG ==========");
        payroll.displayPayroll();

        System.out.println("\n========== 2. KIEM THU DU LIEU MAU ==========");

        Employee e001 = payroll.findEmployee("E001");
        Employee e002 = payroll.findEmployee("E002");
        Employee e003 = payroll.findEmployee("E003");
        Employee e004 = payroll.findEmployee("E004");

        testDouble(
            "TC01 - E001: Luong co dinh",
            18_000_000,
            e001.calculateGrossPay()
        );

        testDouble(
            "TC02 - E002: Luong gio khong tang ca",
            15_500_000,
            e002.calculateGrossPay()
        );

        testDouble(
            "TC03 - E003: Luong gio co tang ca",
            17_500_000,
            e003.calculateGrossPay()
        );

        testDouble(
            "TC04 - E004: Luong kinh doanh",
            19_000_000,
            e004.calculateGrossPay()
        );

        testDouble(
            "TC05 - Tong bang luong",
            70_000_000,
            payroll.calculateTotalPayroll()
        );

        testDouble(
            "TC06 - Tong phong Ho tro",
            33_000_000,
            payroll.calculatePayrollByDepartment("Hỗ trợ")
        );

        Employee highestPaid = payroll.findHighestPaidEmployee();

        testString(
            "TC07 - Nhan vien thu nhap cao nhat",
            "E004",
            highestPaid == null ? null : highestPaid.getEmployeeId()
        );

        System.out.println("\n========== 3. KIEM THU NAP CHONG addBonus() ==========");

        SalariedEmployee bonusEmployee = new SalariedEmployee(
            "E100",
            "Nhan Vien Kiem Thu Thuong",
            "Kiem thu",
            10_000_000,
            0
        );

        // addBonus(double amount)
        bonusEmployee.addBonus(100_000);

        testDouble(
            "TC08 - addBonus(amount)",
            100_000,
            bonusEmployee.getMonthlyBonus()
        );

        // addBonus(double amount, String reason)
        bonusEmployee.addBonus(
            200_000,
            "Thuong ho tro du an"
        );

        testDouble(
            "TC09 - addBonus(amount, reason)",
            300_000,
            bonusEmployee.getMonthlyBonus()
        );

        // addBonus(double rate, double referenceAmount, String reason)
        bonusEmployee.addBonus(
            0.02,
            50_000_000,
            "Thuong theo ty le doanh so"
        );

        testDouble(
            "TC10 - addBonus(rate, referenceAmount, reason)",
            1_300_000,
            bonusEmployee.getMonthlyBonus()
        );

        System.out.println("\nLich su thuong cua E100:");

        for (BonusRecord bonus : bonusEmployee.getBonusHistory()) {
            System.out.println("- " + bonus);
        }

        System.out.println("\n========== 4. KIEM THU BIEN ==========");

        HourlyEmployee hourly160 = new HourlyEmployee(
            "E101",
            "Nhan Vien 160 Gio",
            "Kiem thu",
            100_000,
            160
        );

        testDouble(
            "TC11 - WorkedHours = 160, khong co tang ca",
            16_000_000,
            hourly160.calculateGrossPay()
        );

        HourlyEmployee hourly250 = new HourlyEmployee(
            "E102",
            "Nhan Vien 250 Gio",
            "Kiem thu",
            100_000,
            250
        );

        testDouble(
            "TC12 - WorkedHours = 250, gioi han hop le",
            29_500_000,
            hourly250.calculateGrossPay()
        );

        SalesEmployee salesBoundary = new SalesEmployee(
            "E103",
            "Nhan Vien Hoa Hong Toi Da",
            "Kiem thu",
            5_000_000,
            10_000_000,
            0.3
        );

        testDouble(
            "TC13 - CommissionRate = 0.3, gia tri hop le",
            8_000_000,
            salesBoundary.calculateGrossPay()
        );

        System.out.println("\n========== 5. KIEM THU LOI ==========");

        testException(
            "TC14 - Khong cho employeeId rong",
            () -> new SalariedEmployee(
                "",
                "Test Empty ID",
                "Kiem thu",
                10_000_000,
                0
            )
        );

        testException(
            "TC15 - Khong cho fullName rong",
            () -> new SalariedEmployee(
                "E104",
                "   ",
                "Kiem thu",
                10_000_000,
                0
            )
        );

        testException(
            "TC16 - Khong cho department rong",
            () -> new SalariedEmployee(
                "E105",
                "Test Department",
                "",
                10_000_000,
                0
            )
        );

        testException(
            "TC17 - Khong cho luong co dinh am",
            () -> new SalariedEmployee(
                "E106",
                "Test Negative Salary",
                "Kiem thu",
                -1,
                0
            )
        );

        testException(
            "TC18 - Khong cho phu cap am",
            () -> new SalariedEmployee(
                "E107",
                "Test Negative Allowance",
                "Kiem thu",
                10_000_000,
                -1
            )
        );

        testException(
            "TC19 - Khong cho don gia gio am",
            () -> new HourlyEmployee(
                "E108",
                "Test Negative Rate",
                "Kiem thu",
                -100_000,
                100
            )
        );

        testException(
            "TC20 - Khong cho so gio lam am",
            () -> new HourlyEmployee(
                "E109",
                "Test Negative Hours",
                "Kiem thu",
                100_000,
                -1
            )
        );

        testException(
            "TC21 - Khong cho so gio lam lon hon 250",
            () -> new HourlyEmployee(
                "E110",
                "Test Too Many Hours",
                "Kiem thu",
                100_000,
                251
            )
        );

        testException(
            "TC22 - Khong cho doanh so am",
            () -> new SalesEmployee(
                "E111",
                "Test Negative Revenue",
                "Kiem thu",
                8_000_000,
                -1,
                0.05
            )
        );

        testException(
            "TC23 - Khong cho commissionRate > 0.3",
            () -> new SalesEmployee(
                "E112",
                "Test Commission Over Limit",
                "Kiem thu",
                8_000_000,
                100_000_000,
                0.31
            )
        );

        testException(
            "TC24 - Khong cho commissionRate am",
            () -> new SalesEmployee(
                "E113",
                "Test Negative Commission",
                "Kiem thu",
                8_000_000,
                100_000_000,
                -0.01
            )
        );

        testException(
            "TC25 - Khong cho bonus am",
            () -> bonusEmployee.addBonus(-100_000)
        );

        testException(
            "TC26 - Khong cho bonus bang 0",
            () -> bonusEmployee.addBonus(0)
        );

        testException(
            "TC27 - Khong cho ly do thuong rong",
            () -> bonusEmployee.addBonus(
                100_000,
                "  "
            )
        );

        testException(
            "TC28 - Khong cho rate thuong = 0",
            () -> bonusEmployee.addBonus(
                0,
                50_000_000,
                "Rate bang 0"
            )
        );

        testException(
            "TC29 - Khong cho rate thuong > 0.5",
            () -> bonusEmployee.addBonus(
                0.6,
                50_000_000,
                "Rate qua lon"
            )
        );

        testException(
            "TC30 - Khong cho referenceAmount <= 0",
            () -> bonusEmployee.addBonus(
                0.02,
                0,
                "Gia tri tham chieu bang 0"
            )
        );

        testException(
            "TC31 - Khong cho trung employeeId trong Payroll",
            () -> payroll.addEmployee(
                new SalariedEmployee(
                    "E001",
                    "Nhan Vien Trung Ma",
                    "Kiem thu",
                    10_000_000,
                    0
                )
            )
        );

        testException(
            "TC32 - Khong cho cap nhat doanh so am",
            () -> {
                SalesEmployee sales = new SalesEmployee(
                    "E114",
                    "Test Update Revenue",
                    "Kiem thu",
                    8_000_000,
                    10_000_000,
                    0.05
                );

                sales.updateSalesRevenue(-1);
            }
        );

        System.out.println("\n========== 6. KIEM THU Payroll RONG ==========");

        Payroll emptyPayroll = new Payroll("2026-10");

        testDouble(
            "TC33 - Tong Payroll rong = 0",
            0,
            emptyPayroll.calculateTotalPayroll()
        );

        testNull(
            "TC34 - Payroll rong khong co nguoi luong cao nhat",
            emptyPayroll.findHighestPaidEmployee()
        );

        System.out.println("\n==============================================");
        System.out.println(" KET QUA TONG KET");
        System.out.println("==============================================");

        System.out.println("So test PASS: " + passedTests);
        System.out.println("So test FAIL: " + failedTests);
        System.out.println("Tong so test: " + (passedTests + failedTests));

        if (failedTests == 0) {
            System.out.println("KET LUAN: TAT CA KIEM THU DEU DAT.");
        } else {
            System.out.println(
                "KET LUAN: CO KIEM THU CHUA DAT. HAY KIEM TRA LAI CODE."
            );
        }
    }

    private static Payroll createSamplePayroll() {
        Payroll payroll = new Payroll("2026-09");

        SalariedEmployee e001 = new SalariedEmployee(
            "E001",
            "Nguyễn Minh An",
            "Đào tạo",
            15_000_000,
            2_000_000
        );

        // Chọn addBonus(double amount, String reason)
        e001.addBonus(
            1_000_000,
            "Thuong co dinh thang"
        );

        HourlyEmployee e002 = new HourlyEmployee(
            "E002",
            "Trần Thu Bình",
            "Hỗ trợ",
            100_000,
            150
        );

        // Chọn addBonus(double amount)
        e002.addBonus(500_000);

        HourlyEmployee e003 = new HourlyEmployee(
            "E003",
            "Lê Hoàng Chi",
            "Hỗ trợ",
            100_000,
            170
        );

        SalesEmployee e004 = new SalesEmployee(
            "E004",
            "Phạm Quốc Dũng",
            "Kinh doanh",
            8_000_000,
            200_000_000,
            0.05
        );

        // Chọn addBonus(double rate, double referenceAmount, String reason)
        e004.addBonus(
            0.02,
            50_000_000,
            "Thuong 2 phan tram cua 50 trieu"
        );

        payroll.addEmployee(e001);
        payroll.addEmployee(e002);
        payroll.addEmployee(e003);
        payroll.addEmployee(e004);

        return payroll;
    }

    private static void testDouble(
        String testName,
        double expected,
        double actual
    ) {
        double epsilon = 0.0001;

        if (Math.abs(expected - actual) < epsilon) {
            passedTests++;

            System.out.printf(
                "PASS - %s | Expected: %,.0f | Actual: %,.0f%n",
                testName,
                expected,
                actual
            );
        } else {
            failedTests++;

            System.out.printf(
                "FAIL - %s | Expected: %,.0f | Actual: %,.0f%n",
                testName,
                expected,
                actual
            );
        }
    }

    private static void testString(
        String testName,
        String expected,
        String actual
    ) {
        boolean isPassed = expected == null
            ? actual == null
            : expected.equals(actual);

        if (isPassed) {
            passedTests++;

            System.out.printf(
                "PASS - %s | Expected: %s | Actual: %s%n",
                testName,
                expected,
                actual
            );
        } else {
            failedTests++;

            System.out.printf(
                "FAIL - %s | Expected: %s | Actual: %s%n",
                testName,
                expected,
                actual
            );
        }
    }

    private static void testNull(
        String testName,
        Object actual
    ) {
        if (actual == null) {
            passedTests++;

            System.out.println(
                "PASS - " + testName
            );
        } else {
            failedTests++;

            System.out.println(
                "FAIL - " + testName
                    + " | Expected: null"
                    + " | Actual: " + actual
            );
        }
    }

    private static void testException(
        String testName,
        Runnable action
    ) {
        try {
            action.run();

            failedTests++;

            System.out.println(
                "FAIL - " + testName
                    + " | Expected: IllegalArgumentException"
                    + " | Actual: no exception"
            );
        } catch (IllegalArgumentException exception) {
            passedTests++;

            System.out.println(
                "PASS - " + testName
                    + " | Message: "
                    + exception.getMessage()
            );
        } catch (Exception exception) {
            failedTests++;

            System.out.println(
                "FAIL - " + testName
                    + " | Expected: IllegalArgumentException"
                    + " | Actual: "
                    + exception.getClass().getSimpleName()
            );
        }
    }
}
