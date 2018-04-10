
package interfaceGraphics;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author ahern
 */
public class InfoInterface {
    
    public GridPane Infomarción(){
        
        GridPane info = new GridPane();
        //info.setGridLinesVisible(true);
        info.setPadding(new Insets(20)); //top, rigth, bottom, left
        info.setMaxWidth(700);
        info.setMaxHeight(380);
        info.setVgap(10);
        info.setHgap(10);
        
        info.setStyle("-fx-alignment: center;" + "-fx-background-color: #a9a9a9;" +"-fx-background-radius: 20;");
        
        Label lb_nombre = new Label("Acerca de");
        //lb_nombre.setTextFill(Color.ALICEBLUE);
        lb_nombre.setFont(new javafx.scene.text.Font("Consolas", 20));
        GridPane.setHalignment(lb_nombre, HPos.CENTER);
        GridPane.setValignment(lb_nombre, VPos.TOP);
        GridPane.setConstraints(lb_nombre, 0, 0, 2, 1);
        
        ImageView iv = new ImageView(new Image("logo.jpeg"));
        iv.setFitWidth(180);
        iv.setFitHeight(160);
        info.add(iv, 0, 1);
        
        Label lb_info = new Label("Este es un sofware que permite llevar los registros de una Bliblioteca. Permite almacenar los datos de los estudiantes"
                + ", los materiales y los pedidos. Esta es una versión de prueba por lo tanto se pueden dar errores de funcionamiento en la aplicación.");
        lb_info.setWrapText(true);
        lb_info.setTextAlignment(TextAlignment.JUSTIFY);
        lb_info.setFont(new javafx.scene.text.Font("Consolas", 16));
        GridPane.setConstraints(lb_info, 1, 1);
        
        Label lb_creditos = new Label("Programadores"+"\n"
                + "Paula Sánchez Campos"+"\n"
                + "Albin Hernández Rivera");
        lb_creditos.setFont(new javafx.scene.text.Font("Consolas", 16));
        lb_creditos.setTextAlignment(TextAlignment.LEFT);
        GridPane.setHalignment(lb_creditos, HPos.LEFT);
        GridPane.setConstraints(lb_creditos, 0, 3, 2, 1);
        
        info.getChildren().addAll( lb_nombre, lb_info, lb_creditos);
        
        return info;
    }
    
}
