package com.example.approximation;

import java.sql.*;
import java.util.ArrayList;


public class DataBase {
    private static String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "root";

    private static Connection createConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    public static ArrayList<LineSetDAO> getLineSets() throws Exception {
        try (Connection connection = createConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT line_set.xcord, line_set.ycord, line_set.npoints FROM mydb.line_set"
            );
            ArrayList<LineSetDAO> shapesDAO = new ArrayList<>();
            while (resultSet.next()) {
                shapesDAO.add(new LineSetDAO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
            return shapesDAO;
        } catch (SQLException e) {
            throw new SQLException();
        } catch (Exception e) {
            throw new Exception();
        }
    }
}