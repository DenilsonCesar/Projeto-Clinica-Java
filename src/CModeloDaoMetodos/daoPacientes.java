/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CModeloDaoMetodos;

import MmodeloBeansAtributos.BeansPacientes;
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
public class daoPacientes {

    ConexaoBD conexao00 = new ConexaoBD();
    ConexaoBD conexBairro = new ConexaoBD();
    //para armazernar o nome do bairro
    String nomeBairro;
    //Vai receber o codigo do bairro
    int codBai;

    public void salvar(BeansPacientes paciente) {
        //passando a string que o buscarBaiCod esta passando em seu método
        buscarBaiCod(paciente.getNomeBairro());
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("insert into pacientes(paci_nome, paci_rg, paci_telefone, paci_rua, paci_cep, paci_complemento, paci_baicodigo, paci_nasc) values(?,?,?,?,?,?,?,?)");
            pst.setString(1, paciente.getNomePac());
            pst.setString(2, paciente.getRg());
            pst.setString(3, paciente.getTelefone());
            pst.setString(4, paciente.getRua());
            pst.setString(5, paciente.getCep());
            pst.setString(6, paciente.getComplemento());
            //e so codBai pq abaixo tem um metodo que nele o codBai recebe o codigo do bairro
            pst.setInt(7, codBai);
            pst.setString(8, paciente.getNasc());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Paciente adicionado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar paciente!" + ex);
        }
    }

    public void alterar(BeansPacientes paciente) {
        //passando a string que o buscarBaiCod esta passando em seu método
        buscarBaiCod(paciente.getNomeBairro());
        conexao00.conexao();
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("update pacientes set paci_nome = ?, paci_rg = ?, paci_telefone = ?, paci_rua = ?, paci_cep = ?, paci_complemento = ?, paci_baicodigo = ?, paci_nasc = ? where paci_codigo = ?");
            pst.setString(1, paciente.getNomePac());
            pst.setString(2, paciente.getRg());
            pst.setString(3, paciente.getTelefone());
            pst.setString(4, paciente.getRua());
            pst.setString(5, paciente.getCep());
            pst.setString(6, paciente.getComplemento());
            //e so codBai pq abaixo tem um metodo que nele o codBai recebe o codigo do bairro
            pst.setInt(7, codBai);
            pst.setString(8, paciente.getNasc());
            pst.setInt(9, paciente.getCodPac());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar paciente!" + ex);
        }
    }
    
    //metodo para enviar o codigo do bairro para o cadastro de paciente pois o codigo da tabela bairro e a chave estrangeira da
    //tabela pacientes nela em vez de mostrar o cod vai ser exibido o nome do bairro
    //String nome sera o nome que sera passado para o metodo salvar para receber o nome do bairro 
    public void buscarBaiCod(String nome) {
        conexao00.conexao();
        try {
            //nome vai ser o nome que vai ser passado por parametro na hora de salvar que sera o nome do bairro
            conexao00.executeSQL("select * from bairro where bainome = '" + nome + "'");
            conexao00.rs.first();
            //É responsavel por buscar o código do bairro
            codBai = conexao00.rs.getInt("baicodigo");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar bairro" + ex);
        }
    }
    
    //beansPacientes e o objeto que na busca tem que aver um retorno do tipo passado por parametro ou seja do tipo da busca que desejamos fazer
    public BeansPacientes buscarPacientes(BeansPacientes paciente){
        conexao00.conexao();
        try {
            conexao00.executeSQL("select * from pacientes where paci_nome like '%" + paciente.getPesquisar()+ "%'");
            conexao00.rs.first();
            //vai pegar o resultado do metodo buscarNomeBairro, se for igual a chave estrangeira a baixo vai trazer o 
            //resultado da buscar e armazena-la nela
            buscarNomeBairro(conexao00.rs.getInt("paci_baicodigo"));
            //O codigo a cima e diferente por se tratar de uma chave estrangeira 
            paciente.setCodPac(conexao00.rs.getInt("paci_codigo"));
            paciente.setNomePac(conexao00.rs.getString("paci_nome"));
            paciente.setRg(conexao00.rs.getString("paci_rg"));
            paciente.setTelefone(conexao00.rs.getString("paci_telefone"));
            paciente.setRua(conexao00.rs.getString("paci_rua"));
            paciente.setCep(conexao00.rs.getString("paci_cep"));
            paciente.setComplemento(conexao00.rs.getString("paci_complemento"));
            paciente.setNasc(conexao00.rs.getString("paci_nasc"));
            //foi setado o nome do bairro para dentro do nomeBairro
            paciente.setNomeBairro(nomeBairro);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar paciente" + ex);
        }
        return paciente;
    }
    
    //vai fazer a pesquisa do codigo do bairro e trazer como resultado o nome do bairro
    public void buscarNomeBairro(int cod) {
        conexBairro.conexao();
        try {
            //vai pesquisar o codigo do bairro
            conexBairro.executeSQL("select * from bairro where baicodigo = " + cod);
            conexBairro.rs.first();
            //a variavel global criada, vai receber o resultado da pesquisa do bairro e vai trazer o nome do bairro
            nomeBairro = conexBairro.rs.getString("bainome");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar nome do bairro" + ex);
        }

    }
    
    public void excluir(BeansPacientes paciente){
        conexao00.conexao();
        try {
            PreparedStatement pst = conexao00.conexa0.prepareStatement("delete from pacientes where paci_codigo = ?");
            pst.setInt(1, paciente.getCodPac());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Paciente excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente!" + ex);
        }
    }

}
