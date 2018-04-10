/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphics;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class Window {

    VBox vbox = new VBox();
    Modules modules;

    public Scene menu() {
        modules = new Modules();
        Scene scene = new Scene(new VBox(), 850, 500);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        vbox.setPrefSize(scene.getWidth(), scene.getWidth());
        vbox.setStyle("-fx-background-image: url(\"fondo2.jpg\");" + "-fx-background-size: stretch;");
        
        InfoInterface info = new InfoInterface();
        vbox.getChildren().add(info.Infomarción());
        
        MenuBar menubar = new MenuBar();

        //MenuBar System
        Menu regMaterial = new Menu("Materiales");
        MenuItem regBook = new MenuItem("Registrar un libro", new ImageView(new Image("books.png")));
        regBook.setOnAction((event) -> {
            vbox.getChildren().clear();
            vbox.getChildren().addAll(modules.InsertBook());

        });
        
        MenuItem regAudiovisual = new MenuItem("Registar material audiovisual", new ImageView(new Image("audiovisual.png")));
        regAudiovisual.setOnAction((event) -> {
            vbox.getChildren().clear();
            AudioVisualForm avForm = new AudioVisualForm();
            vbox.getChildren().add(avForm.getForm());   
        });

        Menu student = new Menu("Estudiantes");
        MenuItem regStudent = new MenuItem("Registrar un estudiante", new ImageView(new Image("estudiantes.png")));
        regStudent.setOnAction((event) -> {
            vbox.getChildren().clear();//try catch lo agrego por defecto
            try {
                vbox.getChildren().addAll(modules.InsertStudent());
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //MenuItem studentList = new MenuItem("Listado de estudiantes");

        //prestamo
        Menu loans = new Menu("Préstamos");
        MenuItem loansBook = new MenuItem("Préstamo de Materiales", new ImageView(new Image("booksloans.png")));
        loansBook.setOnAction((event) -> {
            vbox.getChildren().clear();
            OrderInterface oi = new OrderInterface();
            vbox.getChildren().add(oi.getInterface());
        });
       
        MenuItem refund = new MenuItem("Devoluciones", new ImageView(new Image("devolucion.png")));
        refund.setOnAction((event) -> {
            vbox.getChildren().clear();
            DevolutionInterface dvi = new DevolutionInterface();
            vbox.getChildren().add(dvi.setOrderInterface());
        });

        regMaterial.getItems().addAll(regBook, regAudiovisual);
        student.getItems().addAll(regStudent);
        loans.getItems().addAll(loansBook, refund);
        menubar.getMenus().addAll(regMaterial, student, loans);

        ((VBox) scene.getRoot()).getChildren().addAll(menubar, vbox);
        return scene;
    }//end menu
}
