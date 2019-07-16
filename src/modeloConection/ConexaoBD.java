/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloConection;

import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Denilson
 */
public class ConexaoBD {
    
    public Statement stm;//Pesquisa no bd
    public ResultSet rs;//armazena o resultado de uma pesquisa
    private String driver = "org.postgresql.Driver";//identifica o sevidor bd
    private String caminho = "jdbc:postgresql://localhost:5432/projetoclinica";
    private String usuario = "postgres";
    private String senha = "1234";
    public Connection conexa0;//realiza a conexão com o bd
    
    public void conexao(){
        
        try {
            System.setProperty("jdbc.Driver", driver);
            conexa0 = DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conexão Efetuada com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se comunicar ao Banco de Dados!:\n" + ex);
        }
    }
    
    public void executeSQL(String sql){
        try {
            //TYPE_SCROLL_INSENSITIVE para poder pesquisar tanto em minusculo ou maiusculo
            //CONCUR_READ_ONLY para percorrer a lista do começo ao fim de forma rolation
            stm = conexa0.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executeSQL!:\n" + ex);
        }
    }
    /*public void desconectar(){
        try {
            con.close();
            JOptionPane.showMessageDialog(null, "BD Desconectado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com banco de dados!" + e);
        }
    }*/
    
}
