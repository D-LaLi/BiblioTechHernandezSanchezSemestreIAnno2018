
package domain;


public class Audiovisual extends Materials {
    
    private String brand;
    private String type; 
    private String model;

    public Audiovisual(String brand, String type, String model) {
        this.brand = brand;
        this.type = type;
        this.model = model;
    }
    
    //Metodos de acceso
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //Este metodo verifica que el codigo de los materiales audiovisuales sea
    //menor a 5 digitos.
    @Override
    public boolean setCode(String materialCode) {
        if(materialCode.length() < 6) {
            this.materialCode = materialCode;
            return false;
        }else
            return true;
    }//fin metodo setCode 

    @Override
    public String toString() {
        return "Brand:\t\t" + brand 
                +"\nType:\t\t" + type 
                +"\nModel:\t\t" + model 
                +"\n" + super.toString();
    }//fin toString

    @Override
    public int sizeInBytes() {
        return super.sizeInBytes() 
                + brand.length()*2 
                + type.length()*2 
                + model.length()*2; 
    }
    
    
}//fin clase Audiovisual
