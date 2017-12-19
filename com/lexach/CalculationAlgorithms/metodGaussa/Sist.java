package com.lexach.CalculationAlgorithms.metodGaussa;

import java.util.Random;

class Sist{
    private static double[][] a;
    private static double[] b;
    private static int n;

    Sist(int inN){
        n = inN;
        a = new double[n][n];
        b = new double[n];

        //создание системы линейных уравнений
        for(int i = 0; i < n; i++){
            Random r = new Random();
            for(int j = 0; j < n; j++){
                a[i][j] = (double) ((int) (r.nextDouble() * 1000)) / 10;
            }
            b[i] = (double) ((int) (r.nextDouble() * 1000)) / 10;
        }
    }

    public static void print(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(a[i][j]);
                System.out.print("\t");
            }

            System.out.println(b[i]);
        }
        System.out.println();
    }

    public static void oneAtMainDiagonaleMaker(int line){
        for(int k = 0; k < n; k++){
            a[line][k] /= a[line][line];
        }
        b[line] /= a[line][line];
    }

    public static void zerosUnderMainDiagonaleMaker(int line){
        for(int i = line + 1; i < n; i++){
            for(int j = 0; j < n; j++){
                a[i][j] += a[line][j] * ( - a[i][line] / a[line][line] );
                b[i] += b[line] * ( -a[i][line] / b[line]);
            }
        }
    }

    public static void gaussPryamoy(){
        for(int j = 0; j < n; j++){
            oneAtMainDiagonaleMaker(j);

            zerosUnderMainDiagonaleMaker(j);
        }
    }

    public static double[] gaussObratniy(){
        double[] result = new double[n];
        double[] bvar = b.clone();
        for(int i = n - 1; i >= 0; i-- ){
            for(int j = i + 1; j < n; j++){
                bvar[i] = bvar[i] - a[i][j] * result[j];
            }
            result[i] =( bvar[i] );
        }
        return result;
    }

    public static void masPrint(double[] mas){
        for(int i = 0; i < n; i++)
            System.out.print(mas[i] + " ");
        System.out.println();
        System.out.println();
    }

    public static void gauss(){
        gaussPryamoy();

        masPrint( gaussObratniy() );
    }

}
