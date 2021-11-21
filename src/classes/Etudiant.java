package classes;

import interfaces.Ietd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Etudiant implements Ietd {

    private static Connection con = BD.connect();

    private String matricule;
    private String nom,prenom;
    private int groupe;
    private String secion;

    public Etudiant(String matricule, String nom, String prenom, int groupe, String secion) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.groupe = groupe;
        this.secion = secion;
    }

    public Etudiant() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }

    public String getSecion() {
        return secion;
    }

    public void setSecion(String secion) {
        this.secion = secion;
    }

    @Override
    public boolean ajouteretudiant() {
        try{
            PreparedStatement pr=con.prepareStatement("insert into etudiant values ('"+getMatricule()+"','"
                    +getNom()+"','"+getPrenom()
                    +"',"+getGroupe()+",'"+getSecion()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    @Override
    public boolean updatetd()  {
        try {
            PreparedStatement pr = con.prepareStatement("update etudiant set nom='"+getNom()+"',prenom='"
                    +getPrenom()+"',section_etd='" + getSecion() +
                    "',groupe=" + getGroupe() + " where matricule='" + getMatricule() + "'");
            return !pr.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteetd(String mat) throws SQLException {
        PreparedStatement pr=con.prepareStatement("delete from etudiant where matricule='"+mat+"'");
        return !pr.execute();
    }

    @Override
    public Etudiant rechercheetd(String matricule) throws SQLException {
        PreparedStatement pr=con.prepareStatement("select * from etudiant where matricule='"+matricule+"'");
        ResultSet rs=pr.executeQuery();
        if(rs.next()){return(new Etudiant(rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("groupe"),rs.getString("section_etd")));}
        return null;
    }

    @Override
    public ObservableList<Etudiant> listeetd(String section)  {
        ObservableList<Etudiant> list= FXCollections.observableArrayList();
        PreparedStatement pr= null;
        try {
            pr = con.prepareStatement("select * from etudiant where section_etd='"+section+"'");
            ResultSet rs=pr.executeQuery();
            while (rs.next())
                list.add(new Etudiant(rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("groupe"),rs.getString("section_etd")));
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
