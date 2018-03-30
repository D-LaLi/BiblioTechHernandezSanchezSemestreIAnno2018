
package domain;


public class Audiovisual extends Materials {
    
    private String brand;
    private String type; 
    private String model;

    public Audiovisual() {
        brand = "";
        type = "";
        model = "";
    }
    
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
