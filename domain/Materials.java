
package domain;

import java.io.Serializable;

public abstract class Materials implements Serializable {
    
    protected String materialCode;
    protected int quantity;
    
    // constructor 
    public Materials() {
        materialCode = "";
        quantity = 1;
    }

    //Metodos de acceso
    public int getQuantity() {
        return quantity;
    }

    //Metodo para definir la cantidad de un mismo producto que se va a ingresar
    //Se valida que dicha cantidad sean mayor que 0
    public void setQuantity(int quantity) {
        if(quantity > 0) {
            this.quantity = quantity;
        }
    }
    
    public String getMaterialCode() {
        return materialCode;
    }
    
    //método para verificar el codigo del material
    public abstract boolean setMaterialCode(String materialCode);

    @Override
    public String toString() {
        return  "Code:\t\t" + materialCode
                + "\nQuantity:\t" + quantity;
    }
    
    //Metodo que retorna el tamano de los atributos en bytes
    public int sizeInBytes(){
        return 4 + materialCode.length() * 2;
    }
    
}//fin clase Materials
