
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
/**
 * FXML Controller class
 *
 * @author 
 */
public class HomeController1 implements Initializable {
      private Parent fxml;
    @FXML
    private AnchorPane root;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    

    @FXML
    private void AffichageReclamation(MouseEvent event) {
       try {
        fxml = FXMLLoader.load(getClass().getResource("AffichageReclamation.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);
       } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void inscriiption(MouseEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("inscription.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);
       } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Reponse(MouseEvent event) {
             try {
            fxml = FXMLLoader.load(getClass().getResource("Reponse.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);
       } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   

    @FXML
    private void AffichageReponse(MouseEvent event) {
                try {
            fxml = FXMLLoader.load(getClass().getResource("AffichageReponse.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);
       } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Acceuille(MouseEvent event) {
    }

    @FXML
    private void AffichageReclamation(ActionEvent event) {
    }

    @FXML
    private void AffichageReponse(ActionEvent event) {
    }

    @FXML
    private void Reponse(ActionEvent event) {
    }

    @FXML
    private void inscription(MouseEvent event) {
    }

    @FXML
    private void inscription(ActionEvent event) {
    }

    
   
}