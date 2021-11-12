package com.example.approximation;

import org.javatuples.Pair;

import java.util.ArrayList;

public class LinearApproximation {
    public static LineSet linearApproximation(LineSet lineSet) {
        var points = lineSet.getPoints();
        var coefficeint = getСoefficient(points);
        var a = coefficeint.getValue0();
        var b = coefficeint.getValue1();
        LineSet line = new LineSet();
        for (int i = 0; i < points.size(); i++) {
            var x = points.get(i).getValue0();
            line.addPoint(new Pair<>(x, a * x + b));
        }
        return line;
    }

    private static Pair<Double, Double> getСoefficient(ArrayList<Pair<Double, Double>> points) {
        var amountOfPoints = points.size();

        double sumX = 0;
        double sumY = 0;
        double sumX2 = 0;
        double sumXY = 0;

        for (int i = 0; i < amountOfPoints; i++) {
            var currentPoint = points.get(i);
            var x = currentPoint.getValue0();
            var y = currentPoint.getValue1();
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumXY += x * y;
        }

        var a = (amountOfPoints * sumXY - (sumX * sumY)) / (amountOfPoints*sumX2 - sumX * sumX);
        var b = (sumY - a * sumX) / amountOfPoints;

        return new Pair<>(a, b);
    }
}
