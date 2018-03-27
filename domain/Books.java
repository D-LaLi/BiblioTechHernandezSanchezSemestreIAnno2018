
package domain;


public class Books extends Materials {
    
    private String title;
    private String author;
    private String theme;
    private String type;
    private String editorial;

    public Books(String title, String author, String theme, String type, String editorial) {
        this.title = title;
        this.author = author;
        this.theme = theme;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
    
    @Override
    public boolean setCode(String materialCode) {
        if(materialCode.length() > 9 && materialCode.length() < 14){
            this.materialCode = materialCode;
            return true;
        } else 
            return false;
    }//fin metodo setCode

    @Override
    public String toString() {
        return "Title:\t\t" + title 
                + "\nAuthor:\t\t" + author  
                + "\nTheme\t\t" + theme
                + "\nEditorial:\t" + editorial 
                + "\nType:\t\t" + type 
                + "\n"+super.toString();
    }//fin toString

    @Override
    public int sizeInBytes() {
        return super.sizeInBytes() 
                + title.length()*2 
                + author.length()*2 
                + theme.length()*2
                + editorial.length()*2 
                + type.length()*2;
    }//fin sizeInBytes 
    
}//fin de la clase books
