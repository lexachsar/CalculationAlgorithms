package com.lexach.CalculationAlgorithms.NumericalDifferentiation;

import java.util.ArrayList;

public class Main {

    static double q_k(double x, double k) {
        return ((-x) / (2 * (2 * k + 1) * (2 * k + 2)));
    }

    public static void main(String[] args) {
        Double[][] result = new Double[17][6];

        double x, up_bound = 1.7, low_bound = 0, h = 0.1;

        for (x = low_bound; x <= up_bound; x = x + 0.1) {
            double sum = 1, Eps = 0.0001, Uk = 1;
            for (int k = 0; Math.abs(q_k(x, k + 1)) > Eps; k++) {
                //cout  << " " << q_k(x, k) << " " << Uk << " " << sum << endl;
                Uk = Uk * q_k(x, k);
                sum = sum + Uk;
            }

            result[(int) (x * 10)][0] = x;
            result[(int) (x * 10)][1] = sum;
            result[(int) (x * 10)][2] = Math.cos(Math.sqrt(x / 2));

            if (x != low_bound)
                result[(int) (x * 10 - 1)][3] = (result[(int) (x * 10)][1] - result[(int) (x * 10 - 1)][1]) / h;

            if (x != low_bound)
                result[(int) (x * 10)][4] = result[(int) (x * 10 - 1)][3];

            if (x > low_bound + 0.2)
                result[(int) (x * 10 - 1)][5] = (result[(int) (x * 10)][1] - result[(int) (x * 10 - 2)][1]) / (2 * h);
            //cout  << sum << " " << cos(sqrt(x/2));
            //if(x = )
        }

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 6; j++)
                if (result[i][j] == 0)
                    System.out.print("x\t");
                else
                    System.out.print(result[i][j] + "\t");
            System.out.println();
        }
    }
}