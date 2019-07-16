/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CModeloDaoMetodos;

import MmodeloBeansAtributos.BeansAgenda;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloConection.ConexaoBD;

/**
 *
 * @author Denilson
 */
public class daoAgenda {

    BeansAgenda agenda = new BeansAgenda();
    ConexaoBD conexao00 = new ConexaoBD();
    ConexaoBD conexPaciente = new ConexaoBD();
    ConexaoBD conexMedico = new ConexaoBD();
   
    int codMed;
    int codPac;

    public void Salvar(BeansAgenda agenda) {
        buscarMedico(agenda.getNomeMed());
        buscarPaciente(agenda.getNomePac());
        conexao00.conexao();

        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("insert into agenda (agenda_codpac, agenda_codmedico, agenda_motivo, agenda_turno, agenda_data, agenda_status) values(?,?,?,?,?,?)");
            pst.setInt(1, codPac);
            pst.setInt(2, codMed);
            pst.setString(3, agenda.getMotivo());
            pst.setString(4, agenda.getTurno());
            //Para pegar a data no novo formato
            pst.setDate(5, new java.sql.Date(agenda.getData().getTime()));
            pst.setString(6, agenda.getStatus());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Paciente agendado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar agenda" + ex);
        }
    }
    //Para pegar o nome do medico
    public void buscarMedico(String nomeMedico) {
        conexMedico.conexao();
        conexMedico.executeSQL("select * from medicos where nome_medico = '" + nomeMedico + "'");
        try {
            conexMedico.rs.first();
            codMed = conexMedico.rs.getInt("cod_medico");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado!");
        }
    }
    //Para pegar o codigo do medico
    public int buscarCodMedico(String nomeMedico) {
        conexMedico.conexao();
        conexMedico.executeSQL("select * from medicos where nome_medico = '" + nomeMedico + "'");
        try {
            conexMedico.rs.first();
            codMed = conexMedico.rs.getInt("cod_medico");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado!");
        }
        return codMed;
    }

    public void buscarPaciente(String nomePaciente) {
        conexPaciente.conexao();
        conexPaciente.executeSQL("select * from pacientes where paci_nome = '" + nomePaciente + "'");
        try {
            conexPaciente.rs.first();
            codPac = conexPaciente.rs.getInt("paci_codigo");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado!");
        }
    }
    
    public void BuscarAgendamento(BeansAgenda agenda){
        conexMedico.conexao();
        conexMedico.executeSQL("select * from agenda where agenda_data = '" +agenda.getData()+ "'");
        try {
            conexMedico.rs.first();
            codMed = conexMedico.rs.getInt("cod_medico");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Agendamento não encontrado!");
        }
        
    }
    
    //Método para alterar status do agendamento do paciente
    public void Alterar(BeansAgenda agenda){
        conexao00.conexao();
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("update agenda set agenda_status = ? where agenda_cod = ?");
            pst.setString(1, agenda.getStatus());
            pst.setInt(2, agenda.getAgendaCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Agendamento em andamento!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atender agendamento!" + ex);
        }
    }
    
    public BeansAgenda buscarAgendaPorCodigo(int cod){
        //para zera o que tem dentro da variavel agenda
        BeansAgenda agen = new BeansAgenda();
        conexao00.conexao();
        conexao00.executeSQL("select * from agenda inner join pacientes on agenda_codpac = paci_codigo inner join medicos on agenda_codmedico = cod_medico where agenda_cod = '" +cod+ "'");
        try {
            conexao00.rs.first();
            agen.setNomePac(conexao00.rs.getString("paci_nome"));
            agen.setNomeMed(conexao00.rs.getString("nome_medico"));
            agen.setMotivo(conexao00.rs.getString("agenda_motivo"));
            agen.setPacienteNasc(conexao00.rs.getString("paci_nasc"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar agendamento por codigo!" + e);
        }
        return agen;
        
    }
}
