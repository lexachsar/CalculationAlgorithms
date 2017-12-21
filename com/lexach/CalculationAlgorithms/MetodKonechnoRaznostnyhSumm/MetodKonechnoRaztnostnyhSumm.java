package com.lexach.CalculationAlgorithms.MetodKonechnoRaznostnyhSumm;

//import static com.lexach.CalculationAlgorithms.metodProgonki.Progonka.ThreeDiagonalSist;

import com.lexach.CalculationAlgorithms.metodProgonki.ThreeDiagonalSist;

import static com.lexach.CalculationAlgorithms.metodProgonki.Progonka.metodProgonki;

public class MetodKonechnoRaztnostnyhSumm {
    //возвращает p_i = p(x_i)
    private static double getPi(double x){
        return -(x * x * x + 1);
    }

    //возвращает q_i = q(x_i)
    private static double getQi(double x){
        return x * x - 1;
    }

    //возвращает r_i = r(x_i)
    private static double getRi(double x){
        return -Math.exp(1 - 2.5 * x * x);
    }

    //задаем начальную систему
    private static ThreeDiagonalSist systemGenerate() {
        //исходная функция: y'' + (x^3 + 1)y' - (x^2 - 1)y = e^(1 - 2.5 x^2)
        //умножаем на -1: -y'' - (x^3 + 1)y' + (x^2 - 1)y = -e^(1 - 2.5 x^2)

        int n = 10; //количество точек
        double lb = 0, rb = 1; //начальные условия

        double[][] a = new double[n][n];
        double[] b = new double[n];

        double h = (rb - lb) / n; //шаг
        double[] x = new double[n];
        x[0] = lb; x[n - 1] = rb; //по условию концы известны

        //вычисляем x_i
        for(int i = 1; i < n - 1; i++){
            x[i] = x[0] + i * h;
        }

        //вычисляем матрицу коэффициентов
        a[0][0] = 0.5 * (1 + getPi(x[0]) * h / 2); //коэф.: B_1
        for(int i = 1; i < n; i++){
            a[i][i - 1] = 0.5 * (1 + getPi(x[i]) * h / 2); //A
            a[i][i] = 0.5 * (1 - getPi(x[i]) * h / 2); //B
            a[i - 1][i] = 1 + h * h / 2 * getQi(x[i]); //C
            b[i] = h * h / 2 * getRi(x[i]); //F
        }

        return new ThreeDiagonalSist(a, b, n);

    }

    public static void main(String[] args) {
        ThreeDiagonalSist sist = systemGenerate();

        //вывод системы
        sist.printSystem();
        System.out.println();

        metodProgonki(sist);
    }
}

