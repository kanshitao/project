public class question {
    private double a;
    private double b;
    private double c;

    public question(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // 计算判别式
    private double discriminant() {
        return b * b - 4 * a * c;
    }

    // 计算并返回方程的解
    public void solve() {
        double delta = discriminant();

        // 判断判别式的值
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("两个实数解: x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            System.out.println("一个实数解: x = " + x);
        } else {
            System.out.println("无实数解");
        }
    }

    public static void main(String[] args) {
        question equation = new question(1, -3, 2);
        equation.solve();
    }
}
