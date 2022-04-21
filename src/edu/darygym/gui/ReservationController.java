/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Evenement;
import edu.darygym.entity.Reservation;
import edu.darygym.services.ServiceEvenement;
import edu.darygym.services.ServiceReservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField tf_ville;
    @FXML
    private TextField tf_num;
    @FXML
    private Button btn_reserver;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_nomPart;
    @FXML
    private TextField tf_prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void createEvent(ActionEvent event) throws SQLException {
          
            String Namepart = tf_nomPart.getText(); 
            String ville = tf_ville.getText(); 
            String Usernamepart = tf_prenom.getText(); 
            String Email_participent= tf_email.getText();
            String Numtelephonepart= tf_num.getText(); 
          
            
            Reservation rev = new Reservation(Namepart,ville,Usernamepart,Email_participent,Numtelephonepart); 
            ServiceReservation pc = new ServiceReservation() ;
            pc.ajouter(rev); 
    
    }

    }
    
}
