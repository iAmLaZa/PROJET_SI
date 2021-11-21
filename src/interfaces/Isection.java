package interfaces;

import classes.section;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Isection {
    ObservableList<String> namesection() throws SQLException;
    section recherchesection(String code_s) throws SQLException;
    public boolean addsection();
    boolean deletesection(String code_s) throws SQLException;
}
