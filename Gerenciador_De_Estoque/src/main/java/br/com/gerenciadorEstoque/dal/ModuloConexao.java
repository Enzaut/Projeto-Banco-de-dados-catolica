package br.com.gerenciadorEstoque.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ModuloConexao {

    private static final String URL_PADRAO = "jdbc:mysql://localhost:3306/dever_db";
    private static final String USUARIO_PADRAO = "root";
    private static final String SENHA_PADRAO = "senha";

    public static Connection criarConexao() throws SQLException {
        return criarConexao(URL_PADRAO, USUARIO_PADRAO, SENHA_PADRAO);
    }

    public static Connection criarConexao(String url, String usuario, String senha) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }
}
