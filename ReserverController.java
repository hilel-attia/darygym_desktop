/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Archive;
import edu.darygym.entity.Evenement;
import edu.darygym.entity.Reservation;
import edu.darygym.services.ServiceEvenement;
import edu.darygym.services.ServiceReservation;
import edu.darygym.utiles.EmailUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author delll
 */
public class ReserverController implements Initializable {

    private TextField tf_desc;
    @FXML
    private Button btn_reserver;
    @FXML
    private TextField tf_nom;
    
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Reservation resrever  = null; 
        ServiceReservation servicereserve = new ServiceReservation() ;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_ville;
    @FXML
    private TextField tf_num;
    @FXML
    private TextField tf_email; 
      @FXML
    private Text textcs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    


    @FXML
    private void CloseResrver(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
          stage.close();
    }

    @FXML
    private void Reserver(MouseEvent event) {  
          ServiceReservation sR = new ServiceReservation();
         if(tf_nom.getText().equals("")||tf_username.getText().equals("")||(tf_ville.getText().equals("")||tf_num.getText().equals("") ||tf_email.getText().equals(""))){ 
         textcs.setText("saisir  tous les champs!");
       
         }
        else if((tf_num.getText().length()>9) ) {
                            new Alert(Alert.AlertType.ERROR,"ce champ de passe 9",ButtonType.CLOSE).show();

         }
         else{
    
              try {
                  sR.ajouter(new Reservation( tf_nom.getText(),tf_username.getText(),tf_ville.getText(),tf_num.getText(),tf_email.getText()));
                  JOptionPane.showMessageDialog(null, "reservationRestau ajoutèe VOUS ALLREZ PASSER UN SEJOUR DE");
              } catch (SQLException ex) {
                  Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }

    @FXML
    private void Reserver(ActionEvent event) throws SQLException {
        
          ServiceReservation sR = new ServiceReservation();
         if(tf_nom.getText().equals("")||tf_username.getText().equals("")||(tf_ville.getText().equals("")||tf_num.getText().equals("") ||tf_email.getText().equals("")
               || !tf_email.getText().contains("@") )){ 
         textcs.setText("saisir  tous les champs!");
       
         }else{
             textcs.setText("");
               String namepart= tf_nom.getText();
            String usernamepart = tf_username.getText(); 
            String Ville = tf_ville.getText(); 
               String numtelephonepart = tf_num.getText(); 
                  String email_participent = tf_email.getText(); 
            Reservation ar = new Reservation(namepart,usernamepart,Ville,numtelephonepart,email_participent); 
                servicereserve.ajouter(ar); 
        try {
            EmailUtils.sendMail(email_participent, "hi mother fucker");
        } catch (Exception ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
       
    }
    
}
