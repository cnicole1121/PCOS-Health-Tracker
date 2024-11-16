package com.example.pcoshealthtracker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

@WebServlet(name = "DatabaseConnectionServlet", urlPatterns = "/connectToDatabase")
public class DatabaseConnectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String jdbcUrl = "jdbc:mysql://localhost:3306/pcos_health_tracker";
        String username = "root";
        String password = "563324";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get a database connection using DriverManager
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

                if (connection != null && !connection.isClosed()) {
                    out.println("Connected to the database successfully!<br>");

                    Statement statement = connection.createStatement();

                    String query = "SELECT * FROM users"; 
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        int userId = resultSet.getInt("user_id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String email = resultSet.getString("email");

                        out.println("User ID: " + userId + ", Name: " + firstName + " " + lastName + ", email: " + email + "<br>");
                    }

                    resultSet.close();
                    statement.close();
                }
            }

        } catch (ClassNotFoundException e) {
            out.println("MySQL JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
