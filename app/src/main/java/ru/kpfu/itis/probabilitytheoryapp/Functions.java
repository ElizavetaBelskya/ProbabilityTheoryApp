package ru.kpfu.itis.probabilitytheoryapp;

import java.math.BigInteger;
import java.util.List;

public class Functions {

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger combinationsWithoutRepetitions(int n, int m) {
        return factorial(n).divide(factorial(m).multiply(factorial(n-m)));
    }

    public static BigInteger combinationsWithRepetitions(int n, int m) {
        return factorial(n+m-1).divide(factorial(m).multiply(factorial(n-1)));
    }

    public static BigInteger permutationsWithoutRepetitions(int n) {
        return factorial(n);
    }

    public static BigInteger placementWithoutRepetitions(int n, int k) {
        return factorial(n).divide(factorial(n-k));
    }

    public static BigInteger placementWithRepetitions(int n, int k) {
        return BigInteger.valueOf(n).pow(k);
    }

    public static BigInteger permutationsWithRepetitions(List<Integer> list) {
        int sum = 0;
        BigInteger factPr = BigInteger.valueOf(1);
        for (Integer i: list) {
            sum += i;
            factPr = factPr.multiply(factorial(i));
        }

        BigInteger factSun = factorial(sum);
        return factSun.divide(factPr);
    }


}
