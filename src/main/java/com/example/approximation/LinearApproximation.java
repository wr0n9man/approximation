package com.example.approximation;

import org.javatuples.Pair;

import java.util.ArrayList;

public class LinearApproximation {
    public static ArrayList<Pair<Double, Double>> linearApproximation(ArrayList<Pair<Double, Double>> points) {
        var coefficeint = getСoefficient(points);
        ArrayList<Pair<Double, Double>> line = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            line.add(new Pair<>(points.get(i).getValue0(), coefficeint.getValue0() * points.get(i).getValue0() + coefficeint.getValue1()));
        }
        return line;
    }

    private static Pair<Double, Double> getСoefficient(ArrayList<Pair<Double, Double>> points) {
        double sumX = 0;
        double sumY = 0;
        double sumX2 = 0;
        double sumXY = 0;

        for (int i = 0; i < points.size(); i++) {
            var x = points.get(i).getValue0();
            var y = points.get(i).getValue1();
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumXY += x * y;
        }

        var a = (points.size() * sumXY - (sumX * sumY)) / (points.size()*sumX2 - sumX * sumX);
        var b = (sumY - a * sumX) / points.size();

        return new Pair<>(a, b);
    }
}
