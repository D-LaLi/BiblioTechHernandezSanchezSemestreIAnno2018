
package domain;

import files.StudentFile;
import java.io.IOException;
import java.io.Serializable;


public class IdStudent implements Serializable{
    
    private int initials;
    private int year;
    private int secuenceNum;
    private String carnet;
   
    //constructores
    public IdStudent(int initials, int year) {
        this.initials = initials;
        this.year = year; 
        setCarnet();
    }

    public IdStudent() {
        this.initials = 0;
        this.year = 0;    
    }
    
    //accesores
    public int getInitials() {
        return initials;
    }

    public void setInitials(int initials) {
        this.initials = initials;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    //Metodo que calcula el numero consecutivo y retorna el setCarnet final del estudiante;
    public void setCarnet() {
        
        StudentFile sf = new StudentFile("./student.dat");
        
        //se le indica el numero de estudiantes que hay en el registro
        secuenceNum = sf.getLastRegistry();
        
        secuenceNum++; //hacer numero de tres crifras
        carnet = "";
        String secuence = "" + secuenceNum;

        while(secuence.length() < 3){
            secuence = "0" + secuence;
        }
     
        year = year % 10;
        carnet = "" + year + secuence;
        
     // 1 = Informatica
     // 2 = Agronomia
     // 3 = Educacion 
        if(initials == 1)
            carnet = "I" + carnet;
        else if(initials == 2)
            carnet = "A" +carnet;
        else
            carnet = "E" + carnet;
    
    }//fin metodo setCarnet
    
    public String getCarne() {
        return carnet;
    }
    
    public int sizeInBytes(){
        return 6;
    }
    
}//fin clase IdStudent
