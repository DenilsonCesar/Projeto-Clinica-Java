/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MmodeloBeansAtributos;

import java.util.Date;

/**
 *
 * @author Denilson
 */
public class BeansAgenda {
    
    private int agendaCod;
    private String nomeMed;
    private String nomePac;
    private String turno;
    private Date data;
    private String status;
    private String motivo;
    private String pacienteNasc;

    public String getPacienteNasc() {
        return pacienteNasc;
    }

    public void setPacienteNasc(String pacienteNasc) {
        this.pacienteNasc = pacienteNasc;
    }

    public int getAgendaCod() {
        return agendaCod;
    }

    public void setAgendaCod(int agendaCod) {
        this.agendaCod = agendaCod;
    }

    public String getNomeMed() {
        return nomeMed;
    }

    public void setNomeMed(String nomeMed) {
        this.nomeMed = nomeMed;
    }

    public String getNomePac() {
        return nomePac;
    }

    public void setNomePac(String nomePac) {
        this.nomePac = nomePac;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
