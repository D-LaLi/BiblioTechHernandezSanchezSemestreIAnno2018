/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphics;

import domain.Book;
import domain.IdStudent;
import domain.Student;
import files.BookFile;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import files.StudentFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author Paula
 */
public class Modules {

    Student student;
    StudentFile studentFile;
    IdStudent idstudent;
    Label lblinsert;

    public GridPane InsertStudent() throws IOException {
        GridPane gridpane = new GridPane();
        //gridpane.setGridLinesVisible(true);
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);

        gridpane.setStyle("-fx-alignment: center;" + "-fx-background-color: #a9a9a9;" +"-fx-background-radius: 20;");
        gridpane.setPadding(new Insets(20));
        gridpane.setMaxWidth(600);

        studentFile = new StudentFile("./student.dat");

        Label lblInformation = new Label("Datos del estudiante");
        gridpane.add(lblInformation, 1, 0);
        lblInformation.setFont(new Font("arial black", 14));

        Label lblName = new Label("Nombre");
        gridpane.add(lblName, 1, 1);
        lblName.setFont(new Font("arial black", 12));
        
        Label lblLastName = new Label("Apellidos");
        gridpane.add(lblLastName, 1, 2);
        lblLastName.setFont(new Font("arial black", 12));
        
        Label lblEntryYear = new Label("Año de ingreso");
        gridpane.add(lblEntryYear, 1, 3);
        lblEntryYear.setFont(new Font("arial black", 12));
        
        Label lblCareer = new Label("Carrera:");
        gridpane.add(lblCareer, 1, 4);
        lblCareer.setFont(new Font("arial black", 12));
        
        lblinsert = new Label();
        lblinsert.setVisible(false);
        gridpane.add(lblinsert, 1, 8);
        lblinsert.setFont(new Font("arial black", 12));

        TextField txtName = new TextField();
        gridpane.add(txtName, 2, 1);
        
        TextField txtLastName = new TextField();
        gridpane.add(txtLastName, 2, 2);
        
        TextField txtEntryYear = new TextField();
        gridpane.add(txtEntryYear, 2, 3);

        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Informática");
        gridpane.add(rb1, 2, 4);
        rb1.setToggleGroup(group);

        RadioButton rb2 = new RadioButton("Agronomía");
        gridpane.add(rb2, 2, 5);
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("Educación");
        gridpane.add(rb3, 2, 6);
        rb3.setToggleGroup(group);

        Button btnInsert = new Button();
        gridpane.add(btnInsert, 2, 7);
        btnInsert.setText("Insert");

//        InputStream registrar = getClass().getResourceAsStream("/imagenes/registrar.png");
//        Image imageReg = new Image(registrar);
        //btnInsert.setGraphic(new ImageView(imageReg));
        //btnInsert.setStyle("-fx-background-color:WHITE");
        btnInsert.setMinWidth(90);
        
        Tooltip tool = new Tooltip();
        tool.setText("Registar");
        
