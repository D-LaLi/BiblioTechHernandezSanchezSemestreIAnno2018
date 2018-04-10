
package domain;

import java.io.Serializable;

public abstract class Material implements Serializable {
    
    protected String materialCode;
    protected int quantity;
    protected String name;
    
    // constructor 
    public Material() {
        name = "";
        materialCode = "";
        quantity = 1;
    }

    //Metodos de acceso
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getMaterialCode() {
        return materialCode;
    }
    
    //Método abstractos
    public abstract boolean setMaterialCode(String materialCode);

    @Override //se reescribe toString
    public String toString() {
        return  "Code:\t\t" + materialCode
                + "\nQuantity:\t\t" + quantity;
    }
    
    //Metodo que retorna el tamano de los atributos en bytes
    public int sizeInBytes(){
        return 4 + materialCode.length() * 2;
    }
    
}//fin clase Material
