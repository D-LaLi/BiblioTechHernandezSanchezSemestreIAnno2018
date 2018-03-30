
package domain;


public class Book extends Materials {
    
    private String title;
    private String author;
    private String category;
    private String type;
    private String editorial;

    public Book() {
        title = "";
        author = "";
        category = "";
        type = "";
        editorial = "";
    }

    public Book(String title, String author, String theme, String type, String editorial) {
        this.title = title;
        this.author = author;
        this.category = theme;
        this.type = type;
        this.editorial = editorial;
    }

    // Metodos de acceso
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
     
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    //Este mètodo se encarga de verificar que el codigo ISBN sea mayor de 9 digitos
    //y menor que 14 y que solo se puedan ingresar numeros.
    @Override
    public boolean setMaterialCode(String materialCode) {
        if(materialCode.length() > 9 && materialCode.length() < 14){
            try{
                Long.parseLong(materialCode);
                this.materialCode = materialCode;
                return true;
            }catch(NumberFormatException nfe){
                System.err.println("Invalid ISBN code");
                return false;
            }
        } else 
            return false;
    }//fin metodo setMaterialCode

    @Override
    public String toString() {
        return "Title:\t\t" + title 
                + "\nAuthor:\t\t" + author  
                + "\nTheme\t\t" + category
                + "\nEditorial:\t" + editorial 
                + "\nType:\t\t" + type 
                + "\n"+super.toString();
    }//fin toString

    @Override
    public int sizeInBytes() {
        return super.sizeInBytes() 
                + title.length()*2 
                + author.length()*2 
                + category.length()*2
                + editorial.length()*2 
                + type.length()*2;
    }//fin sizeInBytes 
    
}//fin de la clase books
