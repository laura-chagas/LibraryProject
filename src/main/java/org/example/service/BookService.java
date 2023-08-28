package org.example.service;

import java.sql.*;

import static org.example.connection.ConnectionDB.connect;

public class BookService {

    private Connection conn = connect();

    public void register(int code, String nameBook, String nameAuthor, String date) {
        String SQL = "INSERT INTO books (code, namebook, nameauthor, date) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, code);
            preparedStatement.setString(2, nameBook);
            preparedStatement.setString(3, nameAuthor);
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();
            System.out.println("Livro registrado!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeInfo(int code, String nameAuthor) {
        String SQL = "UPDATE books SET nameauthor = ? WHERE code = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, nameAuthor);

            preparedStatement.setInt(2, code);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Livro alterado com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o código fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int code) {

        String SQL = "DELETE FROM books WHERE code = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, code);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o código fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void searchBookByCode(int code) {

        String SQL = "SELECT * FROM books WHERE code = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, code);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                System.out.println("CODIGO: " + rs.getInt("code"));
                System.out.println("NOME DO LIVRO: " + rs.getString("namebook"));
                System.out.println("NOME DO AUTOR: " + rs.getString("nameauthor"));
                System.out.println("DATA DE LANÇAMENTO: " + rs.getString("date"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllBooks() {

        String SQL = "SELECT * FROM books";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                System.out.println("CÓDIGO: " + rs.getInt("code"));
                System.out.println("NOME DO LIVRO: " + rs.getString("namebook"));
                System.out.println("NOME DO AUTOR: " + rs.getString("nameauthor"));
                System.out.println("DATA DE LANÇAMENTO: " + rs.getString("date"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
