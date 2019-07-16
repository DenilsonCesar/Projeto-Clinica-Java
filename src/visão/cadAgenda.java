/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visão;

import CModeloDaoMetodos.daoAgenda;
import MmodeloBeansAtributos.BeansAgenda;
import MmodeloBeansAtributos.ModeloTabela;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import modeloConection.ConexaoBD;

/**
 *
 * @author Denilson
 */
public class cadAgenda extends javax.swing.JFrame {

    ConexaoBD conexao0 = new ConexaoBD();
    //cadAgenda agenda = new cadAgenda();
    BeansAgenda agenda = new BeansAgenda();
    daoAgenda dao = new daoAgenda();
    
    String status;
    String dtHoje;
    
    public cadAgenda() {
        initComponents();
        //para o calendario
        Calendar data = Calendar.getInstance();
        //para data
        Date d = data.getTime();
        //para formatação da data
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.format(d);
        
        dtHoje = dateFormat.format(d);
        status = "Aberto";
        
        preencherTabela("select * from agenda inner join pacientes on agenda_codpac = paci_codigo inner join medicos on agenda_codmedico = cod_medico where agenda_data ='"+dtHoje+"' and agenda_status = '"+status+"' order by agenda_turno");
    }
    
    public void preencherTabela(String sql) {
        //Esta instanciando as listas para fazer as linhas da tabela
        ArrayList dados = new ArrayList();
        //Instanciando a String sendo que e um vetor
        //{} dentro das chaves vai ser o nome das colunas
        //Esta instanciando as colunas para fazer as colunas da tabela
        String[] colunas = new String[]{"ID", "Nome", "Turno", "Data", "Status", "Médico"};
        //conexao0 e a instanciação da classe conexaoBD, e conexao e o metodo que está dentro dela que faz a conexão com o bd
        conexao0.conexao();
        //conexao0, é a conexao com o banco
        //Vai fazer a execução do codigo sql
        conexao0.executeSQL(sql);
        try {
            //vai pegar o primeiro resultado da conexao0
            conexao0.rs.first();
            do {//trazendo os campos do banco para a tabela
                dados.add(new Object[]{conexao0.rs.getInt("agenda_cod"), conexao0.rs.getString("paci_nome"), conexao0.rs.getString("agenda_turno"), conexao0.rs.getDate("agenda_data"), conexao0.rs.getString("agenda_status"), conexao0.rs.getString("nome_medico")});
            }//Equanto estiver dados ele vai ficar percorrendo este registro, ele vai andar nas posicões pois não existe uma limitação e sim uma condição para ele percorrer tudo que ele achar
            while (conexao0.rs.next());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não existem mais agendamentos para hoje!");
        }
        //Dados a quantidade de linhas/ e colunas a quantidade de colunas
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tblAgenda.setModel(modelo);
        //setPreferreWidth ela colocando o tamanho da tabela
        //getColumn(0) pq do (0)? pq a tabela começa do 0 como estamos trabalhado com um array ele começa da posição 0
        //getColumnModel é a coluna, getColumn e de onde vai começa a lista sendo que 0 e o ID e setPreferredWidth(23) é a lagura do campo id que vai receber os ids
        tblAgenda.getColumnModel().getColumn(0).setPreferredWidth(30);
        //setResizable e para o usuario não poder mexer no tamanho dos camposda tabela com o mouse esticado-a
        tblAgenda.getColumnModel().getColumn(0).setResizable(false);
        tblAgenda.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblAgenda.getColumnModel().getColumn(1).setResizable(false);
        tblAgenda.getColumnModel().getColumn(2).setPreferredWidth(105);
        tblAgenda.getColumnModel().getColumn(2).setResizable(false);
        tblAgenda.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblAgenda.getColumnModel().getColumn(3).setResizable(false);
        tblAgenda.getColumnModel().getColumn(4).setPreferredWidth(105);
        tblAgenda.getColumnModel().getColumn(4).setResizable(false);
        tblAgenda.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblAgenda.getColumnModel().getColumn(5).setResizable(false);
        //Não vai poder reodenar o cabeçario
        tblAgenda.getTableHeader().setReorderingAllowed(false);
        //O usuario não poderá redimecionar o tamanho da tabela
        tblAgenda.setAutoResizeMode(tblAgenda.AUTO_RESIZE_OFF);
        //Para o usuario poder somente selecionar um unico usuario da tabela por vez
        tblAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgenda = new javax.swing.JTable();
        btnAtender = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Agendamento para hoje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        tblAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblAgenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAgendaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAgenda);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAtender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAtender.setText("Atender Agendamento");
        btnAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Agenda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(714, 472));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblAgendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAgendaMouseClicked
        String agenda_cod = "" + tblAgenda.getValueAt(tblAgenda.getSelectedRow(), 0);
        conexao0.conexao();
        conexao0.executeSQL("select * from agenda where agenda_cod = '" + agenda_cod + "'");
        try {
            conexao0.rs.first();
            agenda.setStatus("Atendimento");
            agenda.setAgendaCod(conexao0.rs.getInt("agenda_cod"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar dados!" + ex);
        }
    }//GEN-LAST:event_tblAgendaMouseClicked

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        dao.Alterar(agenda);
        preencherTabela("select * from agenda inner join pacientes on agenda_codpac = paci_codigo inner join medicos on agenda_codmedico = cod_medico where agenda_data ='"+dtHoje+"' and agenda_status = '"+status+"' order by agenda_turno");
    }//GEN-LAST:event_btnAtenderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cadAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cadAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cadAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cadAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cadAgenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAgenda;
    // End of variables declaration//GEN-END:variables
}
