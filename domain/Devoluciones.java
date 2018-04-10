/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ahern
 */
public class Devoluciones {
    
    //metodo que verifica si la ficha actual es mayor que la fecha final
    public int penalty(String fecha_) throws ParseException {
        int monto = 0;      
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
        Date fechaFinal = format.parse(fecha_);
        
        //captura la fecha actual del sistema
        Date fechaActual = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        //pregunta si fechaActual es mayor que fechaFinal
        if(fechaFinal.compareTo(fechaActual) < 0){
            //se aplica la multa
            monto = 1000;
        }
        return monto;
    }//fin montoPorMulta
    
}
