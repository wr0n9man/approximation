package com.example.approximation;

import org.javatuples.Pair;

import java.util.Arrays;

public class LineSetDAO {
    String xcord;
    String ycord;
    int npoints;

    public LineSetDAO(String xcord, String ycord, int npoints) {
        this.xcord = xcord;
        this.ycord = ycord;
        this.npoints = npoints;
    }

    private double[] parse(String cordLine) {
        double[] cords = new double[npoints];

        var a = Arrays
                .stream(cordLine.split(", "))
                .map(Double::valueOf)
                .toArray(Double[]::new);

        for (int i = 0; i < npoints; i++)
            cords[i] = a[i].doubleValue();
        return cords;
    }

    public LineSet getLineSet() {
        LineSet lineSet = new LineSet();
        var x = parse(xcord);
        var y = parse(ycord);
        for (int i = 0; i < npoints; i++) {
            lineSet.addPoint(new Pair<>(x[i], y[i]));
        }
        return lineSet;
    }
}
