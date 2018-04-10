
package interfaceGraphics;

import domain.Book;
import domain.Material;
import domain.Order;
import domain.Student;
import files.AudiovisualFile;
import files.BookFile;
import files.OrderFile;
import files.StudentFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;



public class OrderForm {
    
    private Label [] lb_tags;
    private TextField [] tf_fields;
    private Button btn_reg;
    private DatePicker dp_1;
    private DatePicker dp_2;
    private final int CHARGE;
    private Material mat_1;
    private final String PATH;

    public OrderForm() {
        PATH = "./orders.dat";
        CHARGE = 0; 
        lb_tags = new Label[5];
        tf_fields = new TextField[3];
        btn_reg = new Button("Registrar");
        dp_1 = new DatePicker(LocalDate.now());
        dp_2 = new DatePicker();
        
        for (int i = 0; i < lb_tags.length; i++) {
            lb_tags[i] = new Label();
        }
        
        for (int i = 0; i < tf_fields.length; i++) {
            tf_fields[i] = new TextField();
        }
    }//fin constructor

    
    public Material setMat_1(Material mat_1) {
        this.mat_1 = mat_1;
        tf_fields[2].setText(mat_1.getMaterialCode());
        return mat_1;
    }
    
    //Me retorna un GridPane
    public GridPane getOrderForm() {
        
        GridPane gp_1 = new GridPane();
        gp_1.setVgap(10);
        gp_1.setHgap(10);
        gp_1.setStyle("-fx-alignment: center;");
        
        setLabels();
        setTextFields();
        setButtonAction();
        setTextFiledAction();
        
        GridPane.setConstraints(dp_1, 1, 3); //campo fecha inicial
        GridPane.setConstraints(dp_2, 1, 4); //campo fecha final
        
        GridPane.setConstraints(tf_fields[0], 1, 0); //campo para carnet
        GridPane.setConstraints(tf_fields[1], 1, 1); //campo para nombre
        GridPane.setConstraints(tf_fields[2], 1, 2); //campo para codigo/ISBN
        
        GridPane.setConstraints(btn_reg, 1, 5, 1, 1, HPos.RIGHT, VPos.BASELINE);

        for (int i = 0; i < lb_tags.length; i++) {
            GridPane.setConstraints(lb_tags[i], 0, i);
            gp_1.getChildren().add(lb_tags[i]);
        }
             
        gp_1.getChildren().addAll(dp_1, dp_2, tf_fields[0], tf_fields[1], tf_fields[2], btn_reg);
        
        return gp_1;
    }   
    
    //Se configuran los Labels
    public void setLabels() {
        lb_tags[0].setText("Estudiante(Carnet):");
        lb_tags[1].setText("Nombre:");
        lb_tags[2].setText("Codigo/ISBN:");
        lb_tags[3].setText("Fecha de prestamo:");
        lb_tags[4].setText("Fecha de devolucion:");
    }
    
    public void setTextFields() {
        tf_fields[0].setEditable(true);
        tf_fields[1].setEditable(false);
        tf_fields[2].setEditable(false);       
        
        //DatePicker
        dp_1.setEditable(false);
        dp_2.setEditable(false);
        
        //los campos inician deshabilitados
        enableFields(true);      
    }
    
    //se definen las acciones de los TextField
    public void setTextFiledAction() {
        tf_fields[0].setOnKeyReleased((event) -> {
            try {
                //se llama la clase StudentFile y Student
                StudentFile sf = new StudentFile("./student.dat");
                
                //se verifica el id ingresado en el campo
                Student student = sf.getStudent(tf_fields[0].getText());
                tf_fields[1].setText(student.getName() + " " + student.getLastName());
         
                //se habilitan los campos
                enableFields(false);
                
            }catch(IOException | ClassNotFoundException e){
                System.err.println("Error at OrderForm | Method: setTextFieldAction");
            }catch(NullPointerException npe){ // <- si retorna null el estudiante no existe
                //se deshabilitan los campos
                enableFields(true);
            }
        });
    } 
    
    //Este metodo controla la cantidad de materiales de un mismi tipo disponibles que hay
    //de manera que si se realiza un pedido esta cantidad disminuye
    public void quantityControl() {
        
        //Se resta el material
        int realQuantity = -1;
        mat_1.setQuantity(realQuantity);
        
        //se pregunta si es un libro o un audiovisual y se escribe el material en el archivo    
        if(mat_1.getMaterialCode().length() > 5){
            try {
                BookFile bf = new BookFile(new File("./books.dat"));
                bf.addEndRecord(mat_1);
            } catch (IOException ex) { }
        } else {
            try {
                AudiovisualFile avFile = new AudiovisualFile(new File("./audiovisual.dat"));
                avFile.addEndRecord(mat_1);
            } catch (IOException ex) { }
        }
    }//fin quantityControl
    
    //Metodo que determina acciones de btn_reg
    public void setButtonAction() {
        btn_reg.setOnAction((event) -> {
            //se registra el pedido
            setOrder();
            
            //se limpian los campos
            cleanFields();
            
            //disminuye la cantidad del material
            quantityControl();
            
            //se bloquean los campos nuevamente
            enableFields(true);
        });
    }
    
    //Este metodo crea un pedido(Order) y manda a llamar los metodos de almacenamiento
    //que guardan dicho pedido en el archivo
    public void setOrder() {
        //se crea un nuevo pedido
        Order order = new Order();
        OrderFile orderFile = new OrderFile(PATH);
            
        //se llenan los atributos del pedido
        order.setMaterialID(tf_fields[2].getText());
        order.setStudentID(tf_fields[0].getText());
            
        //se convierte el valor de DatePicker en String con formato yyyy-MM-dd
        order.setSDate(dp_1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        order.setFDate(dp_2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            
        //el cargo en un inicio siempre es 0
        order.setCharge(CHARGE);
            
        try {
        //se guarda el pedido en el archivo
            orderFile.fileWriter(order);
        } catch (IOException ex) {
            System.err.println("Error - at writing file at OrderForm");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error - at with class at OrderForm");
        } 
    }//fin setOrder
      
    //Este metodo limpia todo los campos del formulario
    public void cleanFields() {
        tf_fields[0].clear();
        tf_fields[1].clear();
        tf_fields[2].clear();
        
        dp_1.setValue(LocalDate.now());
        dp_2.setValue(null);   
    }//fin cleanFields
    
    public void enableFields(boolean state) {
        tf_fields[1].setDisable(state);
        tf_fields[2].setDisable(state);
        
        dp_1.setDisable(state);
        dp_2.setDisable(state);
    }
    
}//fin clase OrderForm
