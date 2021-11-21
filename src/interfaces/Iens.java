package interfaces;

import classes.enseignant;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Iens {
    ObservableList<String> codeens() throws SQLException;
    enseignant rechercheens(String code_ens) throws SQLException;
    boolean addens();
    boolean updateens();
    boolean deleteens(String code_ens) throws SQLException;
}
