package classes;

import interfaces.Isection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class section implements Isection {
    private static Connection con = BD.connect();
    private String code_s;
    private String libelle,specialite;

    public section(String code_s, String libelle, String specialite) {
        this.code_s = code_s;
        this.libelle = libelle;
        this.specialite = specialite;
    }

    public section(String code_s) {
        this.code_s = code_s;
    }

    public section() {
    }

    public String getCode_s() {
        return code_s;
    }

    public void setCode_s(String code_s) {
        this.code_s = code_s;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public ObservableList<String> namesection() throws SQLException {
        ObservableList<String> list= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select code_s from section");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            list.add(rs.getString("code_s"));
        return list;
    }

    @Override
    public section recherchesection(String code_s)  {
        PreparedStatement pr= null;
        try {
            pr = con.prepareStatement("select * from section where code_s='"+code_s+"'");
            ResultSet rs=pr.executeQuery();
            if(rs.next())
                return new section(code_s,rs.getString("libelle"),rs.getString("specialite"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addsection() {
        try {
            PreparedStatement pr=con.prepareStatement("insert into section values('"+getCode_s()+"','"+getLibelle()+"','"+getSpecialite()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deletesection(String code_s) throws SQLException {

        PreparedStatement pr = con.prepareStatement("delete from section where code_s='" + code_s + "'");
        return !pr.execute();
    }
}
