
package interfaceGraphics;

import domain.Material;
import files.AudiovisualFile;
import files.BookFile;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class OrderInterface {
    
    private final ComboBox CB_1;
    private TextField tf_search;//barra de busqueda
    private ObservableList<String> options;//opciones de ComboBox
    private ListView<Material> mat_list;
    private final String ITEM_BOOK;
    private final String ITEM_AV;
    OrderForm of;
    
    public OrderInterface() {
        ITEM_BOOK = "Books";
        ITEM_AV = "Audiovisuals";
        mat_list = new ListView<>();
        options = FXCollections.observableArrayList(ITEM_BOOK, ITEM_AV);
        tf_search = new TextField();
        CB_1 = new ComboBox(options);
        of = new OrderForm();
    }
    
    //Se configuran los diferentes elementos de la interfaz
    public void setFields() {
        tf_search.setPromptText("Search");
        tf_search.setDisable(true);
        tf_search.setPrefWidth(200);
        
        CB_1.setPromptText("Material");
        
    }//fin setFields
    
    public HBox getInterface() {
      
        GridPane gp_1 = new GridPane();
        gp_1.setHgap(10);
        gp_1.setVgap(10);
        //gp_1.setGridLinesVisible(true);
        
        setFields();
        setTextAction();
        setComboAction();
        setListViewAction();
           
        GridPane.setConstraints(CB_1, 0, 0);
        GridPane.setConstraints(tf_search, 1, 0);
        GridPane.setConstraints(mat_list, 0, 1, 3, 1);

        gp_1.getChildren().addAll(CB_1, tf_search, mat_list);
        
        HBox hb_1 = new HBox();
        hb_1.setSpacing(30);
        hb_1.setPadding(new Insets(50));
        hb_1.setAlignment(Pos.CENTER);
          
        hb_1.getChildren().addAll(gp_1, of.getOrderForm());
        
        return hb_1;
    }//fin getInterface
    
    //Metodo para llenar ListView con la lista de libros que coinciden con el codigo
    //titulo de libro ingresado en el TextField
    public void setBookList() {
        try{
            BookFile bookFile = new BookFile(new File("./books.dat"));
            ObservableList<Material> list_1  = 
                    FXCollections.observableArrayList(bookFile.getList(tf_search.getText()));
            mat_list.setItems(list_1);
        }catch(NullPointerException e) {
            mat_list.getItems().clear();
        }catch(IOException ioe){
            System.err.println("Error reading file at OrderInterface");
        }      
    }//fin metodo setBookList
    
    //Metodo para llenar ListView con la lista de audiovisuales que coinciden con el 
    //codigo o tipo ingresado en el TextField
    public void setAvList() {
        try{
            AudiovisualFile avFile = new AudiovisualFile(new File("./audiovisual.dat"));
            ObservableList<Material> list_1  = 
                    FXCollections.observableArrayList(avFile.getList(tf_search.getText()));
            mat_list.setItems(list_1);
        }catch(NullPointerException e) {
            mat_list.getItems().clear();
        }catch(IOException ioe){
            System.err.println("Error reading file at OrderInterface");
        }    
    }//fin metodo setBookList
    
    //Se configura el comportamiento de la barra de busqueda cada vez que se ha
    //presionado una tecla.
    public void setTextAction() {
        tf_search.setOnKeyReleased((event) -> {
            //Se pregunta si lo que se esta buscado es un libro o un audiovisual 
            if(CB_1.getSelectionModel().getSelectedItem().toString().equals(ITEM_BOOK)) 
                setBookList();//se manda a llamar metodo que me retorna una lista con libros
            else if(CB_1.getSelectionModel().getSelectedItem().toString().equals(ITEM_AV))
                setAvList();//se manda a llamar metodo que me retorna una lista con audiovisuales   
        });  
    }//fin setTextAction
    
    //En este metodo se define las acciones que tendra el ComboBox en la interfaz
    public void setComboAction() {
        CB_1.setOnAction((event) -> {
            tf_search.clear();
            //Se pregunta si lo que se busca son libros o audiovisuales, si no se ha 
            //seleccionado una opcion entonces la barra de busqueda permanece deshabilitada
            if(CB_1.getSelectionModel().getSelectedItem().toString().equals(ITEM_BOOK)){
                tf_search.setPromptText("Search by ISBN or by title"); //Se cambia el promptText de la barra de busqueda
                tf_search.setDisable(false); //se habilita la barra de busqueda
                mat_list.getItems().clear();
            } else if(CB_1.getSelectionModel().getSelectedItem().toString().equals(ITEM_AV)){
                tf_search.setPromptText("Search by code or by type"); //Se cambia el promptText de la barra de busqueda
                tf_search.setDisable(false);//se habilita la barra de busqueda
                mat_list.getItems().clear();
            } else {
                tf_search.setDisable(true);//se deshabilita la barra de busqueda
            }
        });
    }//fin setComboAction   

    //Defina las acciones de ListView
    public void setListViewAction() {
       mat_list.setOnMouseClicked((event) -> {
           of.setMat_1(mat_list.getSelectionModel().getSelectedItem());
       });
    }
    
 
}//end class OrderInterface
