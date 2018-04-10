package files;

import domain.Order;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Albin Hernández Rivera B68200
 */
public class OrderFile {

    String filePath_;

    public OrderFile(String filePath_) {
        super();
        this.filePath_ = filePath_;
    }

    //Este metodo verifica si el archivo existe o no y llama a los metodos
    //writeInFile y getListOfOrder
    public void fileWriter(Order order_) throws IOException, ClassNotFoundException {
        File myFile = new File(filePath_);

        ArrayList<Order> list = new ArrayList<>();

        if (myFile.exists()) {
            list = getListOfOrders();
        }

        list.add(order_);
        writeInFile(list);
    }//fin fileWriter

    //Este metodo escribe una lista de pedidos en el archivo
    public void writeInFile(ArrayList<Order> list_) throws IOException, ClassNotFoundException {

        File myFile = new File(filePath_);
        //se crea la fuente de salida para escribir el archivo y se escribe la lista en el archivo
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(myFile))) {
            outputStream.writeUnshared(list_);
        } //se cierra el archivo

    }//fin writeInFile

    //Este metodo lee un archivo y me devuelve una lista de pedidos 
    public ArrayList<Order> getListOfOrders() throws IOException, ClassNotFoundException {

        File myFile = new File(filePath_);

        ArrayList<Order> list = new ArrayList<>();

        //pregunta si el archivo existe
        if (myFile.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(myFile))) {
                Object tempObject = inputStream.readObject();
                list = (ArrayList<Order>) tempObject;
            }//se cierra el archivo
        }
        return list;
    }//fin getListOfOrders

    /*
    * El siguiente metodo me devuelve una lista de pedidos. Recibe un String como
    * parametro el cual compara con el id de estudiante de los pedidos registrados
    * me devuelve una lista con todos los pedidos a nombre de ese estudiante
     */
    public ArrayList<Order> getListById(String idStudent) {
        try {
            ArrayList<Order> list = new ArrayList<>();
            for (int i = 0; i < getListOfOrders().size(); i++) {
                //remueve lo que no coincide con el elemento comparado
                if (getListOfOrders().get(i).getStudentID().equals(idStudent)) {
                    list.add(getListOfOrders().get(i));
                }
            }
            return list;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    //Este metodo se encarga de borrar de la lista de ordenes cualquier orden que 
    //coincida con el parametro ingresado.
    public void deleteOrder(Order order_) {
        try {
            //se crea un arrayList Temporal
            ArrayList<Order> list = getListOfOrders();
            for (int i = 0; i < list.size(); i++) {
                //se compara el parametro con las ordenes
                if ((order_.getMaterialID().equals(list.get(i).getMaterialID()))
                        && order_.getStudentID().equals(list.get(i).getStudentID())) {
                    //se remueve del array y se vuelve a escribir
                    list.remove(i);
                    writeInFile(list);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("");
        }
    }

    public void modifyOrder(Order order_) {
        try {
            //se crea un arrayList Temporal
            ArrayList<Order> list = getListOfOrders();
            for (int i = 0; i < list.size(); i++) {
                //se compara el parametro con las ordenes
                if ((order_.getMaterialID().equals(list.get(i).getMaterialID()))
                        && order_.getStudentID().equals(list.get(i).getStudentID())) {
                    //se remueve del array y se vuelve a escribir
                    list.get(i).setCharge(1000);
                    writeInFile(list);
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("");
        }
    }

}//end OrderFile class
