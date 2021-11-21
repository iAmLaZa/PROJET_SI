package interfaces;

import classes.Etudiant;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Ietd {
    public boolean ajouteretudiant();
    public boolean updatetd() throws SQLException;
    public boolean deleteetd(String mat) throws SQLException;
    public Etudiant rechercheetd(String matricule) throws SQLException;
    ObservableList<Etudiant> listeetd(String section) throws SQLException;
}
