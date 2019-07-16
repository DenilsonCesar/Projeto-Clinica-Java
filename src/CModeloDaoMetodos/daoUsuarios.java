package CModeloDaoMetodos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import MmodeloBeansAtributos.BeansUsuarios;
import modeloConection.ConexaoBD;

public class daoUsuarios {

    //conexões
    ConexaoBD conexao = new ConexaoBD();
    //atributos encapsulados
    //BensUsuario e para trazer os atributos da sua classe
    public void Salvar(BeansUsuarios usuario) {
        conexao.conexao();
        try{
            PreparedStatement pst = conexao.conexa0.prepareStatement("insert into usuarios(usu_nome, usu_senha, usu_tipo) values(?,?,?)");
            pst.setString(1, usuario.getUsuNome());
            pst.setString(2, usuario.getUsuSenha());
            pst.setString(3, usuario.getUsuTipo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuário inseridos com sucesso!");
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Erro ao inserir usuário!");
        }

    }
    
    public void Alterar(BeansUsuarios usuario){
        conexao.conexao();
        try{
            PreparedStatement pst = conexao.conexa0.prepareStatement("update usuarios set usu_nome = ?, usu_senha = ?, usu_tipo = ? where usu_cod = ?");
            pst.setString(1, usuario.getUsuNome());
            pst.setString(2, usuario.getUsuSenha());
            pst.setString(3, usuario.getUsuTipo());
            pst.setInt(4, usuario.getUsuCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao editar dados do usuário!\nErro: " + ex);
        }
    }
    
   // Esta buscando os dados do bd
     public BeansUsuarios buscarUsuario(BeansUsuarios usuario) {
        //fazendo a conexão com o bd
        conexao.conexao();
        //fazendo a pesquisa com o bd
        //Para pesquisar com a letra digitada exemplo "a", vai trazer todos os registros que contem a latra "a"
        conexao.executeSQL("select * from usuarios where usu_nome like '%" + usuario.getUsuPesquisa()+ "%'");
        try {
            //first ele vai pegar o primeiro resultado que ele acha no bd
            conexao.rs.first();
            usuario.setUsuCod(conexao.rs.getInt("usu_cod"));
            usuario.setUsuNome(conexao.rs.getString("usu_nome"));
            usuario.setUsuSenha(conexao.rs.getString("usu_senha"));
            usuario.setUsuTipo(conexao.rs.getString("usu_tipo"));
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
        }
        return usuario;
    }
     
      public void Excluir(BeansUsuarios usuario){
          conexao.conexao();
        try {
            PreparedStatement pst = conexao.conexa0.prepareStatement("delete from usuarios where usu_cod = ?");
            pst.setInt(1, usuario.getUsuCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados do Usuário!\n" + ex);
        }
    }
}
