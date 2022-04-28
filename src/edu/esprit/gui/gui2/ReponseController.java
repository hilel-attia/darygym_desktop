/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.gui2;


import edu.esprit.entite.reclamation;
import edu.esprit.entite.reponse;
import static edu.esprit.gui.gui2.InscriptionController.isValidNom;
import edu.esprit.services.reponsecrud;
import edu.esprit.util.EmailUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReponseController implements Initializable {

    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField emailTextField;
    private TextField datedebutTextField;
    private TextField datefinTextField;
    @FXML
    private Button btnvalider1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savereponse(ActionEvent event) {
          
        String description = descriptionTextField.getText();
        String email = emailTextField.getText();
       // String datedebut = datedebutTextField.getText();
       // String datefin = datefinTextField.getText();
    reponse rep =new reponse(description, email);
        reponsecrud reps=new reponsecrud();
        {                   if (descriptionTextField.getText().equalsIgnoreCase("")
                            || emailTextField.getText().equalsIgnoreCase(""))
        {//   infoBox("Il faut remplir tout les champs", "Échec", null);
           }
        else{
             ArrayList<String> descripiton1 = new ArrayList<>();
                                        descripiton1.add(descriptionTextField.getText());
                                        for (String i : descripiton1){
                                            if (isValidNom(i)) {
                                                reps.ajouter(rep);
                                             descriptionTextField.setText("");
         try {
            EmailUtils.sendMail(email, "on a resolu votre probleme");
        } catch (Exception ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                              
       }               
      }}
     }
    }}
    
    
