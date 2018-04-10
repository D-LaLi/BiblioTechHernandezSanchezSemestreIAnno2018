/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import domain.Order;
import domain.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class StudentFile {
    
    private String fielPath;

    public StudentFile(String fielPath) {
        super();
        this.fielPath = fielPath;
    }
    
    //Este metodo verifica si el archivo existe o no y llama a los metodos
    //writeInFile y getListOfOrder
    public void insertStudent(Student student_) throws IOException, ClassNotFoundException {
        
        File myFile = new File(fielPath);
        
        ArrayList<Student> list = new ArrayList<>();
        
        if(myFile.exists()){
            list = getListOfStudents();
        }
        
        list.add(student_);
        writeInFile(list);
    }//fin insertStudent
    
    //Este metodo escrie una lista de personas en el archivo
    public void writeInFile(ArrayList<Student> list_) throws IOException, ClassNotFoundException {
        
        File myFile = new File(fielPath);  
        
        //se crea la fuente de salida para escribir el archivo y se escribe la lista en el archivo
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(myFile))) {
            outputStream.writeUnshared(list_);
        } //se cierra el archivo
        
    }//fin writeInFile
    
    //Este metodo lee un archivo y me devuelve una lista de pedidos 
    public ArrayList<Student> getListOfStudents() throws IOException, ClassNotFoundException {
        
        File myFile = new File(fielPath);
        
        ArrayList<Student> list = new ArrayList<>();
        
        //pregunta si el archivo existe
        if(myFile.exists()){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(myFile))) {
                Object tempObject = inputStream.readObject();
                list = (ArrayList<Student>)tempObject;
            }//se cierra el archivo
        }  
        return list;
    }//fin getListOfStudents
    
    
    /* 
    * El siguiente metodo verfica si el estudiante indicado existe. Recibe como parametro 
    * el id del estudiante el cual comparará con el id de los estudiantes que ya se encuentran 
    * registrados en el archivo. Me retorna el estudiante en caso de que este exista de lo 
    * de lo contrario retorna null.
    */
    public Student getStudent(String idStudent_) throws IOException, ClassNotFoundException {
        
        //se crea un ArrayList para almecenar los estudiantes de archivo
        ArrayList<Student> temp_list = getListOfStudents();
        
        //se recorre el ArrayList con los estudiantes
        for (int i = 0; i < temp_list.size(); i++) {
            Student student_temp = temp_list.get(i);
            //se pregunta si el id del estudiante actual conicide con el parametro
            if (student_temp.getIdStudent().equals(idStudent_)) {
                return student_temp;
            }
        }//fin for
        return null;
        
    }//fin getStudent
    
    /*
    *El siguiente metodo retorna la cantidad de estudiantes ingresados en
    *los registros. Esto permite tener un control de cuantos estudiantes estan 
    *registrados.
    */
    public int getLastRegistry() {     
        //se crea un ArrayList para almecenar los estudiantes de archivo
        ArrayList<Student> temp_list;
 
        try {
            //se lee el ArrayList del archivo
            temp_list = getListOfStudents();
            
            //se obtiene el tamaño del ArrayList
            int student_quantity = temp_list.size();
            return student_quantity;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error at StudentFile | Method: getLastRegistry()");
            //en caso de que no exista el archivo se retorna 1
            return 1;
        }
    }//fin getStudent
}//end studentfile
