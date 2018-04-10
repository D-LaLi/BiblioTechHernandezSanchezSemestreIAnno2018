package interfaceGraphics;

import domain.Audiovisual;
import domain.Book;
import domain.Devoluciones;
import domain.Material;
import domain.Order;
import files.AudiovisualFile;
import files.BookFile;
import files.OrderFile;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class DevolutionInterface {

    Label lb_1;
    TextField tf_1;
    ListView<Order> order_list;
    Button btn_1;
    Button btn_2;
    GridPane gp_1;
    private final String PATH;

    //Constructor
    public DevolutionInterface() {
        PATH = "./orders.dat";
        lb_1 = new Label("Carnet del estudiante: ");
        tf_1 = new TextField();
        order_list = new ListView<>();
        btn_1 = new Button("Devolver");
        btn_2 = new Button("Buscar");
        gp_1 = new GridPane();
    }

    //Se configura el ListView
    public void setFieldsSize() {
        //tamaño de ListView
        order_list.setPrefSize(400, 200);

        //tamaño de TextField
        tf_1.setMaxWidth(150);
    }

    //Se definen las acciones de los botones
    public void setBtn_2Action() {
        btn_2.setOnAction((event) -> {
            OrderFile oFile = new OrderFile(PATH);
            ObservableList<Order> list
                    = FXCollections.observableArrayList(oFile.getListById(tf_1.getText()));
            order_list.setItems(list);
        });
    }//fin setTextAction

    public void setBtn_1Action() {
        btn_1.setOnAction((event) -> {           
            try {  
                //se verifica si tiene multa
                Devoluciones dv = new Devoluciones();
                String fecha = order_list.getSelectionModel().getSelectedItem().getFDate();
                int penaltyAmount = dv.penalty(fecha);   
                if(penaltyAmount == 1000){
                    OrderFile of = new OrderFile(PATH);
                    of.modifyOrder(order_list.getSelectionModel().getSelectedItem());
                }else{
                    Action_1();
                    OrderFile of = new OrderFile(PATH);
                    of.deleteOrder(order_list.getSelectionModel().getSelectedItem());
                }
            } catch (ParseException ex) {
                System.err.println("String conversion problem at DevolutionInterface | Method: setBtn_1Action");
            }
        });
    }//fin setBtn_1Action
    
    
    //Metodo para forzar que la catidad de materiales de un mismo tipo vuelva a su
    //estado originar despues de hacer una devolucion
    public void Action_1() {
        //Obtiene el id del item seleccionado
        String materialCode = order_list.getSelectionModel().getSelectedItem().getMaterialID();
        if(materialCode.length() > 5){
            try {
                Book bk_1 = new Book("", "", "", "");
                bk_1.setMaterialCode(materialCode);
                bk_1.setQuantity(1);
                BookFile bkFile = new BookFile(new File("./books.dat"));
                bkFile.addEndRecord(bk_1);
            } catch (IOException ex) {}
        }else if(materialCode.length() < 6){
            try {
                Audiovisual av = new Audiovisual("", "", "", "");
                AudiovisualFile avFile = new AudiovisualFile(new File("./audiovisual.dat"));
                av.setQuantity(1);
                av.setMaterialCode(materialCode);
                avFile.addEndRecord(av);
            } catch (IOException ex) {}
        }
    }//fin Action_1

    //se configura el GridPane con todos los campos
    public StackPane setOrderInterface() {

        setFieldsSize();
        setBtn_2Action();
        setBtn_1Action();

        //gp_1.setGridLinesVisible(true);
        gp_1.setStyle("-fx-alignment: center;");
        gp_1.setHgap(10);
        gp_1.setVgap(10);

        //se agrega el label
        gp_1.add(lb_1, 1, 0);

        //se agrega el textField
        gp_1.add(tf_1, 2, 0);

        //se agrega el ListView
        gp_1.add(order_list, 1, 1, 3, 1);

        //se agrega el boton
        gp_1.add(btn_1, 2, 2, 2, 1);
        GridPane.setHalignment(btn_1, HPos.RIGHT);

        gp_1.add(btn_2, 3, 0);

        StackPane sp = new StackPane(gp_1);
        sp.setStyle("-fx-background-color: #a9a9a9;" +"-fx-background-radius: 20;");
        sp.setPadding(new Insets(20));
        sp.setMaxWidth(600);
        
        ImageView iv = new ImageView(new Image("icono_actualizar.png"));
        iv.setFitWidth(180);
        iv.setFitHeight(180);
        gp_1.add(iv, 0, 0, 1, 3);

        return sp;
    }//fin setOrderInterface    
}
