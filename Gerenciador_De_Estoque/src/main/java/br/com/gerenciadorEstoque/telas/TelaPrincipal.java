package br.com.gerenciadorEstoque.telas;

import java.sql.Connection;


public class TelaPrincipal extends javax.swing.JFrame {

  
   private Connection conexao;
    
    
    TelaProduto telaProduto;
    TelaRelatorio telaRelatorio;
    TelaCategoria telaCategoria;
    TelaMovimentacao telaMovimentacao;
    
public TelaPrincipal(Connection conexao) {
    initComponents();
    // Agora você pode usar a conexão dentro da tela
    this.conexao = conexao;
}

    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRelatorio = new javax.swing.JButton();
        btnMovimentacao = new javax.swing.JButton();
        btnCategoria = new javax.swing.JButton();
        btnProduto = new javax.swing.JButton();
        desktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("telaPrincipal");
        setPreferredSize(new java.awt.Dimension(900, 670));
        setResizable(false);

        btnRelatorio.setText("Relatorio");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        btnMovimentacao.setText("Movimentacão");
        btnMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimentacaoActionPerformed(evt);
            }
        });

        btnCategoria.setText("Categoria");
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        btnProduto.setText("Produto");
        btnProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutoActionPerformed(evt);
            }
        });

        desktop.setPreferredSize(new java.awt.Dimension(640, 580));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnProduto)
                .addGap(74, 74, 74)
                .addComponent(btnCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(btnMovimentacao)
                .addGap(81, 81, 81)
                .addComponent(btnRelatorio)
                .addGap(299, 299, 299))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRelatorio)
                    .addComponent(btnMovimentacao)
                    .addComponent(btnCategoria)
                    .addComponent(btnProduto))
                .addGap(18, 18, 18)
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(916, 678));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
          if (telaCategoria == null || !telaCategoria.isVisible()) {
    telaCategoria = new TelaCategoria(conexao);  // Passando a conexão corretamente
    desktop.add(telaCategoria);
    telaCategoria.setVisible(true);
}
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutoActionPerformed
       if (telaProduto == null || !telaProduto.isVisible()) {
        telaProduto = new TelaProduto(conexao);  // Sem necessidade de conexão, se não precisar
        desktop.add(telaProduto);
        telaProduto.setVisible(true);
    }
    }//GEN-LAST:event_btnProdutoActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
 if (telaRelatorio == null || !telaRelatorio.isVisible()) {
        telaRelatorio = new TelaRelatorio(conexao);  // Sem necessidade de conexão, se não precisar
        desktop.add(telaRelatorio);
        telaRelatorio.setVisible(true);
    }
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void btnMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimentacaoActionPerformed
        if (telaMovimentacao == null || !telaMovimentacao.isVisible()) {
        telaMovimentacao = new TelaMovimentacao(conexao);  // Sem necessidade de conexão, se não precisar
        desktop.add(telaMovimentacao);
        telaMovimentacao.setVisible(true);
    }
    }//GEN-LAST:event_btnMovimentacaoActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnMovimentacao;
    private javax.swing.JButton btnProduto;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JDesktopPane desktop;
    // End of variables declaration//GEN-END:variables
}
