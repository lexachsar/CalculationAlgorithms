package com.lexach.CalculationAlgorithms.GaussianElimination;

class Gauss {
    private static void oneAtMainDiagonaleMaker(int line, Sist system) {
        double[][] a = system.getA();
        double[] b = system.getB();
        int n = system.getN();

        for (int k = 0; k < n; k++) {
            a[line][k] /= a[line][line];
        }
        b[line] /= a[line][line];
    }

    /**
     * @param line
     * @param system system you want to operate with
     */
    private static void zerosUnderMainDiagonaleMaker(int line, Sist system) {
        double[][] a = system.getA();
        double[] b = system.getB();
        int n = system.getN();

        for (int i = line + 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] += a[line][j] * (-a[i][line] / a[line][line]);
                b[i] += b[line] * (-a[i][line] / b[line]);
            }
        }
    }


    private static void gaussPryamoy(Sist system) {
        int n = system.getN();

        for (int j = 0; j < n; j++) {
            oneAtMainDiagonaleMaker(j, system);

            zerosUnderMainDiagonaleMaker(j, system);
        }
    }

    private static double[] gaussObratniy(Sist system) {
        double[][] a = system.getA();
        double[] b = system.getB();
        int n = system.getN();

        double[] result = new double[n];
        double[] bvar = b.clone();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                bvar[i] = bvar[i] - a[i][j] * result[j];
            }
            result[i] = (bvar[i]);
        }
        return result;
    }

    private static double[] masReverse(double[] mas){
        for(int i = 0; i < mas.length / 2; i++) {
            double temp = mas[i];
            mas[i] = mas[mas.length - i - 1];
            mas[mas.length - i - 1] = temp;
        }

        return mas;
    }

    private static double[] gauss(Sist system) {
        gaussPryamoy(system);

        double[] x = gaussObratniy(system);

        return masReverse(x);
    }

    private static void masPrint(double[] mas) {
        for (double ma : mas)
            System.out.print(ma + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int n = 4;
        Sist MySist = new Sist(n);
        MySist.print();

        double[] x = gauss(MySist);

        MySist.print();

        masPrint(x);
    }
}
