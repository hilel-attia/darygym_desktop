/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import vues.MainFXMLController;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;
import validation.TextFieldValidation;

/**
 * FXML Controller class
 *
 * @author hilel
 */
public class RegisterFXMLController implements Initializable {

    
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private PasswordField tfpassword;
   
    @FXML
    private TextField tfemail;

    UserService us = new UserService();
    @FXML
    private Label newaccout;
    
    @FXML
    private Button connexion;
    @FXML
    private Button fermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean whenMatchesTenDigitsNumber_thenCorrect(String number) {
        Pattern pattern = Pattern.compile("^\\d{8}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public void AlertWindow(String title, String contenu, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajout(ActionEvent event) throws Exception {

       user u = new user();
        TextFieldValidation uUtiles = new TextFieldValidation();
         String username = tfnom.getText();
        String nom_complet = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
      String role = "Adherant";
         if (username.isEmpty()) {
	    alert_Box("Verifier votre nom", "Votre nom ne doit pas être vide");
         }else if (nom_complet.isEmpty()) {
	    alert_Box("Verifier votre mail", "veillez saisir une adresse mail valide");
	} else if (!uUtiles.testEmail(email)) {    
	    alert_Box("Verifier votre mail", "veillez saisir une adresse non existant");
	} else if (!uUtiles.testPassword(password)) {
	    alert_Box("Verifier mot de passe", "Votre mot de passe doit doit contenir au moins une une majuscule et un chiffre ");
	 
	
	
       
	} else {
          u.setUsername(username);
          u.setNomcomplet(nom_complet);
          u.setEmail(email);
          u.setPassword(password);
     u.setRole(role);
            
            
        us.ajouterUserPst(u);
        uUtiles.information_Box("Compte créé avec succès", "votre compte est ajouté");

                utils.Mailing.sendMail(u);
               
            Stage stage = (Stage) fermer.getScene().getWindow();
            stage.close();
            GotoFXML("LoginFXML", "darygym", event);
           
//        }else{System.out.println("number is wrong!!!");}
        //error_numtel
    }
    }
    
     public void alert_Box(String title, String message) {
	Alert dg = new Alert(Alert.AlertType.WARNING);
	dg.setTitle(title);
	dg.setContentText(message);
	dg.show();
    }
    @FXML
    private void gotoLogin(MouseEvent event) {
        GotoFXML("LoginFXML", "darygym", event);
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
         Stage stage = (Stage) fermer.getScene().getWindow();
         stage.close();
    }

}
