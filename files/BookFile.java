
package files;

import domain.Book;
import domain.Materials;
import java.io.File;
import java.io.IOException;

public class BookFile extends MaterialFile {

    public BookFile(File file) throws IOException {
        super(file);
    }

    @Override
    public boolean putValue(int position, Materials materialToInsert) throws IOException {
        
        //Se hace un casting de la superclase(Materials) a la subclase(Book)
        Book book1 = (Book)materialToInsert;
        
        //Se verifica que la posicion sea correcta
        if(position >= 0 && position <= regsQuantity){
            //Se verifica que el tamaño del archivo sea correcto
            if(book1.sizeInBytes() > regSize){
                System.err.println("error 401 - invalid size at BookFile");
                return false;
            } else{
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(book1.getTitle());
                randomAccessFile.writeUTF(book1.getAuthor());
                randomAccessFile.writeUTF(book1.getCategory());
                randomAccessFile.writeUTF(book1.getType());
                randomAccessFile.writeUTF(book1.getEditorial());
                randomAccessFile.writeUTF(book1.getMaterialCode());
                randomAccessFile.writeInt(book1.getQuantity());
                return true;
            }
        } else{
            System.err.println("error 402 - invalid position at BookFile");
            return false;
        }         
    }//fin metodo putValue

    @Override
    public Materials getMaterial(int position) throws IOException {
        if(position >= 0 && position <= regsQuantity){
            Book book1 = new Book();
            randomAccessFile.seek(position * regSize);
            book1.setTitle(randomAccessFile.readUTF());
            book1.setAuthor(randomAccessFile.readUTF());
            book1.setCategory(randomAccessFile.readUTF());
            book1.setType(randomAccessFile.readUTF());
            book1.setEditorial(randomAccessFile.readUTF());
            book1.setMaterialCode(randomAccessFile.readUTF());
            book1.setQuantity(randomAccessFile.readInt());
            return book1;
        }else{
            System.err.println("error - 402 invalid position");
            return null;
        }
    }//fin metodo getMaterial     
}//fin clase BookFile
