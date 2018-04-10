
package files;

import domain.Book;
import domain.Material;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class MaterialFile {
    
    //atributos
    public RandomAccessFile randomAccessFile;
    protected int regsQuantity; //cantidad de registros que tiene mi archivo
    protected int regSize; //el tamano el bits de mis registros
    protected String myFilePath; //ruta del archivo
    
    public MaterialFile(File file) throws IOException {
        myFilePath = file.getPath();
        regSize = 200;
        if(file.exists() && !file.isFile()){ 
            throw new IOException(file.getName() + "Invalid file");
        }else{
            randomAccessFile = new RandomAccessFile(file, "rw");
            regsQuantity = (int)Math.ceil((double)randomAccessFile.length()/(double)this.regSize);
        }
    }//fin constructor
    
    //metodo para insertar al final de archivo
    public boolean addEndRecord(Material materialToInsert) throws IOException{
        if (!verifyMaterials(materialToInsert)) {
            boolean success = putValue(this.regsQuantity, materialToInsert);
            if(success){
                ++this.regsQuantity;
                return success;
            }
        }else {return true;}
        return false;
    }//end method
    
    //Método que verifica si el material que se esta intentando registrar ya existe
    //en los archivos. De ser así aumenta la cantidad de ese producto.
    public boolean verifyMaterials(Material material) throws IOException {
        int aux = 0;
            for (int i = 0; i < regsQuantity; i++) {
                if(material.getMaterialCode().equals(getMaterial(i).getMaterialCode())) {
                    aux = material.getQuantity() + getMaterial(i).getQuantity();
                    material = getMaterial(i);
                    material.setQuantity(aux);
                    putValue(i, material);
                    return true;
                }
            }//fin for
            return false;
    }//fin verifiy material
    
    //Metodo que me retorna todos lo materiales del registro en un arrayList
    public ArrayList<Material> getListOfMaterials() throws IOException {
        ArrayList<Material> listOfMaterials = new ArrayList<>();
        for (int i = 0; i < regsQuantity; i++) {
            if(this.getMaterial(i) != null){
                listOfMaterials.add(getMaterial(i));
            }
        }
        return listOfMaterials;
    }//fin metodo getListOfMaterials
    
    //Este metodo realiza un busqueda de los materiales y compara el codigo
    //de ese material con el atributo que se le pasa, retorna un ArrayList
    //con los materiales que coincidieron con los digitos de dicho codigo
    public ArrayList<Material> getListByCode(String code) {
        try {
            ArrayList<Material> temp_list = new ArrayList<>();
            boolean match = false;
            for (int i = 0; i < getListOfMaterials().size(); i++) {
                Material temp_mat = getListOfMaterials().get(i);
                for (int j = 0; j < code.length(); j++) {
                    if(temp_mat.getMaterialCode().charAt(j) == code.charAt(j)) {   
                        match = true;
                    }else{
                        match = false;
                        break;
                    }    
                }//fin for
                if(match)
                    temp_list.add(temp_mat);
            }//fin for
            return temp_list;   
        } catch (IOException ex) {
            System.err.println("Error at getListOfBooks");
            return null;
        }
    }//fin metodo getListByCode
    
    
    //Este metodo realiza un busqueda de los materiales y compara el nombre
    //de ese material con el atributo que se le pasa, retorna un ArrayList
    //con los materiales que coincidieron con los caracteres de dicho nombre.
    //La variable name para libros es equivalente al titulo del libro y para
    //Audiovisuales es equivalente al tipo.
    public ArrayList<Material> getListByName(String name) {
        try {
            ArrayList<Material> temp_list = new ArrayList<>();
            boolean match = false;
            for (int i = 0; i < getListOfMaterials().size(); i++) {
                Material temp_mat = getListOfMaterials().get(i);
                for (int j = 0; j < name.length(); j++) {
                    if(temp_mat.getName().charAt(j) == name.charAt(j)) {   
                        match = true;
                    }else{
                        match = false;
                        break;
                    }    
                }//fin for
                if(match)
                    temp_list.add(temp_mat);
            }//fin for
            return temp_list;   
        } catch (IOException ex) {
            System.err.println("Error at getListOfBooks");
            return null;
        }
    }//fin metodo getListByCode
    
    //determina si lo que se esta ingresando como parametro son numero o letras
    //y llama a los metodos getListByCode and getListByName
    public ArrayList<Material> getList(String str_1){
        if(!str_1.equals("")){
            if(((int)str_1.charAt(0) > 64 && (int)str_1.charAt(0) < 91) 
                    || ((int)str_1.charAt(0) > 96 && (int)str_1.charAt(0) < 123) )
                return getListByName(str_1);
            else if((int)str_1.charAt(0) > 47 && (int)str_1.charAt(0) < 58)
                return getListByCode(str_1);
            else
                return null;
        }
        return null;
    }//fin getList
    
    //Métodos abstractos 
    public abstract boolean putValue(int position, Material materialToInsert)throws IOException;
    
    public abstract Material getMaterial(int position)throws IOException;
 
    //Metodos de acceso
    public int getRegsQuantity() {
        return regsQuantity;
    }

    public void setRegsQuantity(int regsQuantity) {
        this.regsQuantity = regsQuantity;
    } 

    public int getRegSize() {
        return regSize;
    }

    public void setRegSize(int regSize) {
        this.regSize = regSize;
    }
      
}//fin de la clase MaterialFile
