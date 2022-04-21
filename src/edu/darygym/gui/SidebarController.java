/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sun.util.logging.PlatformLogger;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class SidebarController implements Initializable {

    @FXML
    private BorderPane bg;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HomeAdmin(MouseEvent event) {
          bg.setCenter(ap);
    }

    @FXML
    private void GestionEvenement(MouseEvent event) {
        loadPage("GestionEvenement"); 
    }

    @FXML
    private void Archive(MouseEvent event) {
    }

    @FXML
    private void Participent(MouseEvent event) {
    }

    @FXML
    private void HomeClient(MouseEvent event) {
    }

    @FXML
    private void Evenement(MouseEvent event) {
    }

    @FXML
    private void ArchiveEvents(MouseEvent event) {
    }
    
    private void loadPage(String page){ 
        Parent root = null ; 
        try { 
            root = FXMLLoader.load(getClass().getResource(page+".fxml")); 
        } catch (IOException ex) { 
           // Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE,null,ex); 
    }
        bg.setCenter(root);
    }
    
    
}
