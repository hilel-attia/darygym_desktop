/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.gui2;

import edu.esprit.entite.reclamation;
import edu.esprit.entite.reponse;
import edu.esprit.services.reponsecrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageReponseController implements Initializable {

    @FXML
    private TableView<reponse> table;
    @FXML
    private TableColumn<reponse,String> description;
    @FXML
    private TableColumn<reponse,String> email;
    @FXML
    private TableColumn<reponse, reclamation> reclamation_id;
    @FXML
    private TextField emailtextfield;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           reponsecrud reps = new reponsecrud();
        ObservableList<reponse> list = reps.afficher();
        table.setItems(list);
        description.setCellValueFactory(new PropertyValueFactory<reponse, String>("description"));
        //datedebut.setCellValueFactory(new PropertyValueFactory<reponse, String>("datedebut"));
        //datefin.setCellValueFactory(new PropertyValueFactory<reponse, String>("datefin"));
        email.setCellValueFactory(new PropertyValueFactory<reponse, String>("email"));
         reclamation_id.setCellValueFactory(new PropertyValueFactory<reponse, reclamation>("reclamation_id"));
        table.setItems(list);
    }    

    @FXML
    private void showvalues(MouseEvent event) {
    }

    @FXML
    private void btnremove(ActionEvent event) {
          reponsecrud rs = new reponsecrud();
                rs.supprimerParemail(emailtextfield.getText());
                ObservableList<reponse> list = rs.afficher();
                table.setItems(list);
    }
    
}
