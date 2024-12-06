package br.com.gerenciadorEstoque.telas;

import java.sql.*;
import br.com.gerenciadorEstoque.dal.ModuloConexao;
import javax.swing.JOptionPane;


public class TelaCategoria extends javax.swing.JInternalFrame {
    
    
    //usando variavel conexao do dal
    Connection conexao = null;
    
    PreparedStatement pst = null;
    
    ResultSet rs = null;
    
    
    
    
    //construtor usado para inicializar o modulo de conexao
    public TelaCategoria(Connection conexao) {
        initComponents();
        this.conexao = conexao;
    }
    
   private void consultar(){
       String sql = "select * from categoria where id_Categoria=?";
       
       try{
           pst = conexao.prepareStatement(sql);
           
           pst.setString(1,txtIdCategoria.getText());
           
           rs = pst.executeQuery();
           
           if(rs.next()){
               
               txtNomeCategoria.setText(rs.getString(2));
               
               txtDescricaoCategoria.setText(rs.getString(3));
               
           }else{
              JOptionPane.showMessageDialog(null,"não há nada nessa categoria,crie algo.");
              
              //usamos essas linhas abaixo a limpar os campos 
              
              txtNomeCategoria.setText(null);
              
              txtDescricaoCategoria.setText(null);
                                                       
           }
                             
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
             
   }
   
private void cadastrar() {
    String sql = "{call cadastrarCategoria(?, ?)}";  // Chamada para a procedure no banco

    try {
        // Prepara a conexão para uso com CallableStatement
        pst = conexao.prepareCall(sql);

        // Preenche os parâmetros da procedure com os valores dos campos
        pst.setString(1, txtNomeCategoria.getText());
        pst.setString(2, txtDescricaoCategoria.getText());

        // Executa a procedure no banco de dados
        int adicionado = pst.executeUpdate();

        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");

            // Limpa os campos após cadastro
            txtNomeCategoria.setText(null);
            txtDescricaoCategoria.setText(null);
        } else {
            JOptionPane.showMessageDialog(null, "Não há nada nessa categoria, crie algo.");

            // Limpa os campos após a tentativa de cadastro sem sucesso
            txtNomeCategoria.setText(null);
            txtDescricaoCategoria.setText(null);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}


public void alterar() {
    // SQL para atualizar os dados da categoria
    String sql = "UPDATE categoria SET Nome=?, descricao=? WHERE id_Categoria=?";

    try {
        // Prepara a conexão para uso
        pst = conexao.prepareStatement(sql);

        // Verifica se os campos estão preenchidos
        if (txtNomeCategoria.getText().isEmpty() || txtDescricaoCategoria.getText().isEmpty() || txtIdCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return;  // Sai do método caso algum campo esteja vazio
        }

        // Preenche os parâmetros da consulta com os dados dos campos
        pst.setString(1, txtNomeCategoria.getText());
        pst.setString(2, txtDescricaoCategoria.getText());
        pst.setString(3, txtIdCategoria.getText());

        // Executa a atualização no banco
        int linhasAfetadas = pst.executeUpdate();

        // Verifica se a atualização foi bem-sucedida
        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Dados da categoria alterados com sucesso!");
            // Limpa os campos após a atualização
            txtNomeCategoria.setText(null);
            txtDescricaoCategoria.setText(null);
            txtIdCategoria.setText(null);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados. Verifique o ID da categoria.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

public void excluir() {
    // SQL para atualizar os dados da categoria
    String sql = "delete from categoria where id_Categoria=? ";

    try {
        // Prepara a conexão para uso
        pst = conexao.prepareStatement(sql);

        

        // Preenche os parâmetros da consulta com os dados dos campos
       
        pst.setString(1, txtIdCategoria.getText());

        // Executa a atualização no banco
        int excluir = pst.executeUpdate();

        // Verifica se a atualização foi bem-sucedida
        if (excluir > 0) {
            JOptionPane.showMessageDialog(null, "Dados da categoria excluidos com sucesso!");
            // Limpa os campos após a atualização
            txtNomeCategoria.setText(null);
            txtDescricaoCategoria.setText(null);
            txtIdCategoria.setText(null);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados. Verifique o ID da categoria.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCategoria = new javax.swing.JTextField();
        txtDescricaoCategoria = new javax.swing.JTextField();
        btnCatCreate = new javax.swing.JButton();
        btnCatRemove = new javax.swing.JButton();
        btnCatUpdate = new javax.swing.JButton();
        btnCatPesquisar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtIdCategoria = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Categoria");

        jLabel1.setText("Nome da categoria");

        jLabel2.setText("Descriçâo da categoria");

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

        jLabel3.setText("Id categoria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtDescricaoCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addComponent(txtNomeCategoria))
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnCatCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnCatRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(btnCatUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnCatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescricaoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCatRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
        );

        setBounds(0, 0, 640, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCatCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatCreateActionPerformed
        cadastrar();
    }//GEN-LAST:event_btnCatCreateActionPerformed

    private void btnCatPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatPesquisarActionPerformed
        consultar();
    }//GEN-LAST:event_btnCatPesquisarActionPerformed

    private void btnCatUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnCatUpdateActionPerformed

    private void btnCatRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatRemoveActionPerformed
        excluir();
    }//GEN-LAST:event_btnCatRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCatCreate;
    private javax.swing.JButton btnCatPesquisar;
    private javax.swing.JButton btnCatRemove;
    private javax.swing.JButton btnCatUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtDescricaoCategoria;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNomeCategoria;
    // End of variables declaration//GEN-END:variables
}
