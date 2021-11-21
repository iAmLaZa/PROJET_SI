package classes;

import interfaces.Iens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class enseignant implements Iens {
    private static Connection con = BD.connect();
    private String code_ens;
    private String nom_ens,prenom_ens,grade;

    public enseignant(String code_ens, String nom_ens, String prenom_ens, String grade) {
        this.code_ens = code_ens;
        this.nom_ens = nom_ens;
        this.prenom_ens = prenom_ens;
        this.grade = grade;
    }

    public enseignant() {
    }

    public String getCode_ens() {
        return code_ens;
    }

    public void setCode_ens(String code_ens) {
        this.code_ens = code_ens;
    }

    public String getNom_ens() {
        return nom_ens;
    }

    public void setNom_ens(String nom_ens) {
        this.nom_ens = nom_ens;
    }

    public String getPrenom_ens() {
        return prenom_ens;
    }

    public void setPrenom_ens(String prenom_ens) {
        this.prenom_ens = prenom_ens;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public ObservableList<String> codeens() throws SQLException {
        ObservableList<String> list= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select code_ens from enseignant");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            list.add(rs.getString("code_ens"));
        return list;
    }

    @Override
    public enseignant rechercheens(String code_ens) throws SQLException {
        PreparedStatement pr=con.prepareStatement("select * from enseignant where code_ens='"+code_ens+"'");
        ResultSet rs=pr.executeQuery();
        if(rs.next())
            return (new enseignant(rs.getString("code_ens"),
                    rs.getString("nom_ens"),rs.getString("prenom_ens"),rs.getString("grade")));
        return null;
    }

    @Override
    public boolean addens() {

        try{
            PreparedStatement pr=con.prepareStatement("insert into enseignant values ('"+getCode_ens()+"','"+getNom_ens()+"','"+getPrenom_ens()
                    +"','"+getGrade()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateens() {
        try {
            PreparedStatement pr = con.prepareStatement("update enseignant set grade='" + getGrade() + "'");
            return !pr.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteens(String code_ens) throws SQLException {
        PreparedStatement pr=con.prepareStatement("delete from enseignant where code_ens='"+code_ens+"'");
        return !pr.execute();
    }
}
