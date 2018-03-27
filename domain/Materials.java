
package domain;


public abstract class Materials {
    
    protected String materialCode;
    protected boolean available; //indica si esta disponible o no
    
    // constructor
    public Materials() {
        available = true;
    }
    
    //método para verificar el codigo del material
    public abstract boolean setCode(String materialCode);

    //Metodos de acceso
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public String getMaterialCode() {
        return materialCode;
    }

    @Override
    public String toString() {
        return  "Code:\t\t" + materialCode
                + "\nAvailable:\t" + available;
    }
    
    //Metodo que retorna el tamano de los atributos en bytes
    public int sizeInBytes(){
        return 1 + materialCode.length() * 2;
    }
    
}//fin clase Materials
