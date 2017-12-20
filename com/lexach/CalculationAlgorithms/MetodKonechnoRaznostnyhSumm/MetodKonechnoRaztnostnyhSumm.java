package com.lexach.CalculationAlgorithms.MetodKonechnoRaznostnyhSumm;

public class MetodKonechnoRaztnostnyhSumm {

    public static void main(String[] args) {
        //èñõîäíàÿ ôóíêöèÿ: y'' + (x^3 + 1)y' - (x^2 - 1)y = e^(1 - 2.5 x^2)
        //óìíîæàåì íà -1: -y'' - (x^3 + 1)y' + (x^2 - 1)y = -e^(1 - 2.5 x^2)

        int n = 10; //êîëè÷åñòâî òî÷åê
        double a = 0, b = 1; //íà÷àëüíûå óñëîâèÿ

        double[][] arr = new double[n][n];
        double[] brr = new double[n];

        double h = (b - a) / n; //øàã
        double[] x = new double[n];
        x[0] = a; x[n - 1] = b; //ïî óñëîâèþ êîíöû èçâåñòíû

        //âû÷èñëÿåì x_i
        for(int i = 1; i < n - 1; i++){
            x[i] = x[0] + i * h;
        }

        //âû÷èñëÿåì ìàòðèöó êîýôôèöèåíòîâ
        arr[0][0] = 0.5 * (1 + findingPi(x[0]) * h / 2); //êîýô.: B_1
        for(int i = 1; i < n; i++){
            arr[i][i - 1] = 0.5 * (1 + findingPi(x[i]) * h / 2); //A
            arr[i][i] = 0.5 * (1 - findingPi(x[i]) * h / 2); //B
            arr[i - 1][i] = 1 + h * h / 2 * findingQi(x[i]); //C
            brr[i] = h * h / 2 * findingRi(x[i]); //F
        }

        //âûâîä ìàòðèöû è ñòîëáöà
        printMatrix(arr, brr);
        System.out.println();

        //----------------------------------------------------------------------

        //ïðÿìîé õîä ìåòîäà ïðîãîíêè
        double[] P = new double[n];
        double[] Q = new double[n];

        P[0] = -arr[0][1] / arr[0][0];
        Q[0] = brr[0] / arr[0][0];

        //for P:
        for(int i = 1; i < n - 1; i++){
            P[i] = arr[i][i + 1] / (arr[i][i] + arr[i][i - 1] * P[i - 1]);
        }
        P[n - 1] = 0;

        //for Q:
        for(int i = 1; i < n; i++){
            Q[i] = (brr[i] - arr[i][i - 1] * Q[i - 1])
                    / (arr[i][i] + arr[i][i - 1] * P[i - 1]);
        }

        //finding X:
        double[] res = new double[n];
        res[n - 1] = Q[n - 1];
        for(int i = n - 2; i >= 0; i--){
            res[i] = P[i] * res[i + 1] + Q[i];
        }

        //âûâîä îòâåòà
        for(int i = 0; i < res.length; i++){
            System.out.print("x[");
            System.out.print(i + 1);
            System.out.print("] = ");
            System.out.print(res[i]);
            System.out.println();
        }
    }

    //ìåòîä äëÿ âûâîäà ìàòðèöû â System.out
    static void printMatrix(double a[][], double b[]){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println(b[i]);
        }
    }

    //âîçâðàùàåò p_i = p(x_i)
    public static double findingPi(double x){
        return -(x * x * x + 1);
    }

    //âîçâðàùàåò q_i = q(x_i)
    public static double findingQi(double x){
        return x * x - 1;
    }

    //âîçâðàùàåò r_i = r(x_i)
    public static double findingRi(double x){
        return -Math.exp(1 - 2.5 * x * x);
    }

}

