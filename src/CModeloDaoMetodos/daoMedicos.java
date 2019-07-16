/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CModeloDaoMetodos;

import modeloConection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import MmodeloBeansAtributos.BeansMedicos;

/**
 *
 * @author Denilson
 */
public class daoMedicos {

    //conexões
    ConexaoBD conexao00 = new ConexaoBD();
    //atributos encapsulados

    public void Salvar(BeansMedicos medico) {
        conexao00.conexao();
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("insert into medicos(nome_medico, espercializacao_medico,crm_medico) values(?,?,?)");
            pst.setString(1, medico.getNome());
            pst.setString(2, medico.getEspercializacao());
            pst.setInt(3, medico.getCrm());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados!/nErro:" + ex);
        }

        //conexao.desconectar();
    }

    public void Editar(BeansMedicos medico) {
        conexao00.conexao();
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("update medicos set nome_medico = ?, crm_medico = ?, espercializacao_medico = ? where cod_medico = ?");
            pst.setString(1, medico.getNome());
            pst.setInt(2, medico.getCrm());
            pst.setString(3, medico.getEspercializacao());
            pst.setInt(4, medico.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados do médico editado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar dados do médico!\nErro: " + ex);
        }
    }
    
    public void Excluir(BeansMedicos medico){
        conexao00.conexao();
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("delete from medicos where cod_medico = ?");
            pst.setInt(1, medico.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados do médico!\n" + ex);
        }
    }

    public BeansMedicos buscarMedicos(BeansMedicos medico) {
        //fazendo a conexão com o bd
        conexao00.conexao();
        //fazendo a pesquisa com o bd
        conexao00.executeSQL("select * from medicos where nome_medico like '%" + medico.getPesquisa() + "%'");
        try {
            //first ele vai pegar o primeiro resultado que ele acha no bd
            conexao00.rs.first();
            medico.setCodigo(conexao00.rs.getInt("cod_medico"));
            medico.setNome(conexao00.rs.getString("nome_medico"));
            medico.setCrm(conexao00.rs.getInt("crm_medico"));
            medico.setEspercializacao(conexao00.rs.getString("espercializacao_medico"));
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Medico não cadastrado!");
        }

        return medico;

    }
}
