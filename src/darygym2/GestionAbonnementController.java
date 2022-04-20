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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Abonnement;
import models.Offres;
import services.AbonnementServices;
import services.OffresServices;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionAbonnementController implements Initializable {

    @FXML
    private TableView<Abonnement> tableabonnement;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfoffre;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    @FXML
    private TableColumn<Abonnement,String> dureea;
    @FXML
    private TableColumn<Abonnement,Integer> offrea;
     int  index= -1; 
     
     
       private Abonnement c; 
            
            ObservableList<Abonnement>  list =  FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> affecter;
   
                Connection cnx = DataSource.getInstance().getCnx();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            fillcombo();
        } 
   catch (SQLException ex) {
        } 
        
        
        
      affiche();
        ObservableList<Abonnement>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT offres_id , duree FROM abonnement");
            while(rs.next())
            {
                list.add(new Abonnement(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }

      
  
        // TODO
 tableabonnement.setItems(list);
  tableabonnement.refresh();

        // TODO
    }    
    
    
      ObservableList combo = FXCollections.observableArrayList();
     public void fillcombo() throws SQLException {
        PreparedStatement pst;
        String query = "select offres_id from offres";
        pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            combo.add(rs.getString("offres_id"));
            affecter.setItems(combo);
            
           
            

    }}

    @FXML
    private void selected(MouseEvent event) {
        
          index=tableabonnement.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        tfoffre.setText(offrea.getCellData(index).toString());
                tfduree.setText(dureea.getCellData(index).toString());
               
        
    }

    @FXML
    private void supprimerAbonnement(ActionEvent event) {
        
        AbonnementServices Abonnement = new AbonnementServices();
    Abonnement ls = new Abonnement();
    ls = tableabonnement.getSelectionModel().getSelectedItem();
                

       Abonnement.SupprimerAbonnement(ls.getDuree());
       
       affiche();
           
        
     
        
        
    }

    @FXML
    private void modifierAbonnement(ActionEvent event) {
          if(verifUserChamps() ){ 
                        if ( controlSaisie()){

        
             int offres_id=Integer.parseInt(tfoffre.getText());
        String duree=tfduree.getText();
   

        AbonnementServices sp = new AbonnementServices();
        Abonnement c = new Abonnement();
        c.setOffres_id(offres_id);
 c.setDuree(duree);

                c.setOffres_id(offres_id);

        
        sp.modifier(c);
        
         affiche();
         
         tableabonnement.refresh();
                        }}
         
        
        
    }

    @FXML
    private void ajouterAbonnement(ActionEvent event) {
          if(verifUserChamps() ){ 
                        if ( controlSaisie()){

          AbonnementServices ts = new AbonnementServices();
                  AbonnementServices ts1 = new AbonnementServices();
       ts.AjouterAbonnement(new Abonnement(Integer.parseInt(tfoffre.getText()),tfduree.getText()));
        
                 tableabonnement.refresh();
              
    affiche();
    }
          }}
    
    
    
    
       public ObservableList<Abonnement> show1()
    { 
       

           try {
               ObservableList<Abonnement> obl =FXCollections.observableArrayList();
                             Connection cnx = DataSource.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select offres_id,duree from abonnement ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonnement ls = new Abonnement();
                 ls.setOffres_id(rs.getInt("offres_id"));

                 ls.setDuree(rs.getString("duree"));
                
                 
             

                  
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
        
           
                         
      offrea.setCellValueFactory(new PropertyValueFactory<>("offres_id"));
      dureea.setCellValueFactory(new PropertyValueFactory<>("duree"));
   
      ObservableList<Abonnement> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tableabonnement.setItems(obl);
      System.out.println(""+obl);

                      
    }
 
 
 public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        tfoffre.setStyle(styledefault);
        tfduree.setStyle(styledefault);
       

     
       
 

        if (tfoffre.getText().equals("")) {
            tfoffre.setStyle(style);
            verif = 1;
        }
       
        if ( tfduree.getText().equals("")) {
             tfduree.setStyle(style);
            verif = 1;
        }
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
    public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       
                if(checkIfStringContainsNumber(tfduree.getText())){
            alert.setContentText("La duree ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }   
             

        
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
   public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }
   
    
    
        
    
    
    
}
