package com.lexach.CalculationAlgorithms.GaussianElimination;

import java.util.Random;

public class Sist {
    private static double[][] a;
    private static double[] b;
    private static int n;

    Sist(int inN) {
        n = inN;
        a = new double[n][n];
        b = new double[n];

        //создание системы линейных уравнений
        for (int i = 0; i < n; i++) {
            Random r = new Random();
            for (int j = 0; j < n; j++) {
                a[i][j] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            }
            b[i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        }
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]);
                System.out.print("\t");
            }

            System.out.println(b[i]);
        }
        System.out.println();
    }

    public double[][] getA() {
        return a;
    }

    public double[] getB() {
        return b;
    }

    public int getN() {
        return n;
    }

}
