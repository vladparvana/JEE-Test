package beans;
import java.util.Objects;

public class StudentBean implements java.io.Serializable {
    private String nume = null;
    private String prenume = null;
    private int varsta = 0;
    private int id = 0;
    private int anStudii = 0;
    private String facultate = null;

    public StudentBean() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getFacultate(){
        return this.facultate;
    }

    public void setAnStudii(int anStudii){
        this.anStudii= anStudii;
    }

    public int getAnStudii(){
        return anStudii;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Reflexivitate
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StudentBean other = (StudentBean) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}