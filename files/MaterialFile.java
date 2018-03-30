
package files;

import domain.Book;
import domain.Materials;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

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
    public boolean addEndRecord(Materials materialToInsert) throws IOException{
        if (!verifyMaterials(materialToInsert)) {
            boolean success = putValue(this.regsQuantity, materialToInsert);
            if(success){
                ++this.regsQuantity;
                return success;
            }
        }else
            return true;
        return false;
    }//end method
    
    //Método que verifica si el material que se esta intentando registrar ya existe
    //en los archivos. De ser así aumenta la cantidad de ese producto.
    public boolean verifyMaterials(Materials material) throws IOException {
        int aux = 0;
            for (int i = 0; i < regsQuantity; i++) {
                if(material.getMaterialCode().equals(getMaterial(i).getMaterialCode())) {
                    aux = material.getQuantity() + getMaterial(i).getQuantity();
                    material.setQuantity(aux);
                    putValue(i, material);
                    return true;
                }
            }//fin for
            return false;
    }//fin verifiy material
    
    //Método abstracto para insertar un material en una posición específica
    public abstract boolean putValue(int position, Materials materialToInsert)throws IOException;
    
    //Método abstracto que retorna un material ubicado en una posición específica
    //de un archivo
    public abstract Materials getMaterial(int position)throws IOException;
 
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
