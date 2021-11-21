package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import manipulation.outils;
import sample.BD;


import java.io.IOException;
import java.sql.*;

public class login {
    private static Connection con = BD.connect();
    private static Statement statement;

    static {
        try {
            statement = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ResultSet rs = null;
    @FXML
    private Label error;
    @FXML
    private TextField usernametxt;
    @FXML
    private PasswordField passwordtxt;






    int count=0;
    @FXML
    Button btnlogin;

    public void getlogin(ActionEvent actionEvent) throws SQLException, IOException {
        error.setText("");


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");

        String username = usernametxt.getText();
        String password = passwordtxt.getText();


        String sql = "select * from user";
        rs = statement.executeQuery(sql);


        while (rs.next()) {
            if (!rs.getString("username").equals(username)) {
                error.setText("user n'existe pas");
            } else {
                sql = "select * from user where username='"+username+"'";
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    if (!rs.getString("password").equals(password)) {
                        count++;
                        if(count==3){
                            /*sql="delete from user where username='"+username+"'";
                            PreparedStatement pr=con.prepareStatement(sql);
                            pr.execute();*/
                            btnlogin.setDisable(true);
                            error.setText("redémarrer application");
                            usernametxt.setText("");

                        }
                            error.setText("password incorret");
                            passwordtxt.setText("");

                    } else {

                        outils.loadp(actionEvent, "Accueil", "/sample/menu.fxml");


                    }
                }
            }
        }
    }
}
