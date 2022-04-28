/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Evenement;
import edu.darygym.services.ServiceEvenement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class OneEvenementEventController implements Initializable {

    @FXML
    private Text date;
    @FXML
    private Text lieu;
    @FXML
    private Text desc;
    @FXML
    private Text prix;
    @FXML
    private Text nom;
    @FXML
    private Text type;
    @FXML
    private Text image;
   String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
     private Evenement ev;
    ServiceEvenement es = new  ServiceEvenement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    public void setData(Evenement e) throws SQLException {
       connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/darygym_database", "root","");    
         this.ev=e;
        nom.setText(e.getNom());
        desc.setText(e.getDescription());
        prix.setText(e.getPrix());
        type.setText(e.getType());
        lieu.setText(e.getLieu());
        date.setText(e.getDate());
      
    }

    
}
