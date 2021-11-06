package com.example.approximation;

import org.javatuples.Pair;

import java.util.ArrayList;

public class LagrangeInterpolation {
    public static ArrayList<Pair<Double, Double>> lagrangeInterpolation(ArrayList<Pair<Double, Double>> points) {

        ArrayList<Pair<Double, Double>> line = new ArrayList<>();
        for (double x = points.get(0).getValue0(); x <= points.get(points.size()-1).getValue0(); x = x + 0.5) {
            line.add(new Pair<>(x, calc(points, x)));
        }
        return line;
    }

    private static Double calc(ArrayList<Pair<Double, Double>> points, Double x) {
        double sum = 0;
        for (int i = 0; i < points.size(); ++i){
            var currentPoint = points.get(i);
            double l = 1;
            for (int j = 0; j < points.size(); ++j) {
                var innerCurrentPoint = points.get(j);
                if (j != i)
                    l *= (x - innerCurrentPoint.getValue0()) / (currentPoint.getValue0() - innerCurrentPoint.getValue0());
            }
            sum += currentPoint.getValue1() * l;
        }
        return sum;
    }
}
