
package br.com.gerenciadorEstoque.telas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TelaRelatorio extends javax.swing.JInternalFrame {

   
    
    
    Connection conexao = null;
    
    PreparedStatement pst = null;
    
    ResultSet rs = null;
    
    public TelaRelatorio(Connection conexao) {
        initComponents();
        this.conexao = conexao;
    }
    

   private void mostrarCadastrados() {
    // SQL para consultar todos os produtos e movimentações
    String sql = "SELECT nome, quantidade, Valor_compra, Valor_venda, categoria_id from produto";
               

    try {
        // Estabelece a conexão
        pst = conexao.prepareStatement(sql);

        // Executa a consulta
        rs = pst.executeQuery();

        // Modelo da tabela para exibir os resultados
        DefaultTableModel model = (DefaultTableModel) tblCadastrado.getModel();
        model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados

        // Preenche a tabela com os dados retornados da consulta
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nome"),
                rs.getInt("quantidade"),
                rs.getDouble("Valor_compra"),
                rs.getDouble("Valor_venda"),
                rs.getString("categoria_id")
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + ex.getMessage());
    }   
}
   
    private void mostrarBaixoEstoque() {
    // SQL para consultar todos os produtos e movimentações
    String sql = "SELECT nome, quantidade, Valor_compra, Valor_venda, categoria_id from produto where quantidade < 10";
               

    try {
        // Estabelece a conexão
        pst = conexao.prepareStatement(sql);

        // Executa a consulta
        rs = pst.executeQuery();

        // Modelo da tabela para exibir os resultados
        DefaultTableModel model = (DefaultTableModel) tblCadastrado.getModel();
        model.setRowCount(0);  // Limpa a tabela antes de adicionar novos dados

        // Preenche a tabela com os dados retornados da consulta
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nome"),
                rs.getInt("quantidade"),
                rs.getDouble("Valor_compra"),
                rs.getDouble("Valor_venda"),
                rs.getString("categoria_id")
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + ex.getMessage());
    
    }
}

private void CalcularLucro() {
    // SQL corrigido para calcular o lucro considerando a quantidade de produtos
    String sql = "SELECT nome, valor_Compra, valor_Venda, "
               + "(valor_Venda * quantidade - valor_Compra * quantidade) AS lucro "
               + "FROM produto";

    try {
        // Executando a consulta
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery(); // Executa a consulta para buscar os dados

        // Modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tablLucro.getModel();
        model.setRowCount(0); // Limpa os dados da tabela antes de adicionar novos

        // Preenche a tabela com os dados retornados da consulta
        while (rs.next()) {
            // Adiciona uma nova linha na tabela para cada produto
            model.addRow(new Object[] {
                rs.getString("nome"),  // Nome do produto
                rs.getDouble("valor_Venda"),  // Valor de venda
                rs.getDouble("valor_Compra"), // Valor de compra
                rs.getDouble("lucro") // Lucro calculado
            });
        }
    } catch (SQLException ex) {
        // Exibe o erro caso ocorra
        JOptionPane.showMessageDialog(null, "Erro ao calcular o lucro: " + ex.getMessage());
    }
}

    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCadastrado = new javax.swing.JTable();
        btnCadastrado = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnVendaLucro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablLucro = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Relatorio");

        jLabel3.setText("Relatorio de vendas e lucro");

        tblCadastrado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Quantidade", "Compra", "Venda", "Categoria"
            }
        ));
        jScrollPane1.setViewportView(tblCadastrado);

        btnCadastrado.setText("Produto Cadastrado");
        btnCadastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastradoActionPerformed(evt);
            }
        });

        jButton1.setText("Produto com Baixo Estoque");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnVendaLucro.setText("Venda e Lucro");
        btnVendaLucro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendaLucroActionPerformed(evt);
            }
        });

        tablLucro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Venda", "Compra", "Lucro"
            }
        ));
        jScrollPane2.setViewportView(tablLucro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrado)
                        .addGap(35, 35, 35)
                        .addComponent(jButton1))
                    .addComponent(jLabel3)
                    .addComponent(btnVendaLucro)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrado)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnVendaLucro)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 640, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastradoActionPerformed
        mostrarCadastrados();
    }//GEN-LAST:event_btnCadastradoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarBaixoEstoque();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVendaLucroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendaLucroActionPerformed
        CalcularLucro();
    }//GEN-LAST:event_btnVendaLucroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrado;
    private javax.swing.JButton btnVendaLucro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablLucro;
    private javax.swing.JTable tblCadastrado;
    // End of variables declaration//GEN-END:variables
}
