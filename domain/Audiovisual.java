
package domain;


public class Audiovisual extends Material {
    
    private String brand;
    private String model;
    private String color;

    public Audiovisual() {
        this.brand = "";
        this.name = "";
        this.model = "";
        this.color = "";
    }
    
    public Audiovisual(String brand, String type, String model, String color) {
        this.brand = brand;
        this.name = type;
        this.model = model;
        this.color = color;
    }
    
    //Metodos de acceso
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return name;
    }

    public void setType(String type) {
        this.name = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //Este método verifica que el código de los materiales audiovisuales sea
    //menor a 5 dígitos y que solo se puedan ingresar números.
    @Override
    public boolean setMaterialCode(String materialCode) {
        if(materialCode.length() < 6 && materialCode.length() > 4) {
            try{
                Integer.parseInt(materialCode);
                this.materialCode = materialCode;
                return true;
            }catch(NumberFormatException nfe){
                System.err.println("Invalid Audiovisual Code");
                return false;
            }
        }else
            return false;
    }//fin metodo setMaterialCode 

    @Override
    public String toString() {
        return "Brand:\t\t" + brand 
                +"\nType:\t\t" + name 
                +"\nModel:\t\t" + model 
                +"\n" + super.toString();
    }//fin toString

    @Override
    public int sizeInBytes() {
        return super.sizeInBytes() 
                + brand.length()*2 
                + name.length()*2 
                + model.length()*2
                + color.length()*2; 
    }   
}//fin clase Audiovisual
