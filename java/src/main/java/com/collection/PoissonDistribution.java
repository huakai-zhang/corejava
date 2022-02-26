package com.collection;

/**
 * @author 春阳
 * @date 2021-06-22 15:12
 * 泊松分布
 */
public class PoissonDistribution {
    public static void main(String[] args) {
        for (double k = 0; k <= 8; k++) {
            double j = Math.pow(0.5, k);
            double e = Math.exp(-0.5);
            double h = factorial(k);

            System.out.printf("%.8f", (j * e) / h);
            System.out.println();
        }
    }

    public static double factorial(double number) {
        if (number <= 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }
}