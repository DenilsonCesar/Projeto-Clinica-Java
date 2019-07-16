
package MmodeloBeansAtributos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTabela extends AbstractTableModel {
    
    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public ModeloTabela(ArrayList lin, String[] col){
        setLinhas(lin);
        setColunas(col);
        
        
    }

    public ModeloTabela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the linhas
     */
    public ArrayList getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    /**
     * @return the colunas
     */
    public String[] getColunas() {
        return colunas;
    }

    /**
     * @param colunas the colunas to set
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    //vai contar a quantidade de colunas, a quantidade de retornos
    //length == comprimento
    public int getColumnCount(){
        return colunas.length;
    }
    //Vai contar a quantidade de linhas que vamos ter na tabela
    //size == tamanho, É usada quando a lista pode ser muito grande e um length nao pode dar conta
    public int getRowCount(){
        return linhas.size();
    }
    //Após ele pegar os nomes ele vai retornar quantas colunas deu
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    //Este metodo que vai montar a tabela, ele que vai montar as linhas da tabela
    public Object getValueAt(int numLin, int numCol){
       Object[] linha = (Object[])getLinhas().get(numLin);
       return linha[numCol];
    }
}
