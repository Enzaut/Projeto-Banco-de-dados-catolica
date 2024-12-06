package br.com.gerenciadorEstoque.telas;

import br.com.gerenciadorEstoque.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaProduto extends javax.swing.JInternalFrame {

    Connection conexao = null;
    
    PreparedStatement pst = null;
    
    ResultSet rs = null;
    
     public TelaProduto(Connection conexao) {
        initComponents();
        this.conexao = conexao;
    }
    
    private void limparCampos() {
    txtIdProduto.setText(null);
    txtNomeProduto.setText(null);
    txtDescricao.setText(null);
    txtQuantidadeEstoque.setText(null);
    txtPrecoCompra.setText(null);
    txtPrecoVenda.setText(null);
    txtCategoriaProduto.setText(null);
}
private void cadastrar() {
    // Consulta SQL para inserção
    String sql = "call cadastrarProduto(?, ?, ?, ?, ?, ?)";
    try {
        // Prepara a conexão e o comando SQL
        pst = conexao.prepareStatement(sql);

        // Substitui os parâmetros na query
        pst.setString(1, txtNomeProduto.getText());
        pst.setString(2, txtDescricao.getText());
        pst.setInt(3, Integer.parseInt(txtQuantidadeEstoque.getText()));
        pst.setDouble(4, Double.parseDouble(txtPrecoCompra.getText()));
        pst.setDouble(5, Double.parseDouble(txtPrecoVenda.getText()));
        pst.setInt(6, Integer.parseInt(txtCategoriaProduto.getText()));

        // Executa o comando SQL
        int adicionado = pst.executeUpdate();

        // Confirma se foi inserido com sucesso
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            limparCampos(); // Limpa os campos do formulário
        }
    } catch (Exception e) {
        // Exibe mensagem de erro caso algo dê errado
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    }
}


private void consultar() {
    // Consulta SQL para buscar produto pelo ID
    String sql = "SELECT * FROM produto WHERE id_Produto=?";
    try {
        // Prepara a conexão e o comando SQL
        pst = conexao.prepareStatement(sql);

        // Verifica se o campo ID está preenchido
        if (txtIdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ID do produto para consulta!");
            return;
        }

        // Substitui o parâmetro na query
        pst.setInt(1, Integer.parseInt(txtIdProduto.getText()));

        // Executa a consulta
        rs = pst.executeQuery();

        // Verifica se encontrou resultados
        if (rs.next()) {
            txtNomeProduto.setText(rs.getString("nome"));
            txtDescricao.setText(rs.getString("descricao"));
            txtQuantidadeEstoque.setText(String.valueOf(rs.getInt("quantidade")));
            txtPrecoCompra.setText(String.valueOf(rs.getDouble("Valor_compra")));
            txtPrecoVenda.setText(String.valueOf(rs.getDouble("Valor_venda")));
            txtCategoriaProduto.setText(rs.getString("categoria_id"));
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            limparCampos(); // Limpa os campos do formulário
        }
    } catch (Exception e) {
        // Exibe mensagem de erro
        JOptionPane.showMessageDialog(null, "Erro ao consultar produto: " + e.getMessage());
    }
}

private void excluir() {
    // Consulta SQL para exclusão
    String sql = "DELETE FROM produto WHERE id_Produto=?";
    try {
        // Prepara a conexão e o comando SQL
        pst = conexao.prepareStatement(sql);

        // Verifica se o campo ID está preenchido
        if (txtIdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ID do produto para exclusão!");
            return;
        }

        // Substitui o parâmetro na query
        pst.setInt(1, Integer.parseInt(txtIdProduto.getText()));

        // Exibe uma confirmação antes de excluir
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            // Executa o comando SQL
            int deletado = pst.executeUpdate();

            // Verifica se a exclusão foi bem-sucedida
            if (deletado > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                limparCampos(); // Limpa os campos do formulário
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado ou já foi excluído!");
            }
        }
    } catch (Exception e) {
        // Exibe mensagem de erro
        JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage());
    }
}

