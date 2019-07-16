/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visão;

import CModeloDaoMetodos.daoAgenda;
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
public class cadAgendaMedica extends javax.swing.JFrame {

    ConexaoBD conexao0 = new ConexaoBD();
    daoAgenda dao = new daoAgenda();
    
    String cod_ag;
    String dtHoje;
    String status;
    
    public cadAgendaMedica() {
        initComponents();
        preecherMedicos();
        
        //para o calendario
        Calendar data = Calendar.getInstance();
        //para data
        Date d = data.getTime();
        //para formatação da data
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.format(d);
        
        dtHoje = dateFormat.format(d);
        status = "Atendimento";
    }
    
    public void preecherMedicos() {
        conexao0.conexao();
        conexao0.executeSQL("select * from medicos order by nome_medico");
        try {
            conexao0.rs.first();
            //vai limpar o combro box os dados que ja vem no campo para aparecer somente os do bd
            cboMedicos.removeAllItems();
            do {
                //vai adicionar um item que sera o nome_medico, no caso sera o selecionado
                cboMedicos.addItem(conexao0.rs.getString("nome_medico"));
            } while (conexao0.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preecher medicos" + ex);
        }

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
            JOptionPane.showMessageDialog(null, "Não existem mais agendamentos para este médico!");
        }
        //Dados a quantidade de linhas/ e colunas a quantidade de colunas
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        tblPacientes.setModel(modelo);
        //setPreferreWidth ela colocando o tamanho da tabela
        //getColumn(0) pq do (0)? pq a tabela começa do 0 como estamos trabalhado com um array ele começa da posição 0
        //getColumnModel é a coluna, getColumn e de onde vai começa a lista sendo que 0 e o ID e setPreferredWidth(23) é a lagura do campo id que vai receber os ids
        tblPacientes.getColumnModel().getColumn(0).setPreferredWidth(30);
        //setResizable e para o usuario não poder mexer no tamanho dos camposda tabela com o mouse esticado-a
        tblPacientes.getColumnModel().getColumn(0).setResizable(false);
        tblPacientes.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblPacientes.getColumnModel().getColumn(1).setResizable(false);
        tblPacientes.getColumnModel().getColumn(2).setPreferredWidth(105);
        tblPacientes.getColumnModel().getColumn(2).setResizable(false);
        tblPacientes.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblPacientes.getColumnModel().getColumn(3).setResizable(false);
        tblPacientes.getColumnModel().getColumn(4).setPreferredWidth(105);
        tblPacientes.getColumnModel().getColumn(4).setResizable(false);
        tblPacientes.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblPacientes.getColumnModel().getColumn(5).setResizable(false);
        //Não vai poder reodenar o cabeçario
        tblPacientes.getTableHeader().setReorderingAllowed(false);
        //O usuario não poderá redimecionar o tamanho da tabela
        tblPacientes.setAutoResizeMode(tblPacientes.AUTO_RESIZE_OFF);
        //Para o usuario poder somente selecionar um unico usuario da tabela por vez
        tblPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        jLabel2 = new javax.swing.JLabel();
        cboMedicos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        btnAtender = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Médico:");

        cboMedicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPacientes);

        btnAtender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAtender.setText("Atender Paciente");
        btnAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenderActionPerformed(evt);
            }
        });

        jLabel3.setText("Agendamento de pacientes");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(btnAtender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(475, 475, 475)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Agenda Médica");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        setSize(new java.awt.Dimension(705, 533));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int cod = dao.buscarCodMedico((String) cboMedicos.getSelectedItem());
        String codigo = String.valueOf(cod);
        preencherTabela("select * from agenda inner join pacientes on agenda_codpac = paci_codigo inner join medicos on agenda_codmedico = cod_medico where agenda_codmedico ='" +codigo+"' and agenda_data = '"+dtHoje+"' and agenda_status = '"+status+"'");
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacientesMouseClicked
        //Para pegar um valor na tabela
        cod_ag = "" + tblPacientes.getValueAt(tblPacientes.getSelectedRow(), 0);
    }//GEN-LAST:event_tblPacientesMouseClicked

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        cadConsulta tela = new cadConsulta(cod_ag);
        tela.setVisible(true);
        dispose();
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
            java.util.logging.Logger.getLogger(cadAgendaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cadAgendaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cadAgendaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cadAgendaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cadAgendaMedica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cboMedicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPacientes;
    // End of variables declaration//GEN-END:variables
}
