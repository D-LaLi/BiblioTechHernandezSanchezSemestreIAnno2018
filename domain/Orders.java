
package domain;

import java.io.Serializable;


public class Orders implements Serializable {
    
    private Materials material;
    private Student student;
    private String SDate;
    private String FDate;
    private int charge;

    public Orders() {
        material = null;
        student = new Student();
        SDate = "";
        FDate = "";
        charge = 0;
    }

    public Orders(Materials material, Student student, String SDate, String FDate, int charge) {
        this.material = material;
        this.student = student;
        this.SDate = SDate;
        this.FDate = FDate;
        this.charge = charge;
    }
    //Metodos de acceso
    public Materials getMaterial() {
        return material;
    }

    public void setMaterial(Materials material) {
        this.material = material;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        
    public boolean verifyStudent(Student student) {
        if(this.student.getIdStudent().carne().equals(student.getIdStudent().carne()))
            return true;
        return false;
    }//fin metodo verifyStudent
    
}
