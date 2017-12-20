package com.lexach.CalculationAlgorithms.metodProgonki;

import java.util.Random;

public class Progonka {

    //рассчет параметра delta = -d_i / (c_i + b_i * delta_i-1)
    private static double[] deltaCompute(ThreeDiagonalSist sist) {
        double[][] a = sist.getA();
        double[] b = sist.getB();
        int n = sist.getN();

        double[] delta = new double[sist.getN()];

        delta[0] = -a[0][1] / a[0][0];

        for (int i = 1; i < n - 1; i++) {
            delta[i] = a[i][i + 1] / (a[i][i] + a[i][i - 1] * delta[i - 1]);
        }
        delta[n - 1] = 0;

        return delta;
    }

    //рассчет delta = (r_i - b_i * lambda_i-1) / (c_i + b_i * delta_i-1)
    private static double[] lambdaCompute(double[] delta, ThreeDiagonalSist sist) {
        double[][] a = sist.getA();
        double[] b = sist.getB();
        int n = sist.getN();

        double[] lambda = new double[n];

        lambda[0] = b[0] / a[0][0];

        //for Q:
        for (int i = 1; i < n; i++) {
            lambda[i] = (b[i] - a[i][i - 1] * lambda[i - 1])
                    / (a[i][i] + a[i][i - 1] * delta[i - 1]);
        }

        return lambda;
    }

    private static double[] xCompute(double[] delta, double[] lambda, ThreeDiagonalSist sist) {
        double[][] a = sist.getA();
        double[] b = sist.getB();
        int n = sist.getN();

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

    public static void metodProgonki(ThreeDiagonalSist system){
        //вывод системы
        system.printSystem();

        //прямая прогонка
        double[] delta = deltaCompute(system);
        double[] lambda = lambdaCompute(delta, system);

        //поиск x (обратная прогонка)
        double[] x = xCompute(delta, lambda, system);

        //вывод ответа
        xPrint(x);
    }

    public static void main(String[] args) {
        //создание системы
        ThreeDiagonalSist system = new ThreeDiagonalSist();

        //вызов метода прогонки
        metodProgonki(system);
    }
}