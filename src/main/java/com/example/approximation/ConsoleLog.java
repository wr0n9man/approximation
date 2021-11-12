package com.example.approximation;

public class ConsoleLog implements ILogger {
    public void log(String... message) {
        for (var m : message)
            System.out.println(m);
    }
}
