package org.example;
import java.sql.*;

public class WatchStore {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/watch_store";
        String username = "root";
        String password = "14325";

        try {
            // Установка соединения с базой данных
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Вывести марки заданного типа часов
            String type = "кварцевые";
            String query1 = "SELECT DISTINCT марка FROM час WHERE тип = '" + type + "'";
            ResultSet resultSet1 = statement.executeQuery(query1);

            System.out.println("Марки " + type + " часов:");
            while (resultSet1.next()) {
                String марка = resultSet1.getString("марка");
                System.out.println(марка);
            }
            resultSet1.close();

            System.out.println();

            // Вывести информацию о механических часах, цена на которые не превышает заданную
            int maxPrice = 1000;
            String query2 = "SELECT * FROM час WHERE тип = 'механические' AND цена <= " + maxPrice;
            ResultSet resultSet2 = statement.executeQuery(query2);

            System.out.println("Информация о механических часах с ценой не более " + maxPrice + ":");
            while (resultSet2.next()) {
                String марка = resultSet2.getString("марка");
                String тип = resultSet2.getString("тип");
                int цена = resultSet2.getInt("цена");
                int количество = resultSet2.getInt("количество");
                String реквизитыПроизводителя = resultSet2.getString("реквизиты_производителя");

                System.out.println("Марка: " + марка);
                System.out.println("Тип: " + тип);
                System.out.println("Цена: " + цена);
                System.out.println("Количество: " + количество);
                System.out.println("Реквизиты производителя: " + реквизитыПроизводителя);
                System.out.println();
            }
            resultSet2.close();

            // Вывести марки часов, изготовленных в заданной стране
            String country = "США";
            String query3 = "SELECT DISTINCT марка FROM час JOIN производитель ON час.реквизиты_производителя = производитель.название WHERE производитель.страна = '" + country + "'";
            ResultSet resultSet3 = statement.executeQuery(query3);

            System.out.println("Марки часов, изготовленных в стране " + country + ":");
            while (resultSet3.next()) {
                String марка = resultSet3.getString("марка");
                System.out.println(марка);
            }
            resultSet3.close();

            System.out.println();

            // Вывести производителей, общая сумма часов которых в магазине не превышает заданную
            int maxTotalPrice = 5000;
            String query4 = "SELECT производитель.название, SUM(час.цена * час.количество) AS общая_сумма FROM час JOIN производитель ON час.реквизиты_производителя = производитель.название GROUP BY производитель.название HAVING общая_сумма <= " + maxTotalPrice;
            ResultSet resultSet4 = statement.executeQuery(query4);

            System.out.println("Производители, общая сумма часов которых не превышает " + maxTotalPrice + ":");
            while (resultSet4.next()) {
                String название = resultSet4.getString("название");
                int общаяСумма = resultSet4.getInt("общая_сумма");
                System.out.println("Название производителя: " + название);
                System.out.println("Общая сумма часов: " + общаяСумма);
                System.out.println();
            }
            resultSet4.close();

            // Закрытие соединения с базой данных
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}