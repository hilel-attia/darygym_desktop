/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class HomeController implements Initializable {
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
    private void Acceuille(MouseEvent event) { 
       try { 
        fxml = FXMLLoader.load(getClass().getResource("./AfficheEvenement.fxml")); 
        root.getChildren().removeAll(); 
        root.getChildren().setAll(fxml); 
       } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }

    @FXML
    private void event(MouseEvent event) {
         try { 
            fxml = FXMLLoader.load(getClass().getResource("./GestionEvenement.fxml")); 
        root.getChildren().removeAll(); 
        root.getChildren().setAll(fxml); 
       } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }

    @FXML
    private void Historique(MouseEvent event) { 
             try { 
            fxml = FXMLLoader.load(getClass().getResource("./GestionHistory.fxml")); 
        root.getChildren().removeAll(); 
        root.getChildren().setAll(fxml); 
       } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }
    

    @FXML
    private void participants(MouseEvent event) { 
                try { 
            fxml = FXMLLoader.load(getClass().getResource("./Participent.fxml")); 
        root.getChildren().removeAll(); 
        root.getChildren().setAll(fxml); 
       } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }

    @FXML
    private void ConsulterEvent(MouseEvent event) { 
        try { 
            fxml = FXMLLoader.load(getClass().getResource("./Reserver.fxml")); 
        root.getChildren().removeAll(); 
        root.getChildren().setAll(fxml); 
       } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }

    @FXML
    private void ConsulterHistory(MouseEvent event) {
         try { 
            fxml = FXMLLoader.load(getClass().getResource("./PhotoAff.fxml")); 
        root.getChildren().removeAll(); 
        root.getChildren().setAll(fxml); 
       } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }
    
}
