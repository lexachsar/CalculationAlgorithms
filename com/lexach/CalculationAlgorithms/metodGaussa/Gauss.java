package com.lexach.CalculationAlgorithms.metodGaussa;

class Gauss{
    public static void main(String args[]){
        int n = 4;
        Sist MySist = new Sist(n);
        MySist.print();

        MySist.gauss();

        MySist.print();

    }
}
