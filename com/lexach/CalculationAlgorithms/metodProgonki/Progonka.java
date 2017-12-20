package com.lexach.CalculationAlgorithms.metodProgonki;

import java.util.Random;

public class Progonka {

    private static final int n = 3;
    private static final double[][] a = new double[n][n];
    private static final double[] b = new double[n];

    //создание системы из рандомных чисел
    private static void createSystem() {
        Random r = new Random();
        a[0][0] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        b[0] = (double) ((int) (r.nextDouble() * 1000)) / 10;

        for (int i = 1; i < n; i++) {
            a[i][i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            a[i - 1][i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            a[i][i - 1] = (double) ((int) (r.nextDouble() * 1000)) / 10;

            b[i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        }
    }

    //вывод системы
    private static void printSystem() {
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }

            System.out.println(b[i]);
        }
    }

    //рассчет параметра delta = -d_i / (c_i + b_i * delta_i-1)
    private static double[] deltaCompute() {
        double[] delta = new double[n];

        delta[0] = -a[0][1] / a[0][0];

        for (int i = 1; i < n - 1; i++) {
            delta[i] = a[i][i + 1] / (a[i][i] + a[i][i - 1] * delta[i - 1]);
        }
        delta[n - 1] = 0;

        return delta;
    }

    //рассчет delta = (r_i - b_i * lambda_i-1) / (c_i + b_i * delta_i-1)
    private static double[] lambdaCompute(double[] delta) {
        double[] lambda = new double[n];

        lambda[0] = b[0] / a[0][0];

        //for Q:
        for (int i = 1; i < n; i++) {
            lambda[i] = (b[i] - a[i][i - 1] * lambda[i - 1])
                    / (a[i][i] + a[i][i - 1] * delta[i - 1]);
        }

        return lambda;
    }

    private static double[] xCompute(double[] delta, double[] lambda) {
        double[] x = new double[n];
        x[n - 1] = lambda[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = delta[i] * x[i + 1] + lambda[i];
        }

        return x;
    }

    //вывод ответа
    private static void xPrint(double[] x) {
        for (int i = 0; i < x.length; i++)
            System.out.print("x[" + (i + 1) + "] = " + x[i] + "\n");
    }

    public static void

    public static void main(String[] args) {

        //создание системы
        createSystem();

        //вывод системы
        printSystem();

        //прямая прогонка
        double[] delta = deltaCompute();
        double[] lambda = lambdaCompute(delta);

        //поиск x (обратная прогонка)
        double[] x = xCompute(delta, lambda);

        //вывод ответа
        xPrint(x);
    }
}