        btnInsert.setTooltip(tool);
        btnInsert.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnInsert.setCursor(Cursor.HAND);
            }
        });

        btnInsert.setOnAction((event) -> {
            if (txtName.getText().equals("")) {
                txtName.setPromptText("OBLIGATORIO LLENAR");
            } else if (txtLastName.getText().equals("")) {
                txtLastName.setPromptText("OBLIGATORIO LLENAR");
            } else if (txtEntryYear.getText().equals("")) {
                txtEntryYear.setPromptText("OBLIGATORIO LLENAR");
            } else if (group.getToggles().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error");
            } else {
                if (rb1.isSelected()) {
                    student = new Student(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtEntryYear.getText()), 1);
                    try {
                        studentFile.insertStudent(student);
                        lblinsert.setText("El estudiante ha sido ingresado con exito");
                        lblinsert.setVisible(true);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (rb2.isSelected()) {
                    student = new Student(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtEntryYear.getText()), 2);
                    try {
                        studentFile.insertStudent(student);
                        lblinsert.setText("El estudiante ha sido ingresado con exito");
                        lblinsert.setVisible(true);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    student = new Student(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtEntryYear.getText()), 3);
                    try {
                        studentFile.insertStudent(student);
                        lblinsert.setText("El estudiante ha sido ingresado con exito");
                        lblinsert.setVisible(true);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }//fin else
            }
        });

        Button btnclean = new Button();
        InputStream clean = getClass().getResourceAsStream("/imagenes/clean.png");
        Image imageClean = new Image(clean);
        btnclean.setGraphic(new ImageView(imageClean));
        btnclean.setStyle("-fx-background-color:WHITE");
        gridpane.add(btnclean, 1, 7);
        btnclean.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnclean.setCursor(Cursor.HAND);
            }
        });

        btnclean.setOnAction((event) -> {
            txtName.setText("");
            txtLastName.setText("");
            txtEntryYear.setText("");
            rb1.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
        });

        return gridpane;
    }//end insertstudent

    public GridPane StudentList() {
        GridPane gridpane = new GridPane();
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);

        Label lblList = new Label("Lista de estudiantes registrados");
        gridpane.add(lblList, 0, 2);

        return gridpane;
    }//end studentlist

    public GridPane InsertBook() {
        
        GridPane gridpane = new GridPane();
        //gridpane.setGridLinesVisible(true);
        gridpane.setHgap(10);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);
        gridpane.setStyle("-fx-alignment: center;" + "-fx-background-color: #a9a9a9;" +"-fx-background-radius: 20;");
        gridpane.setPadding(new Insets(20));
        gridpane.setMaxWidth(600);
        
        ImageView iv = new ImageView(new Image("icono_resrvaciones.png"));
        iv.setFitWidth(180);
        iv.setFitHeight(180);
        gridpane.add(iv, 0, 0, 1, 10);

        Label lblinformation = new Label("Información del libro");
        gridpane.add(lblinformation, 1, 0, 2, 1);
        lblinformation.setFont(new Font("arial black", 14));

        Label lbltitle = new Label("Título del libro");
        lbltitle.setFont(new Font("arial black", 12));
        gridpane.add(lbltitle, 1, 1);
        
        Label lblauthor = new Label("Autor");
        lblauthor.setFont(new Font("arial black", 12));
        gridpane.add(lblauthor, 1, 2);
        
        Label lbltheme = new Label("Tema/categoría");
        lbltheme.setFont(new Font("arial black", 12));
        gridpane.add(lbltheme, 1, 3);
        
        Label lbltype = new Label("Tipo");
        lbltype.setFont(new Font("arial black", 12));
        gridpane.add(lbltype, 1, 4);
        
        Label lbleditorial = new Label("Editorial");
        lbleditorial.setFont(new Font("arial black", 12));
        gridpane.add(lbleditorial, 1, 7);
        
        Label lbISBN = new Label("ISBN");
        lbISBN.setFont(new Font("arial black", 12));
        gridpane.add(lbISBN, 1, 8);
        

        TextField txtTitle = new TextField();
        gridpane.add(txtTitle, 2, 1);
        
        TextField txtauthor = new TextField();
        gridpane.add(txtauthor, 2, 2);
        
        TextField txtTheme = new TextField();
        gridpane.add(txtTheme, 2, 3);
        
//        TextField txtdigitalQuantity = new TextField();
//        gridpane.add(txtdigitalQuantity, 4, 15);   
//        txtdigitalQuantity.setVisible(false);
        
//        TextField txtphysicalQuantity = new TextField();
//        gridpane.add(txtphysicalQuantity, 4, 16);
//        txtphysicalQuantity.setVisible(false);
        
        TextField txteditorial = new TextField();
        gridpane.add(txteditorial, 2, 7);
        
        TextField txtISBN = new TextField();
        gridpane.add(txtISBN, 2, 8);

        RadioButton rbdigital = new RadioButton("Digital");
        gridpane.add(rbdigital, 2, 4);
        InputStream digital = getClass().getResourceAsStream("/imagenes/pdf.png");
        Image pdf = new Image(digital);
        rbdigital.setGraphic(new ImageView(pdf));

        RadioButton rbphysical = new RadioButton("Físico");
        gridpane.add(rbphysical, 2, 5);
        InputStream pshysical = getClass().getResourceAsStream("/imagenes/libros.png");
        Image books = new Image(pshysical);
        rbphysical.setGraphic(new ImageView(books));

//        CheckBox chb = new CheckBox("Cantidad de ejemplares");
//        gridpane.add(chb, 4, 14);

