package interfaces;

import classes.Module;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Imodule {
    public boolean ajoutermodule() throws SQLException;
    ObservableList<Module> listmodule() throws SQLException;
    boolean updatemodule() throws SQLException;
    boolean deletemodule(String codem0) throws SQLException;
}
