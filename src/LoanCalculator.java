import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class LoanCalculator {
    private BigDecimal loanAmount;  // 贷款总额
    private int loanMonths;         // 贷款时间（月）
    private BigDecimal annualInterestRate;  // 年利率

    // 构造函数
    public LoanCalculator(BigDecimal loanAmount, int loanMonths, BigDecimal annualInterestRate) {
        this.loanAmount = loanAmount;
        this.loanMonths = loanMonths;
        this.annualInterestRate = annualInterestRate;
    }

    // 计算月利率
    private BigDecimal getMonthlyInterestRate() {
        return annualInterestRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);
    }

    // 等额本金还款方式总利息
    public BigDecimal calculateEqualPrincipalInterest() {
        BigDecimal monthlyInterestRate = getMonthlyInterestRate();
        BigDecimal totalInterest = loanAmount
                .multiply(monthlyInterestRate)
                .multiply(BigDecimal.valueOf(loanMonths + 1))
                .divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP);
        return totalInterest;
    }

    // 等额本息还款方式月还款额
    private BigDecimal calculateMonthlyPaymentEqualInstallments(BigDecimal monthlyInterestRate) {
        BigDecimal onePlusInterestRate = monthlyInterestRate.add(BigDecimal.ONE);
        BigDecimal temp = onePlusInterestRate.pow(loanMonths);
        return loanAmount.multiply(monthlyInterestRate).multiply(temp)
                .divide(temp.subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
    }

    // 等额本息还款方式总利息
    public BigDecimal calculateEqualPaymentInterest() {
        BigDecimal monthlyInterestRate = getMonthlyInterestRate();
        BigDecimal monthlyPayment = calculateMonthlyPaymentEqualInstallments(monthlyInterestRate);
        BigDecimal totalPayment = monthlyPayment.multiply(BigDecimal.valueOf(loanMonths));
        return totalPayment.subtract(loanAmount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入贷款总额，贷款时间，年利率
        System.out.print("请输入贷款总额(万元): ");
        BigDecimal loanAmount = scanner.nextBigDecimal();
        System.out.print("请输入贷款时间(月): ");
        int loanMonths = scanner.nextInt();
        System.out.print("请输入年利率(%): ");
        BigDecimal annualInterestRate = scanner.nextBigDecimal();

        // 创建LoanCalculator对象
        LoanCalculator calculator = new LoanCalculator(loanAmount, loanMonths, annualInterestRate);

        // 计算两种方式的总利息
        BigDecimal equalPrincipalInterest = calculator.calculateEqualPrincipalInterest();
        BigDecimal equalPaymentInterest = calculator.calculateEqualPaymentInterest();

        // 输出结果
        System.out.println("等额本金还款总利息: " + equalPrincipalInterest.setScale(2, RoundingMode.HALF_UP) + " 万元");
        System.out.println("等额本息还款总利息: " + equalPaymentInterest.setScale(2, RoundingMode.HALF_UP) + " 万元");

        scanner.close();
    }
}