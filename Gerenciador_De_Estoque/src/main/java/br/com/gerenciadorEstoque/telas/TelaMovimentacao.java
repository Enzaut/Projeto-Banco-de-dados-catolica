package br.com.gerenciadorEstoque.telas;

import java.sql.*;
import javax.swing.JOptionPane;

public class TelaMovimentacao extends javax.swing.JInternalFrame {

    // Variáveis de conexão
    private final Connection conexao;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // Construtor que recebe a conexão do banco
    public TelaMovimentacao(Connection conexao) {
        initComponents();
        this.conexao = conexao;
    }

   private void cadastrar() {
    String sqlMovimentacao = "INSERT INTO movimentacao (tipo, quantidade, produto_id, data_movimentacao) VALUES (?, ?, ?, ?)";
    String sqlAtualizarEstoque = "UPDATE Produto SET quantidade = quantidade + ? WHERE id_produto = ?";

    try {
        // Prepara a conexão para registrar a movimentação
        pst = conexao.prepareStatement(sqlMovimentacao);

        // Obter os valores dos campos
        String tipo = combTipo.getSelectedItem().toString(); // Tipo da movimentação (Entrada/Saída)
        int quantidade = Integer.parseInt(txtQuantidade.getText()); // Quantidade
        int produtoId = Integer.parseInt(txtProdutoId.getText()); // ID do produto
        java.sql.Timestamp dataMovimentacao = new java.sql.Timestamp(System.currentTimeMillis());

        // Preencher os parâmetros da movimentação
        pst.setString(1, tipo);
        pst.setInt(2, quantidade);
        pst.setInt(3, produtoId);
        pst.setTimestamp(4, dataMovimentacao);

        // Executa a consulta de inserção
        int adicionado = pst.executeUpdate();

        if (adicionado > 0) {
            // Atualiza o estoque do produto
            int ajusteEstoque = tipo.equals("Entrada") ? quantidade : -quantidade;

            pst = conexao.prepareStatement(sqlAtualizarEstoque);
            pst.setInt(1, ajusteEstoque);
            pst.setInt(2, produtoId);

            int atualizado = pst.executeUpdate();

            if (atualizado > 0) {
                JOptionPane.showMessageDialog(null, "Movimentação registrada e estoque atualizado com sucesso!");
                
                // Consultar alertas gerados pelo trigger
                consultarAlertas(produtoId);

                // Limpar campos após cadastro
                
                txtQuantidade.setText(null);
                txtProdutoId.setText(null);
                txtRegistroMovimentacao.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar o estoque.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação: " + e.getMessage());
    }
}


    private void consultarAlertas(int produtoId) {
    String sql = "SELECT mensagem FROM AlertaEstoque WHERE produto_id = ?";

    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, produtoId);
        rs = pst.executeQuery();

        // Exibe todos os alertas registrados
        while (rs.next()) {
            String mensagem = rs.getString("mensagem");
            JOptionPane.showMessageDialog(null, mensagem, "Alerta de Estoque", JOptionPane.WARNING_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar alertas: " + e.getMessage());
    }
}


    // Método para consultar o histórico de movimentações
    private void consultarHistorico() {
        String sql = """
                     SELECT m.tipo, m.id_movimentacao, m.quantidade, p.id_produto, m.data_movimentacao 
                     FROM Movimentacao m 
                     INNER JOIN Produto p ON m.produto_id = p.id_produto
                     """;

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            StringBuilder historico = new StringBuilder();
            while (rs.next()) {
                historico.append("Tipo: ").append(rs.getString("tipo"))
                        .append(", ID Movimentação: ").append(rs.getInt("id_movimentacao"))
                        .append(", Quantidade: ").append(rs.getInt("quantidade"))
                        .append(", Produto ID: ").append(rs.getInt("id_produto"))
                        .append(", Data: ").append(rs.getTimestamp("data_movimentacao"))
                        .append("\n\n");
            }
            txtRegistroMovimentacao.setText(historico.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar histórico: " + e.getMessage());
        }
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        enviar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        txtProdutoId = new javax.swing.JTextField();
        combTipo = new javax.swing.JComboBox<>();
        btnHistorico = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRegistroMovimentacao = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Movimentação");
        setPreferredSize(new java.awt.Dimension(640, 540));

        jLabel2.setText("Tipo");

        jLabel3.setText("Quantidade");

        jLabel4.setText("Produto id");

        enviar.setText("enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        jLabel5.setText("Registro movimentação");

        combTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Saida" }));
        combTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combTipoActionPerformed(evt);
            }
        });

        btnHistorico.setText("Historico");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        txtRegistroMovimentacao.setColumns(20);
        txtRegistroMovimentacao.setRows(5);
        jScrollPane1.setViewportView(txtRegistroMovimentacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProdutoId)
                            .addComponent(txtQuantidade)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 252, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(52, 52, 52)
                        .addComponent(btnHistorico)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProdutoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(combTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        setBounds(0, 0, 640, 539);
    }// </editor-fold>//GEN-END:initComponents

    private void combTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combTipoActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        cadastrar();
    }//GEN-LAST:event_enviarActionPerformed

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        consultarHistorico();
    }//GEN-LAST:event_btnHistoricoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistorico;
    private javax.swing.JComboBox<String> combTipo;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtProdutoId;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextArea txtRegistroMovimentacao;
    // End of variables declaration//GEN-END:variables
}
