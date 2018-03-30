
package domain;


public class IdStudent {
    
    private int initials;
    private int year;
    private static short secuenceNum;
   
    //constructores
    public IdStudent(int initials, int year) {
        this.initials = initials;
        this.year = year;        
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
    
    //Metodo que calcula el numero consecutivo y retorna el carne final del estudiante;
    public String carne() {
        secuenceNum++; //hacer numero de tres crifras
        String result = "";
        String secuence = "" + secuenceNum;

        while(secuence.length() < 3){
            secuence = "0" + secuence;
        }
     
        year = year % 10;
        result = "" + year + secuence;
        
     // 1 = Informatica
     // 2 = Agronomia
     // 3 = Educacion 
        if(initials == 1)
            result = "I" + result;
        else if(initials==2)
            result = "A" +result;
        else
            result = "E" + result;
    
        return result;
    }//fin metodo carne
    
    public int sizeInBytes(){
        return 6;
    }

    @Override
    public String toString() {
        return carne();
    }
    
}//fin clase IdStudent
