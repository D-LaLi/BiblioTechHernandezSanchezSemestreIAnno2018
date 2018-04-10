
package interfaceGraphics;

import domain.Audiovisual;
import files.AudiovisualFile;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class AudioVisualForm {
    
    private TextField [] tf_fields;
    private Label [] lb_tags;
    private Button btn;
    private GridPane gp_1;

    public AudioVisualForm() {
        tf_fields = new TextField[5];
        lb_tags = new Label[5];
        btn = new Button("Insertar");
        gp_1 = new GridPane();
        fieldInitializer();
        tagInitializer();
    }
    
    //se inicializa cada TextField dentro del arreglo
    public void fieldInitializer() {
        for (int i = 0; i < tf_fields.length; i++) {
            tf_fields[i] = new TextField();
        }
    }
    
    //se inicializa cada Lable dentro del arreglo
    public void tagInitializer() {
        for (int i = 0; i < lb_tags.length; i++) {
            lb_tags[i] = new Label();
        }
    }
    
    //se configuran los Labels
    public void setTags() {
        lb_tags[0].setText("Marca:");
        lb_tags[1].setText("Tipo:");
        lb_tags[2].setText("Modelo:");
        lb_tags[3].setText("Color:");
        lb_tags[4].setText("Codigo:");    
    }
    
    //En este metodo se configura en GridPane
    public void setForm() {
        
        setTags();
        setButtonAction();

        //gp_1.setGridLinesVisible(true);
        gp_1.setHgap(10);
        gp_1.setVgap(10);
        gp_1.setStyle("-fx-alignment: center;");
        
        //se agregan los Labels al GridPane
        for (int i = 0; i < lb_tags.length; i++) {
            gp_1.add(lb_tags[i], 1, i);
        }
        
        //se agregan los TextFile al GridPane
        for (int i = 0; i < tf_fields.length; i++) {
            gp_1.add(tf_fields[i], 2, i);
        }
        
        //se agrega el boton
        gp_1.add(btn, 2, 5);  
        GridPane.setHalignment(btn, HPos.RIGHT);
        
        ImageView iv = new ImageView(new Image("icono_resrvaciones.png"));
        iv.setFitWidth(180);
        iv.setFitHeight(180);
        gp_1.add(iv, 0, 0, 1, 6);
        
        gp_1.setStyle("-fx-background-color: #a9a9a9;" +"-fx-background-radius: 20;");
        gp_1.setPadding(new Insets(20));
        gp_1.setMaxWidth(600);
        
    }
    
    //En este metodo se crea el audiovisual y se escribe en el archivo
    public void setAudiovisual() {
        try {
            AudiovisualFile avFile = new AudiovisualFile(new File("./audiovisual.dat"));
            Audiovisual av = new Audiovisual();
            
            //se obtienen los atributos de la clase
            av.setBrand(tf_fields[0].getText());
            av.setType(tf_fields[1].getText());
            av.setModel(tf_fields[2].getText());
            av.setColor(tf_fields[3].getText());
            
            //se valida lo ingrasado en el campo para materialCode
            if(!av.setMaterialCode(tf_fields[4].getText())){
                tf_fields[4].setText("El valor ingresado es inválido");
            } 
            
            avFile.addEndRecord(av);
            
        } catch (IOException ex) {   
            System.err.println("Problems with file at AudioVisualForm | Method: setAudiovisual");
        }     
    }
    
    public void setButtonAction() {
        btn.setOnAction((event) -> {
            setAudiovisual();
            
            //se limpian los campos
            cleanFields();
        });
    }
    
    //metodo que limpia los campos del formulario
    public void cleanFields(){
        for (int i = 0; i < tf_fields.length; i++) {
            tf_fields[i].clear();
        }
    }
    
    //se retorna el formulario ya configurado en el GridPane
    public GridPane getForm() {
        setForm();
        return gp_1;
    }
}
