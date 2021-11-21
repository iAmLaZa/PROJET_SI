package classes;

public class examen {
    private String matricule;
    private String code_m;
    private double note;

    public examen(String matricule, String code_m, double note) {
        this.matricule = matricule;
        this.code_m = code_m;
        this.note = note;
    }

    public examen() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCode_m() {
        return code_m;
    }

    public void setCode_m(String code_m) {
        this.code_m = code_m;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
