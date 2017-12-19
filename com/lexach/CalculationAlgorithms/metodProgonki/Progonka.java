package com.lexach.CalculationAlgorithms.metodProgonki;

import java.util.Random;

public class Progonka{

    public static void main(String[] args) {

        int n = 3;
        double[][] a = new double[n][n];
        double[] b = new double[n];

        //забиваем матрицу коэффициентов и столбец
        Random r = new Random();
        a[0][0] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        b[0] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        for(int i = 1; i < n; i++){
            a[i][i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            a[i - 1][i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            a[i][i - 1] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            b[i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        }

        //вывод матрицы и столбца
        printMatrix(a, b);

        //прямой ход
        double[] p = new double[n];
        double[] q = new double[n];

        p[0] = -a[0][1] / a[0][0];
        q[0] = b[0] / a[0][0];

        //for P:
        for(int i = 1; i < n - 1; i++){
            p[i] = a[i][i + 1] / (a[i][i] + a[i][i - 1] * p[i - 1]);
        }
        p[n - 1] = 0;

        //for Q:
        for(int i = 1; i < n; i++){
            q[i] = (b[i] - a[i][i - 1] * q[i - 1])
                    / (a[i][i] + a[i][i - 1] * p[i - 1]);
        }

        //finding X:
        double[] x = new double[n];
        x[n - 1] = q[n - 1];
        for(int i = n - 2; i >= 0; i--){
            x[i] = p[i] * x[i + 1] + q[i];
        }

        //вывод ответа
        for(int i = 0; i < x.length; i++){
            System.out.print("x[");
            System.out.print(i + 1);
            System.out.print("] = ");
            System.out.print(x[i]);
            System.out.println();
        }
    }

    //метод для вывода матрицы в System.out
    static void printMatrix(double a[][], double b[]){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println(b[i]);
        }
    }

}
