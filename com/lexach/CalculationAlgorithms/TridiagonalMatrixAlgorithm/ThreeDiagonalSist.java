package com.lexach.CalculationAlgorithms.TridiagonalMatrixAlgorithm;

import java.util.Random;

public class ThreeDiagonalSist {

    private static int n;
    private static double[][] a = new double[n][n];
    private static double[] b = new double[n];

    //создание системы из рандомных чисел
    public ThreeDiagonalSist(int N) {
        n = N;
        a = new double[n][n];
        b = new double[n];

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

    public ThreeDiagonalSist(double[][] arrA, double[] arrB, int N){
        n = N;
        arrA = a;
        arrB = b;
    }

    //возврат n
    public static int getN(){
        return n;
    }

    //возврат матрицы A
    public static double[][] getA(){
        return a;
    }

    //возврат матрицы B
    public static double[] getB(){
        return b;
    }

    //вывод системы
    public static void printSystem() {
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }

            System.out.println(b[i]);
        }
    }


}
