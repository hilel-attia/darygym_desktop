/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Offres;
import services.OffresServices;
import utils.DataSource;



/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionOffresController implements Initializable {

    @FXML
    private TableView<Offres> tableoffres;
    @FXML
    private TableColumn<Offres,String> imagea;
    @FXML
    private TableColumn<Offres, String> titrea;
    @FXML
    private TableColumn<Offres,String> descriptiona;
    @FXML
    private TableColumn<Offres,Float> prixa;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfprix;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                affiche();
ObservableList<Offres>  list =  FXCollections.observableArrayList();
        // TODO
        
        
                 tableoffres.refresh();

        
        
       

    
        // TODO
    }    
    
   

    @FXML
    private void selected(MouseEvent event) {
    }

    @FXML
    private void supprimerOffres(ActionEvent event) {
    OffresServices Offres = new OffresServices();
    Offres ls = new Offres();
    ls = tableoffres.getSelectionModel().getSelectedItem();
                

       Offres.SupprimerOffres(ls.getTitre());
           
        
        affiche();
        tableoffres.refresh();

    }

    @FXML
    private void modifierOffres(ActionEvent event) {
                String image=tfimage.getText();
        String titre=tftitre.getText();
        String description=tfdescription.getText();

        Float prix=Float.parseFloat(tfprix.getText());
        

        OffresServices sp = new OffresServices();
        Offres c = new Offres();
        c.setImage(image);
 c.setTitre(titre);
        c.setDescription(description);

        c.setPrix(prix);
                

                c.setImage(image);

        
        sp.modifier(c);
        
         affiche();
         
         tableoffres.refresh();
         
        
        
    }

    @FXML
    private void ajouterOffres(ActionEvent event) {
        
       
         OffresServices ts = new OffresServices();
                  OffresServices ts1 = new OffresServices();
       ts.AjouterOffres(new Offres(tfimage.getText(),tftitre.getText(),tfdescription.getText(),Float.parseFloat(tfprix.getText())));
        affiche();
                 tableoffres.refresh();

    }
    
    
    
    
     public ObservableList<Offres> show1()
    { 
       

           try {
               ObservableList<Offres> obl =FXCollections.observableArrayList();
                             Connection cnx = DataSource.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select image,titre,description,prix from offres ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Offres ls = new Offres();
                 ls.setImage(rs.getString("image"));

                 ls.setTitre(rs.getString("titre"));
                 ls.setDescription(rs.getString("description"));
                 ls.setPrix(rs.getFloat("prix"));
                 
             

                  
                  System.out.println("");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
 public void affiche() {
        
           
                         
      imagea.setCellValueFactory(new PropertyValueFactory<>("image"));
      titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
      descriptiona.setCellValueFactory(new PropertyValueFactory<>("description"));
      prixa.setCellValueFactory(new PropertyValueFactory<>("prix"));
      ObservableList<Offres> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tableoffres.setItems(obl);
      System.out.println(""+obl);

                      
    }
   
    
    
    
    
    
}
