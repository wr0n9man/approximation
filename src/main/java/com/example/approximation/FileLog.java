package com.example.approximation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLog implements ILogger{
    File logFile;
    Path path = Paths.get("./logs");

    public FileLog() {
        createFile();
    }

    @Override
    public void log(String... message) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            for (var m : message)
                writer.append(m+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFile() {
        var currentDate = java.time.LocalDate.now();
        var currentTime = java.time.LocalTime.now();
        String fileName = String.format(
                "log-%d-%d-%d-%d-%d-%d.txt",
                currentDate.getDayOfMonth(),
                currentDate.getMonthValue(),
                currentDate.getYear(),
                currentTime.getHour(),
                currentTime.getMinute(),
                currentTime.getSecond()
        );

        if (!Files.exists(path))
        {
            try {
                path = Files.createDirectory(Path.of("./logs"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logFile = new File(path.toString(), fileName);

        try {
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
