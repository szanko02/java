package org.example;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/watchstore")
public class WatchStoreServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3307/watch_store";
        String username = "root";
        String password = "14325";

        try {
            // Установка соединения с базой данных
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Получение значения параметра типа часов из запроса
            String type = request.getParameter("type");

            // Формирование и выполнение запроса
            String query = "SELECT DISTINCT марка FROM час WHERE тип = '" + type + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Формирование HTML-документа с результатами запроса
            out.println("<html>");
            out.println("<head><title>Результаты запроса</title></head>");
            out.println("<body>");
            out.println("<h2>Марки " + type + " часов:</h2>");
            out.println("<ul>");
            while (resultSet.next()) {
                String марка = resultSet.getString("марка");
                out.println("<li>" + марка + "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");

            resultSet.close();

            // Закрытие соединения с базой данных
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}