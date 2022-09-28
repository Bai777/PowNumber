package org.example.pow;

import java.util.Scanner;

public class PowNumber {
    public static int operationNumber(int x, int n) {
        return n == 0 ? 1 : operationNumber(x, n - 1) * x;
    }

    public static void main(String[] args) {
        Scanner myNumber = new Scanner(System.in);
        System.out.print("Input number x = ");
        int x = myNumber.nextInt();
        System.out.print("Input pow n = ");
        int n = myNumber.nextInt();
        int pow = operationNumber(x, n);
        System.out.println("Pow( " + x + ", " + n + ") = " + pow);
    }
}
