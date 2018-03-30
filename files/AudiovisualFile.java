/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import domain.Audiovisual;
import domain.Book;
import domain.Materials;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ahern
 */
public class AudiovisualFile extends MaterialFile {

    public AudiovisualFile(File file) throws IOException {
        super(file);
    }

    @Override
    public boolean putValue(int position, Materials materialToInsert) throws IOException {
        if(position >= 0 && position <= regsQuantity){
            if(materialToInsert.sizeInBytes() > regSize){
                System.err.println("error - 401 invalid size at AudiovisualFile");
                return false;
            }else{
                randomAccessFile.seek(position * 200);
                randomAccessFile.writeUTF(((Audiovisual)materialToInsert).getBrand());
                randomAccessFile.writeUTF(((Audiovisual)materialToInsert).getType());
                randomAccessFile.writeUTF(((Audiovisual)materialToInsert).getModel());
                randomAccessFile.writeUTF(((Audiovisual)materialToInsert).getMaterialCode());
                randomAccessFile.writeInt(((Audiovisual)materialToInsert).getQuantity()); 
                return true;
            }
        }else{
            System.err.println("error -402 invalid position at AudiovisualFile");
            return false;
        }
    }//fin metodo putValue

    @Override
    public Materials getMaterial(int position) throws IOException {
        if(position >= 0 && position <= regsQuantity){
            randomAccessFile.seek(position * regSize);
            Audiovisual audiovisual = new Audiovisual();
            audiovisual.setBrand(randomAccessFile.readUTF());
            audiovisual.setType(randomAccessFile.readUTF());
            audiovisual.setModel(randomAccessFile.readUTF());
            audiovisual.setMaterialCode(randomAccessFile.readUTF());
            audiovisual.setQuantity(randomAccessFile.readInt());
            
            return audiovisual;
        }else{
            System.err.println("error - 402 invalid position");
            return null;
        }
    }   
}
