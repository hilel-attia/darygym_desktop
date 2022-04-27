/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static pidev.Pidev.Userconnected;

/**
 * FXML Controller class
 *
 * @author hilel
 */
public class MainClientFXMLController implements Initializable {
    @FXML
    private ImageView logout;
    @FXML
    private Button profil;
  
    @FXML
    private Label UserName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserName.setText(Userconnected.getNomcomplet()+" "+Userconnected.getUsername());
        // TODO
    }    

    private void GotoFXML(String vue, String title,Event aEvent) {
           try {
               Event event;
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
               Parent root1 = (Parent) fxmlLoader.load();
               Stage stage =(Stage)((Node) aEvent.getSource()).getScene().getWindow() ;
               stage.setTitle(title);
               stage.setScene(new Scene(root1));
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   
    
    
   

    @FXML
    private void GotoGestionProfil(ActionEvent event) {
        GotoFXML("ProfilFXML", "Darygym",event);
    }

    @FXML
    private void Logout(MouseEvent event) throws IOException, Exception {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deconnexion");
        alert.setContentText("Voulez-vous vraiment Déconnecter?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        Userconnected.setId(0);
        Userconnected.setNomcomplet("");
        Userconnected.setUsername("");
        Userconnected.setEmail("");
        Userconnected.setPassword("");
       
        Userconnected.setImage("");
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            LoginFXMLController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
    }}}
    
}
