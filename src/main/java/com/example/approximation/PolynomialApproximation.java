package com.example.approximation;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.function.Function;

public class PolynomialApproximation {
    public static LineSet polynomialApproximation(LineSet lineSet, int polyDegree) {
        var points = lineSet.getPoints();
        LineSet line = new LineSet();
        allocmatrix(points);
        var func = Calculate(points.size(), polyDegree);
        var xLast = points.get(points.size() - 1).getValue0();
        for (double x = points.get(0).getValue0(); x <= xLast; x = x + 0.5) {
            line.addPoint(new Pair<>(x, func.apply(x)));
        }
        return line;
    }

    private static double[] a;
    private static double[] b;
    private static double[] x;
    private static double[] y;
    private static double[][] sums;

    private static void refresh(int K) {
        for (int i = 0; i < K; i++) {
            a[i] = b[i] = 0;
            for (int j = 0; j < K; j++)
                sums[i][j] = 0;
        }
    }

    private static void allocmatrix(ArrayList<Pair<Double, Double>> points) {
        int N = points.size();
        a = new double[N];
        b = new double[N];
        x = new double[N];
        y = new double[N];
        sums = new double[N][N];

        for (int i = 0; i < N; i++) {
            var currentPoint = points.get(i);
            x[i] = currentPoint.getValue0();
            y[i] = currentPoint.getValue1();
        }
    }

    private static Function<Double, Double> Calculate(int N, int K) {
        int i, j, k;
        double s, t, M;
        refresh(K);
        //упорядочиваем узловые точки по возрастанию абсцисс
        for (i = 0; i < N; i++) {
            for (j = i; j >= 1; j--)
                if (x[j] < x[j - 1]) {
                    t = x[j - 1];
                    x[j - 1] = x[j];
                    x[j] = t;
                    t = y[j - 1];
                    y[j - 1] = y[j];
                    y[j] = t;
                }
        }
        //заполняем коэффициенты системы уравнений
        for (i = 0; i < K + 1; i++) {
            for (j = 0; j < K + 1; j++) {
                sums[i][j] = 0;
                for (k = 0; k < N; k++)
                    sums[i][j] += Math.pow(x[k], i + j);
            }
        }
        //заполняем столбец свободных членов
        for (i = 0; i < K + 1; i++) {
            b[i] = 0;
            for (k = 0; k < N; k++)
                b[i] += Math.pow(x[k], i) * y[k];
        }
        //применяем метод Гаусса для приведения матрицы системы к треугольному виду
        for (k = 0; k < K + 1; k++) {
            for (i = k + 1; i < K + 1; i++) {
                M = sums[i][k] / sums[k][k];
                for (j = k; j < K + 1; j++)
                    sums[i][j] -= M * sums[k][j];
                b[i] -= M * b[k];
            }
        }
        //вычисляем коэффициенты аппроксимирующего полинома
        for (i = K; i >= 0; i--) {
            s = 0;
            for (j = i; j < K + 1; j++)
                s += sums[i][j] * a[j];
            a[i] = (b[i] - s) / sums[i][i];
        }

        return aDouble -> {
            double result = a[0];
            for (int i1 = 1; i1 < a.length; i1++) {
                result += a[i1] * Math.pow(aDouble, i1);
            }
            return result;
        };
    }
}
