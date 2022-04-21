/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Evenement;
import edu.darygym.services.ServiceEvenement;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class GestionEvenementController implements Initializable {

    @FXML
    private TextField tf_desc;
    @FXML
    private TextField tf_lieu;
    @FXML
    private Button btn_create;
    @FXML
    private Button btn_back;
    @FXML
    private TextField tf_price;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_img;
    @FXML
    private TextField tf_datefin;
    @FXML
    private TextField tf_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createEvent(ActionEvent event) throws SQLException {
    
            String Nom = tf_nom.getText(); 
            String Lieu = tf_lieu.getText(); 
            String date = tf_date.getText(); 
            String Type = tf_type.getText();
            String description= tf_desc.getText();
            String image = tf_img.getText(); 
            String prix = tf_price.getText(); 
            String datefin = tf_datefin.getText(); 
            
            Evenement p = new Evenement(Nom,Lieu,date,Type,description,image,prix,datefin); 
            ServiceEvenement pc = new ServiceEvenement() ;
            pc.ajouter(p); 
    
    }

    @FXML
    private void GOBack(ActionEvent event) {
    }
    
}
