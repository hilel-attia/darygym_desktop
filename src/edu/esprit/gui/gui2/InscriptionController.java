/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.gui2;

import edu.esprit.entite.reclamation;
import edu.esprit.services.reclamationcrud;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.Node;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscriptionController implements Initializable {

    @FXML
    private Button btnvalider;
    @FXML
    private TextField prenomTextField;
     @FXML
    private TextField nomTextField;
    @FXML
    private TextField titreTextField;
    @FXML
    private TextField descriptionTextField;
     @FXML
    private TextField statutTextField;
      @FXML
    private TextField typeTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public static boolean isValidNom(String nom) {

        String nomRegex = "^[a-zA-Z]+[a-zA-Z]$";
        Pattern pat = Pattern.compile(nomRegex);
        if (nom == null) {
            return false;
        }
        return pat.matcher(nom).matches();
    }
    @FXML
    private void savereclamation(ActionEvent event) {
         String prenom = prenomTextField.getText();
        String nom = nomTextField.getText();
        String Titre = titreTextField.getText();
        //String created_At = created_AtTextField.getText();
        String description = descriptionTextField.getText();
        String statut = statutTextField.getText();
        String type = typeTextField.getText();
      
         reclamation re =new reclamation(Titre, description, prenom, nom, type, statut);
        reclamationcrud rs=new reclamationcrud();
        {                   if (prenomTextField.getText().equalsIgnoreCase("")
                            || nomTextField.getText().equalsIgnoreCase("")
                            || titreTextField.getText().equalsIgnoreCase("")
                            //|| created_AtTextField.getText().equalsIgnoreCase("")
                            || descriptionTextField.getText().equalsIgnoreCase("")
                            || typeTextField.getText().equalsIgnoreCase("")
                            || statutTextField.getText().equalsIgnoreCase("")) {
                       //infoBox("Il faut remplir tout les champs", "Échec", null);
                    } else {
                                        ArrayList<String> nom1 = new ArrayList<>();
                                        nom1.add(nomTextField.getText());
                                        for (String i : nom1) {
                                            if (isValidNom(i)) {
                                               ArrayList<String> prenom1 = new ArrayList<>();
                                                prenom1.add(prenomTextField.getText());
                                                for (String p : prenom1) {
                                                    
                                                    if (isValidNom(p)) {     
                                                                    rs.ajouter(re);
                                                                  // infoBox("Inscription effectuée avec succès", "Succès", null);
                                                                  nomTextField.setText("");
                                                                   prenomTextField.setText("");
                                                                   titreTextField.setText("");
                                                                   descriptionTextField.setText("");
                                                                   typeTextField.setText("");
                                                                   statutTextField.setText("");
                                                                   
                                                                }}}}
                 
            }
        }
    }}
    
    
   