//        chb.setOnMousePressed((event) -> {
//
//            txtdigitalQuantity.setVisible(true);
//            txtphysicalQuantity.setVisible(true);
//
//        });

        Button btn = new Button("Registrar");
        gridpane.add(btn, 2, 9);
        btn.setOnAction((event) -> {
            if (rbdigital.isSelected()) {
                try {
                    BookFile bf = new  BookFile(new File("./books.dat"));
                    Book book = new Book();
                    book.setTitle(txtTitle.getText());
                    book.setAuthor(txtauthor.getText());
                    book.setCategory(txtTheme.getText());
                    book.setType("Digital");
//                    if (!txtdigitalQuantity.getText().equals("")) {
//                        book.setQuantity(Integer.parseInt(txtdigitalQuantity.getText()));
//                    }
                    book.setEditorial(txteditorial.getText());
                    book.setMaterialCode(txtISBN.getText());
                    bf.addEndRecord(book);
                    
                    //se limpian los campos
                    txtTitle.clear();
                    txtISBN.clear();
                    txtTheme.clear();
                    txtauthor.clear();
                    txteditorial.clear();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }else if(rbphysical.isSelected()){
                try {
                    BookFile bf = new  BookFile(new File("./books.dat"));
                    Book book = new Book();
                    book.setTitle(txtTitle.getText());
                    book.setAuthor(txtauthor.getText());
                    book.setCategory(txtTheme.getText());
                    book.setType("Fisicio");
//                    if (!txtdigitalQuantity.getText().equals("")) {
//                        book.setQuantity(Integer.parseInt(txtdigitalQuantity.getText()));
//                    }
                    book.setEditorial(txteditorial.getText());
                    book.setMaterialCode(txtISBN.getText());
                    bf.addEndRecord(book);
                    
                    //se limpian los campos
                    txtTitle.clear();
                    txtISBN.clear();
                    txtTheme.clear();
                    txtauthor.clear();
                    txteditorial.clear();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Modules.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });

        return gridpane;
    }//end loandsBook

    public GridPane loansBooks() {
        
        GridPane gridpane = new GridPane();
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);
        gridpane.setStyle("-fx-background-image: url(\"fondo.jpg\");" + "-fx-background-size: stretch;");

        Label lblsearchStudent = new Label("Ingrese el nombre del estudiante");
        lblsearchStudent.setFont(new Font("arial black", 12));
        gridpane.add(lblsearchStudent, 0, 2);
        Label lblname = new Label("Nombre el estudiante");
        lblname.setFont(new Font("arial black", 12));
        gridpane.add(lblname, 0, 5);
        Label lblid = new Label("Carné estudiantil");
        lblid.setFont(new Font("arial black", 12));
        gridpane.add(lblid, 0, 7);
        Label lblsearch = new Label("Búsqueda del artículo");
        lblsearch.setFont(new Font("arial black", 12));
        gridpane.add(lblsearch, 0, 9);
        Label lblcode = new Label("Código artículo");
        lblcode.setFont(new Font("arial black", 12));
        gridpane.add(lblcode, 0, 11);
        Label lblreDate = new Label("Fecha de retiro");
        lblreDate.setFont(new Font("arial black", 12));
        gridpane.add(lblreDate, 0, 13);
        Label lblLastday = new Label("Fecha máxima de devolución");
        lblLastday.setFont(new Font("arial black", 12));
        gridpane.add(lblLastday, 0, 15);

        DatePicker dploans = new DatePicker();
        dploans.setEditable(false);
        gridpane.add(dploans, 3, 13);

        DatePicker dpreturn = new DatePicker();
        dpreturn.setEditable(false);
        gridpane.add(dpreturn, 3, 15);

        TextField txtsearchStudent = new TextField();
        gridpane.add(txtsearchStudent, 3, 2);
        TextField txtname = new TextField();
        gridpane.add(txtname, 3, 5);
        TextField txtid = new TextField();
        gridpane.add(txtid, 3, 7);
        TextField txtsearch = new TextField();
        gridpane.add(txtsearch, 3, 9);
        TextField txtcode = new TextField();
        gridpane.add(txtcode, 3, 11);

        Button btninformation = new Button();
        gridpane.add(btninformation, 4, 9);
        InputStream information = getClass().getResourceAsStream("/imagenes/information.png");
        Image imageInformation = new Image(information);
        btninformation.setGraphic(new ImageView(imageInformation));
        btninformation.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btninformation.setCursor(Cursor.HAND);
            }
        });
        btninformation.setOnAction((event) -> {
            JOptionPane.showMessageDialog(null, "Búsqueda de libro: ISBN ó inciales.\nBúsqueda de audiovisual: código ó inciales.");
        });

        Button btnverify = new Button();
        gridpane.add(btnverify, 4, 2);
        InputStream search = getClass().getResourceAsStream("/imagenes/search.png");
        Image imageSearch = new Image(search);
        btnverify.setGraphic(new ImageView(imageSearch));
        btnverify.setStyle("-fx-background-color:WHITE");
        btnverify.setMinWidth(50);
        Tooltip tool = new Tooltip();
        tool.setText("Buscar estudiante");
        btnverify.setTooltip(tool);
        btnverify.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnverify.setCursor(Cursor.HAND);
            }
        });

        Button btndone = new Button("Realizar préstamo");
        gridpane.add(btndone, 3, 17);

        return gridpane;
    }//end loansbooks

    public GridPane InsertAudiovisual() {
        GridPane gridpane = new GridPane();
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);
        gridpane.setStyle("-fx-background-image: url(\"fondo.jpg\");" + "-fx-background-size: stretch;");

        Label lblinformation = new Label("Material audiovisual");
        lblinformation.setFont(new Font("arial black", 14));
        gridpane.add(lblinformation, 0, 2);
        Label lblbrand = new Label("Marca");
        lblbrand.setFont(new Font("arial black", 12));
        gridpane.add(lblbrand, 0, 3);
        Label lbltype = new Label("Tipo");
        lbltype.setFont(new Font("arial black", 12));
        gridpane.add(lbltype, 0, 5);
        Label lblmodel = new Label("Modelo");
        lblmodel.setFont(new Font("arial black", 12));
        gridpane.add(lblmodel, 0, 7);

        TextField txtbrand = new TextField();
        gridpane.add(txtbrand, 3, 3);
        TextField txtType = new TextField();
        gridpane.add(txtType, 3, 5);
        TextField txtmodel = new TextField();
        gridpane.add(txtmodel, 3, 7);

        Button btnInsert = new Button("Registrar");
        btnInsert.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnInsert.setCursor(Cursor.HAND);
            }
        });

        return gridpane;
    }//end Insertaudiovisual
        
}
