/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MmodeloBeansAtributos;

/**
 *
 * @author Denilson
 */
public class BeansMedicos {
    
    private int codigo;
    private String nome;
    private String espercializacao;
    private int crm;
    private String pesquisa;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspercializacao() {
        return espercializacao;
    }

    public void setEspercializacao(String espercializacao) {
        this.espercializacao = espercializacao;
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }
    
    public String getPesquisa(){
        return pesquisa;
    }
    
    public void setPesquisa(String p){
        this.pesquisa = p;
    }
}
