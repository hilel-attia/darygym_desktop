/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Archive;
import edu.darygym.services.ServiceArchive;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class HistoryController implements Initializable {

    @FXML
    private TextField tf_desc;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_image;
        @FXML
    private Button btn_create;
    
       String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Archive archive = null;
    int id;

        ServiceArchive serviceArchive = new ServiceArchive () ;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createEvent(MouseEvent event) throws SQLException {
     
    }

    @FXML
    private void AjouterArchive(ActionEvent event) throws SQLException { 
            String nomarchive= tf_nom.getText();
            String description = tf_desc.getText(); 
            String image = tf_image.getText(); 
            Archive ar = new Archive(nomarchive,description,image); 
                serviceArchive.ajouter(ar); 
           }

    @FXML
    private void Closeview(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
          stage.close();
    }


    }
