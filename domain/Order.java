
package domain;

import java.io.Serializable;


public class Order implements Serializable {
    
    private String materialID;
    private String studentID;
    private String SDate;
    private String FDate;
    private int charge;

    public Order() {
        materialID = "";
        studentID = "";
        SDate = "";
        FDate = "";
        charge = 0;
    }

    public Order(String materialID, String studentID, String SDate, String FDate, int charge) {
        this.materialID = materialID;
        this.studentID = studentID;
        this.SDate = SDate;
        this.FDate = FDate;
        this.charge = charge;
    }
    //Metodos de acceso

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    public String getSDate() {
        return SDate;
    }

    public void setSDate(String SDate) {
        this.SDate = SDate;
    }

    public String getFDate() {
        return FDate;
    }

    public void setFDate(String FDate) {
        this.FDate = FDate;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "Material:\t\t" + materialID
                + "\nStudent:\t\t" + studentID
                + "\nSart Date:\t\t" + SDate 
                + "\nFinal Date:\t" + FDate 
                + "\nCharge:\t\t" + charge;
    }
    
}
