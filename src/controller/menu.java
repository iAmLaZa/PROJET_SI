package controller;

import classes.Etudiant;
import classes.Module;
import classes.enseignant;
import classes.section;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import manipulation.outils;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class menu  implements Initializable {

    @FXML
    AnchorPane anchormaj,anchoretat,anchorsection;
    public static Etudiant e=new Etudiant();
    public static classes.section s=new section();
     public static Module m=new Module();
     public static enseignant en=new enseignant();
     int i;

    public void menumaj(){
        anchoretat.setVisible(false);
        anchormaj.setVisible(true);
        anchormajetd.setVisible(false);
        anchormajmodule.setVisible(false);
        anchorsection.setVisible(false);
        anchorens.setVisible(false);

    }
    @FXML
    AnchorPane anchormajetd,anchormajmodule,anchorens;

    public void menubtnetd(){
        anchormajetd.setVisible(true);
        anchoretat.setVisible(false);
        anchormajmodule.setVisible(false);
        anchorsection.setVisible(false);
        anchorens.setVisible(false);
        try {

            section.setItems(s.namesection());
            newsection.setItems(s.namesection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    TextField mat,nom,prenom,grp;
    @FXML
    ChoiceBox<String> section;
    public void ajouteretd(){
        if(outils.showconfirmationmessage("ajouter etd","ajouter ce etd")){
            if(mat.getText()!=null) {
                Etudiant etd = new Etudiant(mat.getText(), nom.getText(), prenom.getText(), Integer.valueOf(grp.getText()), section.getValue());
                etd.ajouteretudiant();
                outils.showmessage("ajouter","etd ajouter avec succes");

        }

            mat.setText("");
            nom.setText("");
            prenom.setText("");
            section.setValue("");
            grp.setText("");

        }
    }
    public void rechercheetdinsc() throws SQLException {

        e=e.rechercheetd(mat.getText());
        if(e!=null){

            mat.setText(e.getMatricule());
            nom.setText(e.getNom());
            prenom.setText(e.getPrenom());
            section.setValue(e.getSecion());
            grp.setText(String.valueOf(e.getGroupe()));
            outils.showmessage("information","etudiant existant");
        }else{
            e=new Etudiant();
            outils.showmessage("information","etudiant inexistant remplire les autres données");
        }
    }
    @FXML
    TextField matupdate,newgrp,newnom,newprenom;
    @FXML
    ChoiceBox<String> newsection;
    public void rechercheetdupd() throws SQLException {

        e=e.rechercheetd(matupdate.getText());
        if(e!=null){
            newnom.setText(e.getNom());
            newprenom.setText(e.getPrenom());
           newsection.setValue(e.getSecion());
           newgrp.setText(String.valueOf(e.getGroupe()));
        }
        else{
            outils.showerroronmessage("erreur","etudiant n'existe pas");
            matupdate.setText("");
            e=new Etudiant();
        }
    }
    @FXML
    TextField matdepart;
    public void departetd() throws SQLException {
        if(e.rechercheetd(matdepart.getText())!=null){
            if(outils.showconfirmationmessage("depart etd","depart etd")){

                e.deleteetd(matdepart.getText());

                }
            }else{
            outils.showerroronmessage("erreur","etd n'existe pas");
        }

        matdepart.setText("");
    }
    public void updateetd(){
        if(outils.showconfirmationmessage("update etd","update etd")){
            e=new Etudiant(matupdate.getText(),newnom.getText(),newprenom.getText(),Integer.valueOf(newgrp.getText()),newsection.getValue());
            e.updatetd();
        }

        matupdate.setText("");
        newsection.setValue("");
        newgrp.setText("");
        newnom.setText("");
        newprenom.setText("");
    }
    public void menubtnmodule(){
        anchoretat.setVisible(false);
        anchormajetd.setVisible(false);
        anchormajmodule.setVisible(true);
        anchorsection.setVisible(false);
        anchorens.setVisible(false);
        try {
            ens.setItems(en.codeens());
            newens.setItems(en.codeens());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @FXML
    TextField codem,lmodule,coef,newlm,newcom,searchmodule;
    @FXML
    ChoiceBox<String> ens,newens;
    public void ajoutermodule(){
        if(outils.showconfirmationmessage("add module","add module")){
            if(codem.getText()!=""){
                m.setCode_m(codem.getText());
                m.setLibelle(lmodule.getText());
                m.setCoef(Integer.valueOf(coef.getText()));
                m.setCode_ens(ens.getValue());
                m.ajoutermodule();
                listemodule.add(m);

            }
            else outils.showerroronmessage("erreur","saisie tous les information");
        }

            codem.setText("");
            lmodule.setText("");
            coef.setText("");



    }
    @FXML
    Button updatemodule,deletemodule;
    public void selectmodule(){
        m=tablemodule.getSelectionModel().getSelectedItem();
        i=listemodule.indexOf(m);
        newlm.setVisible(true);
        newlm.setText(m.getLibelle());
        newcom.setVisible(true);
        newcom.setText(String.valueOf(m.getCoef()));
        newens.setVisible(true);
        newens.setValue(m.getCode_ens());
        updatemodule.setVisible(true);
        deletemodule.setVisible(true);
    }
    public void updatemodule() throws SQLException {
        if(outils.showconfirmationmessage("update module","update module")){
            m.setCode_ens(newens.getValue());
            m.setCoef(Integer.valueOf(newcom.getText()));
            m.setLibelle(newlm.getText());
            m.updatemodule();
            listemodule.set(i,m);
            outils.showmessage("update","update module avec succes");
        }
        newlm.setVisible(false);
        newlm.setText("");
        newcom.setVisible(false);
        newcom.setText("");
        newens.setVisible(false);
        updatemodule.setVisible(false);
        deletemodule.setVisible(false);
    }
    public void deletemodule() throws SQLException {
        if(outils.showconfirmationmessage("delete module","delete module")){
            m.deletemodule(m.getCode_m());
            listemodule.remove(i);
            outils.showmessage("delete","delete module avec succes");
        }

        newlm.setVisible(false);
        newlm.setText("");
        newcom.setVisible(false);
        newcom.setText("");
        newens.setVisible(false);
        updatemodule.setVisible(false);
        deletemodule.setVisible(false);
    }

    @FXML
    TableView<Module> tablemodule;
    @FXML
    TableColumn<Module,String> cm;
    @FXML
    TableColumn<Module,String> lm;
    @FXML
    TableColumn<Module,Integer> com;
    @FXML
    TableColumn<Module,String> em;
    ObservableList<Module> listemodule;
    public void searchmodule(){
        outils.searchglobale(searchmodule,listemodule,tablemodule);
    }




    public void menuetat() throws SQLException {
        anchoretat.setVisible(true);
        anchormaj.setVisible(false);
        anchormajetd.setVisible(false);
        anchormajmodule.setVisible(false);
        anchorsection.setVisible(false);
        anchorens.setVisible(false);
        sectionetd.setItems(s.namesection());
    }
    public void menubtnsection() throws SQLException {
        anchoretat.setVisible(false);
        anchormajetd.setVisible(false);
        anchormajmodule.setVisible(false);
        anchorsection.setVisible(true);
        anchorens.setVisible(false);
        sectionetd.setItems(s.namesection());
    }
    @FXML
    TextField codesection,lsection,specialite,codesectionsupp;
    @FXML
    Button addsection;
    public void searchsection()  {
        s=s.recherchesection(codesection.getText());
        if(s!=null){
            lsection.setText(s.getLibelle());
            specialite.setText(s.getSpecialite());
            outils.showmessage("information","section existe deja");
        } else{
        outils.showerroronmessage("information","section inexistant remplire les autres données");
        addsection.setVisible(true);
        s=new section();
            lsection.setText("");
            specialite.setText("");
        }
    }
    public void addsection(){
       if(outils.showconfirmationmessage("confirmatin","section ajouter")){
           s.setCode_s(codesection.getText());
           s.setLibelle(lsection.getText());
           s.setSpecialite(specialite.getText());
           s.addsection();

       }
       codesection.setText("");
        lsection.setText("");
        specialite.setText("");
        addsection.setVisible(false);



    }
    public void deletesection() throws SQLException {
        if(s.deletesection(codesectionsupp.getText())==true) {
            outils.showerroronmessage("erreur", "section n'existe pas");
        }
        codesectionsupp.setText("");

    }
    public void menubtnens() throws SQLException {
        anchoretat.setVisible(false);
        anchormajetd.setVisible(false);
        anchormajmodule.setVisible(false);
        anchorsection.setVisible(false);
        anchorens.setVisible(true);
        ens.setItems(en.codeens());
        newens.setItems(en.codeens());
    }
    @FXML
    TextField codeens,nomens,prenomens,grade,newgrade,updatecodeens,deletecodeens;
    public void searchens() throws SQLException {
        en=en.rechercheens(codeens.getText());
        if(en!=null){

            codeens.setText(en.getCode_ens());
            nomens.setText(en.getNom_ens());
            prenomens.setText(en.getPrenom_ens());
            grade.setText(en.getGrade());

            outils.showmessage("information","ens existant");
        }else{
            en=new enseignant();
            outils.showmessage("information","ens inexistant remplire les autres données");
        }
    }
    public void addens(){
        if(outils.showconfirmationmessage("ajouter ens","ajouter ce ens")){
            if(mat.getText()!=null) {
                en = new enseignant(codeens.getText(), nomens.getText(), prenomens.getText(), grade.getText());
                en.addens();
                outils.showmessage("ajouter","ens ajouter avec succes");

            }

            codeens.setText("");
            nomens.setText("");
            prenomens.setText("");
            grade.setText("");


        }
    }
    public void searchupdate() throws SQLException {
        en=en.rechercheens(updatecodeens.getText());
        if(en!=null){
           newgrade.setText(en.getGrade());
        }
        else{
            outils.showerroronmessage("erreur","ens n'existe pas");
            updatecodeens.setText("");
            en=new enseignant();
        }

    }
    public void updateens(){
        if(outils.showconfirmationmessage("update ens","update ens")){
            en=new enseignant();
            en.setCode_ens(updatecodeens.getText());
            en.setGrade(newgrade.getText());
            e.updatetd();
        }

        updatecodeens.setText("");
        newgrade.setText("");

    }
    public void depart() throws SQLException {
        if(en.rechercheens(deletecodeens.getText())!=null){
            if(outils.showconfirmationmessage("depart ens","depart ens")){

                en.deleteens(deletecodeens.getText());

            }
        }else{
            outils.showerroronmessage("erreur","ens n'existe pas");
        }

        deletecodeens.setText("");
    }
    @FXML
    Button logout;
    public void logout(){
        Stage stage =(Stage) logout.getScene().getWindow();
        stage.close();
    }

    @FXML
    ChoiceBox<String> sectionetd;

    @FXML
    TableView<Etudiant> tableetd;
    @FXML
    TableColumn<Etudiant,String> matsection;
    @FXML
    TableColumn<Etudiant,String> nomsection;
    @FXML
    TableColumn<Etudiant,String> prenomsection;
    @FXML
    TableColumn<Etudiant,String> secsection;
    @FXML
    TableColumn<Etudiant,Integer> grpsection;
    ObservableList<Etudiant> listeetd;

    public void sectionetd() throws SQLException {
        listeetd=e.listeetd(sectionetd.getValue());
        tableetd.setItems(listeetd);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ens.setItems(en.codeens());
            newens.setItems(en.codeens());
            section.setItems(s.namesection());
            newsection.setItems(s.namesection());
            sectionetd.setItems(s.namesection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        cm.setCellValueFactory(new PropertyValueFactory<Module,String>("code_m"));
        lm.setCellValueFactory(new PropertyValueFactory<Module,String>("libelle"));
        com.setCellValueFactory(new PropertyValueFactory<Module,Integer>("coef"));
        em.setCellValueFactory(new PropertyValueFactory<Module,String>("code_ens"));


        try {
            listemodule=m.listmodule();
            tablemodule.setItems(listemodule);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        matsection.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("matricule"));
        nomsection.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
        prenomsection.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
        secsection.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("secion"));
        grpsection.setCellValueFactory(new PropertyValueFactory<Etudiant,Integer>("groupe"));
        listeetd=e.listeetd("");
        tableetd.setItems(listeetd);
    }
}
