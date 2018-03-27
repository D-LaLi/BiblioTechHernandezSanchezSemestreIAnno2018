
package files;

import domain.Materials;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class MaterialFiles {
    
    //atributos
    public RandomAccessFile randomAccessFile;
    private int regsQuantity; //cantidad de registros que tiene mi archivo
    private int regSize; //el tamano el bits de mis registros
    private String myFilePath; //ruta del archivo
    
    public MaterialFiles(File file) throws IOException {
        myFilePath = file.getPath();
        regSize = 200;
        if(file.exists() && !file.isFile()){ 
            throw new IOException(file.getName() + "Invalid file");
        }else{
            randomAccessFile = new RandomAccessFile(file, "rw");
            regsQuantity = (int)Math.ceil((double)randomAccessFile.length()/(double)this.regSize);
        }
    }//fin constructor

    
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
   
    //metodo para insertar al final de archivo
    public boolean addEndRecord(Materials materialToInsert) throws IOException{
        boolean success = putValue(this.regsQuantity, materialToInsert);
        if(success)
            ++this.regsQuantity;
        return success;
    }//end method
    
    //Metodo abstracto para insertar un material en una posicion especifica
    public abstract boolean putValue(int position, Materials materialToInsert)throws IOException;
    
}//fin de la clase MaterialFiles
