
package files;

import domain.Books;
import domain.Materials;
import java.io.File;
import java.io.IOException;

public class BookFile extends MaterialFiles {

    public BookFile(File file) throws IOException {
        super(file);
    }

    @Override
    public boolean putValue(int position, Materials materialToInsert) throws IOException {
        
        //Se hace un casting de la superclase(Materials) a la subclase(Books)
        Books book1 = (Books)materialToInsert;
        
        //Se verifica que la posicion sea correcta
        if(position >= 0 && position <= regsQuantity){
            //Se verifica que el tamaño del archivo sea correcto
            if(book1.sizeInBytes() > regSize){
                System.err.println("error 401 - invalid size");
                return false;
            } else{
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(book1.getTitle());
                randomAccessFile.writeUTF(book1.getAuthor());
                randomAccessFile.writeUTF(book1.getTheme());
                randomAccessFile.writeUTF(book1.getType());
                randomAccessFile.writeUTF(book1.getEditorial());
                randomAccessFile.writeUTF(book1.getMaterialCode());
                return true;
            }
        } else{
            System.err.println("error 402 - invalid position");
            return false;
        }         
    }//fin metodo putValue
    
}//fin clase BookFile
