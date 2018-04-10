/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import interfaceGraphics.Window;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author Paula
 */
public class Main extends Application {
    
     Window window;
    
    @Override
    public void start(Stage primaryStage) {
        window = new Window();
        primaryStage.setTitle("Sistema de Bibloteca");
        primaryStage.setScene(window.menu());
        primaryStage.show();     
    }
    
    public static void main(String[] args) {
        launch(args);   
    }
}
