package domain;

public class Student {

    private String name;
    private String lastName;
    private IdStudent idStudent;
    private int entryYear;
    private int careerNum;

    //constructores
    public Student(String name, String lastName, int entryYear, int careerNum) {
        this.name = name;
        this.lastName = lastName;
        this.idStudent = new IdStudent(careerNum, entryYear);
        this.entryYear = entryYear;
        this.careerNum = careerNum;
    }

    public Student() {
        this.name = "";
        this.lastName = "";
        this.idStudent = new IdStudent();
        this.entryYear = 0;
        this.careerNum = 0;
    }

    //accesores
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

    public int getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(int entryYear) {
        this.entryYear = entryYear;
    }

    public int getCareerNum() {
        return careerNum;
    }

    public void setCareerNum(int careerNum) {
        this.careerNum = careerNum;
    }

    public IdStudent getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int careerNum, int entryYear) {
        this.idStudent = new IdStudent(careerNum, entryYear);
    }

    public int sizeInBytes() {
        //retornar la suma en btytes de todos los atributos 
        return 8 + this.name.length() * 2 + this.lastName.length() * 2 + idStudent.sizeInBytes();
    }

    @Override
    public String toString() {
        return "Name:\t\t" + name 
                + "\nLastName:\t" + lastName 
                + "\nIdStudent:\t" + idStudent.toString()
                + "\nEntryYear:\t" + entryYear 
                + "\nCareerNum:\t" + careerNum;
    } 
}
