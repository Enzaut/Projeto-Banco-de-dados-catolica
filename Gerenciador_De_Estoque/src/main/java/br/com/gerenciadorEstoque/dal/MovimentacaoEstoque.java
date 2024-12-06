import java.sql.*;
import javax.swing.*;

public class MovimentacaoEstoque {

    private Connection conexao;

    public MovimentacaoEstoque(Connection conexao) {
        this.conexao = conexao;
    }

    public void registrarMovimentacao(int produtoId, String tipo, int quantidade) {
        String sql = "INSERT INTO Movimentacao (produto_id, tipo, quantidade) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);
            stmt.setString(2, tipo);
            stmt.setInt(3, quantidade);
            stmt.executeUpdate();

            // Com esse trigger no banco de dados, a quantidade será atualizada automaticamente.
            // Porém, você pode querer verificar erros no processo (como estoque insuficiente ou estoque abaixo do mínimo)
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação: " + e.getMessage());
        }
    }
}
