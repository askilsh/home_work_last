package db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbService {
    private static ArrayList<String[]> rez;
    private static boolean rezCount;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Statement getStatement() {
        return statement;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static boolean executeUpdate(final String sql) {
        Connection connection = DbClient.getConnection();
        statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            rezCount = true;
        } catch (SQLException e) {
            rezCount = false;
        }
        return rezCount;
    }

    public static ArrayList<String[]> executeQueryGetTable(final String sql) {
        statement = null;
        try {
            Connection connection = DbClient.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<String[]> value = new ArrayList<>();
            rez = value;
            int column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] str = new String[column];
                for (int i = 0; i < column; i++) {
                    str[i] = resultSet.getString(i + 1);
                }
                value.add(str);
            }
            rez = value;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public static String executeQueryGetColumn(final String column, final String sql) {
        statement = null;
        String value = " ";
        try {
            Connection connection = DbClient.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                value = resultSet.getString(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static <T> void printTable(final ArrayList<T[]> table) {
        for (T[] mass : table) {
            for (T str : mass) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
