package br.com.gerenciadorEstoque.main;

import java.sql.Connection;
import java.sql.SQLException;
import br.com.gerenciadorEstoque.dal.ModuloConexao;
import br.com.gerenciadorEstoque.telas.TelaPrincipal;

public class Gerenciador_De_Estoque {
    public static void main(String[] args) {
        try {
            Connection conexao = ModuloConexao.criarConexao(); // Cria a conexão com o banco
            TelaPrincipal tela = new TelaPrincipal(conexao); // Passa a conexão para o construtor
            tela.setVisible(true); // Exibe a tela
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco. Verifique os detalhes.");
            System.exit(1); // Encerra a aplicação caso a conexão falhe
        }
    }
}