private void alterar() {
    // Consulta SQL para atualização
    String sql = "UPDATE produto SET Nome = ?, descricao = ?, quantidade = ?, Valor_compra = ?, Valor_venda = ?, categoria_id = ? WHERE id_Produto=?";
    try {
        // Prepara a conexão e o comando SQL
        pst = conexao.prepareStatement(sql);

        // Verifica se o campo ID está preenchido
        if (txtIdProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ID do produto para alteração!");
            return;
        }

        // Substitui os parâmetros na query
        pst.setString(1, txtNomeProduto.getText());
        pst.setString(2, txtDescricao.getText());
        pst.setInt(3, Integer.parseInt(txtQuantidadeEstoque.getText()));
        pst.setDouble(4, Double.parseDouble(txtPrecoCompra.getText()));
        pst.setDouble(5, Double.parseDouble(txtPrecoVenda.getText()));
        pst.setString(6, txtCategoriaProduto.getText());
        pst.setInt(7, Integer.parseInt(txtIdProduto.getText()));

        // Exibe uma confirmação antes de alterar
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar este produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            // Executa o comando SQL
            int alterado = pst.executeUpdate();

            // Verifica se a alteração foi bem-sucedida
            if (alterado > 0) {
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
                limparCampos(); // Limpa os campos do formulário
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            }
        }
    } catch (Exception e) {
        // Exibe mensagem de erro
        JOptionPane.showMessageDialog(null, "Erro ao alterar produto: " + e.getMessage());
    }
}




  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCatCreate = new javax.swing.JButton();
        btnCatRemove = new javax.swing.JButton();
        btnCatUpdate = new javax.swing.JButton();
        btnCatPesquisar = new javax.swing.JButton();
        txtNomeProduto = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtQuantidadeEstoque = new javax.swing.JTextField();
        txtPrecoCompra = new javax.swing.JTextField();
        txtPrecoVenda = new javax.swing.JTextField();
        txtCategoriaProduto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produtos");
        setPreferredSize(new java.awt.Dimension(640, 580));

        jLabel2.setText("Nome produto");

        jLabel3.setText("Descrição");

        jLabel4.setText("Quantidade em estoque");

        jLabel5.setText("Preço de compra");

        jLabel6.setText("Preço de venda");

        jLabel7.setText("Categoria do produto");

        btnCatCreate.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\adicionar.png")); // NOI18N
        btnCatCreate.setToolTipText("Adicionar");
        btnCatCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatCreateActionPerformed(evt);
            }
        });

        btnCatRemove.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\deletar.png")); // NOI18N
        btnCatRemove.setToolTipText("excluir");
        btnCatRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatRemove.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatRemoveActionPerformed(evt);
            }
        });

        btnCatUpdate.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\editar.png")); // NOI18N
        btnCatUpdate.setToolTipText("editar");
        btnCatUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatUpdateActionPerformed(evt);
            }
        });

        btnCatPesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Caian\\OneDrive\\Imagens\\icones\\pesquisar.png")); // NOI18N
        btnCatPesquisar.setToolTipText("pesquisar");
        btnCatPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCatPesquisar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCatPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("Id produto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnCatCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btnCatRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnCatUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnCatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtCategoriaProduto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantidadeEstoque)
                            .addComponent(txtPrecoCompra)
                            .addComponent(txtPrecoVenda)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(53, 53, 53))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(81, 81, 81)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(95, 95, 95)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomeProduto)
                            .addComponent(txtDescricao)
                            .addComponent(txtIdProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCatCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCatUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );

        setBounds(0, 0, 640, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCatCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatCreateActionPerformed
        cadastrar();
    }//GEN-LAST:event_btnCatCreateActionPerformed

    private void btnCatPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatPesquisarActionPerformed
         consultar();
    }//GEN-LAST:event_btnCatPesquisarActionPerformed

    private void btnCatRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatRemoveActionPerformed
       excluir();
    }//GEN-LAST:event_btnCatRemoveActionPerformed

    private void btnCatUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnCatUpdateActionPerformed
    
    
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCatCreate;
    private javax.swing.JButton btnCatPesquisar;
    private javax.swing.JButton btnCatRemove;
    private javax.swing.JButton btnCatUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCategoriaProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPrecoCompra;
    private javax.swing.JTextField txtPrecoVenda;
    private javax.swing.JTextField txtQuantidadeEstoque;
    // End of variables declaration//GEN-END:variables

}