package com.example.approximation;

import org.javatuples.Pair;

import java.util.ArrayList;

public class LineSet {
    ArrayList<Pair<Double, Double>> lineSet;

    public LineSet() {
        lineSet = new ArrayList<>();
    }

    public void addPoint(Pair<Double, Double> point) {
        lineSet.add(point);
    }

    public ArrayList<Pair<Double, Double>> getPoints() {
        return lineSet;
    }

    public int getSize() {
        return lineSet.size();
    }

    @Override
    public String toString() {
        StringBuilder lineSetInfo = new StringBuilder();
        lineSetInfo.append(String.format("LineSet has %d points:", lineSet.size()));
        for (int i = 0; i < lineSet.size(); i++) {
            var pair = lineSet.get(i);
            lineSetInfo.append(String.format(
                    "x[%d]=%f; y[%d]=%f\n",
                    i, pair.getValue0(), i, pair.getValue1()
            ));
        }
        return lineSetInfo.toString();
    }
}
