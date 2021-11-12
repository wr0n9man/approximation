package com.example.approximation;

import org.javatuples.Pair;

import java.util.ArrayList;

public class LagrangeInterpolation {
    public static LineSet lagrangeInterpolation(LineSet lineSet) {
        var points = lineSet.getPoints();
        LineSet line = new LineSet();
        for (double x = points.get(0).getValue0(); x <= points.get(points.size()-1).getValue0(); x = x + 0.5) {
            line.addPoint(new Pair<>(x, calc(points, x)));
        }
        return line;
    }

    private static Double calc(ArrayList<Pair<Double, Double>> points, Double x) {
        double sum = 0;
        for (int i = 0; i < points.size(); ++i){
            var currentPoint = points.get(i);
            double l = 1;
            for (int j = 0; j < points.size(); ++j) {
                var innerCurrentPoint_X = points.get(j).getValue0();
                if (j != i)
                    l *= (x - innerCurrentPoint_X) / (currentPoint.getValue0() - innerCurrentPoint_X);
            }
            sum += currentPoint.getValue1() * l;
        }
        return sum;
    }
}
