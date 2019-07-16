/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MmodeloBeansAtributos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Denilson
 */
public class Hora {
    
    public String hora;
    SimpleDateFormat hf = new SimpleDateFormat("HH:mm:ss");
    
    public void ler_hora(){
        
        Date horaAtual = new Date();
        hora = hf.format(horaAtual);
    }
}
