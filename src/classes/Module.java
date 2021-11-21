package classes;

import interfaces.Imodule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Module implements Imodule {
    private static Connection con = BD.connect();
    private String code_m;
    private String libelle;
    private int coef;
    private String code_ens;

    public Module(String code_m, String libelle, int coef, String code_ens) {
        this.code_m = code_m;
        this.libelle = libelle;
        this.coef = coef;
        this.code_ens = code_ens;
    }


    public Module() {
    }

    public String getCode_m() {
        return code_m;
    }

    public void setCode_m(String code_m) {
        this.code_m = code_m;
    }

    public void setCode_ens(String code_ens) {
        this.code_ens = code_ens;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public String getCode_ens() {
        return code_ens;
    }


    @Override
    public ObservableList<Module> listmodule() throws SQLException {
        ObservableList<Module> list= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from module");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            list.add(new Module(rs.getString("code_m"),rs.getString("libelle"),
                    rs.getInt("coef"),rs.getString("code_ens")));
        return list;
    }

    @Override
    public boolean ajoutermodule()  {
        try {
            PreparedStatement pr=con.prepareStatement("insert into module values('"+getCode_m()
                    +"','"+getLibelle()+"',"+getCoef()+",'"+getCode_ens()+"')");
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }


    @Override
    public boolean updatemodule() throws SQLException {
        PreparedStatement pr=con.prepareStatement("update module set libelle='"+getLibelle()+
                "',coef="+getCoef()+",code_ens='"+getCode_ens()+"' where code_m='"+getCode_m()+"'");

        return !pr.execute();
    }

    @Override
    public boolean deletemodule(String codem0) throws SQLException {
        PreparedStatement pr=con.prepareStatement("delete from module where code_m='"+codem0+"'");
        return !pr.execute();
    }
}
