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
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
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
    public static int  nh = 0 ,nf=0 , ne = 0 ;  
            ObservableList<String> list = FXCollections.observableArrayList("un mois","six mois","un an");
    

    @FXML
    private TableView<Abonnement> tableabonnement;
    @FXML
    private TextField tfduree;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    @FXML
    private TableColumn<Abonnement,String> dureea;
    private TableColumn<Abonnement,String> titre;
    private Abonnement c; 
      int  index= -1;
     
     
            
    @FXML
    private ComboBox<String> affecter;
   
                Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Label lblClose;
    @FXML
    private TableColumn<Abonnement, String> titrea;
    @FXML
    private Button stat;
   

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
         ObservableList<Abonnement>  list =  FXCollections.observableArrayList();
          try { 
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT titre , duree FROM abonnement");
            while(rs.next())
            {
                list.add(new Abonnement(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }

       titrea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getTitre());
        });
    //image_colonne.setCellValueFactory(new PropertyValueFactory<>("image"));

    dureea.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getDuree());
        });
    
  
        // TODO
 tableabonnement.setItems(list);
  tableabonnement.refresh();

        // TODO
    }    
    
    
      ObservableList combo = FXCollections.observableArrayList();
     public void fillcombo() throws SQLException {
        PreparedStatement pst;
        String query = "select titre from offres";
        pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            combo.add(rs.getString("titre"));
            affecter.setItems(combo);
            
           
            

    }}

    @FXML
    private void selected(MouseEvent event) {
        
          index=tableabonnement.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
        
                tfduree.setText(dureea.getCellData(index).toString());
               
        
    }

    @FXML
    private void supprimerAbonnement(ActionEvent event) {
        
        AbonnementServices Abonnement = new AbonnementServices();
    Abonnement ls = new Abonnement();
    ls = tableabonnement.getSelectionModel().getSelectedItem();
                

       Abonnement.SupprimerAbonnement(ls.getDuree());
       
       affiche();
       Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Alert");
        al.setContentText("ABONNEMENT supprimé AVEC SUCCeS");
        al.setHeaderText(null);
        al.show() ; 
           
        
     
        
        
    }

    @FXML
    private void modifierAbonnement(ActionEvent event) {
          if(verifOffreChamps() ){ 
                        if ( controlSaisie()){

        
             String titre=affecter.getValue();
        String duree=tfduree.getText();
   

        AbonnementServices sp = new AbonnementServices();
        Abonnement c = new Abonnement();
        c.setTitre(titre);
 c.setDuree(duree);

                c.setDuree(duree);

        
        sp.modifier(c);
        
         affiche();
         
         Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Alert");
        al.setContentText("ABONNEMENT modifié AVEC SUCCeS");
        al.setHeaderText(null);
        al.show() ; 
         
         tableabonnement.refresh();
                        }}
         
        
        
    }

    @FXML
    private void ajouterAbonnement(ActionEvent event) {
          if(verifOffreChamps() ){ 
                        if ( controlSaisie()){

          AbonnementServices ts = new AbonnementServices();
                  AbonnementServices ts1 = new AbonnementServices();
       ts.AjouterAbonnement(new Abonnement(affecter.getValue(),tfduree.getText()));
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Alert");
        al.setContentText("ABONNEMENT AJOUTé AVEC SUCCeS");
        al.setHeaderText(null);
        al.show() ; 
        
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
                 PreparedStatement pt= cnx.prepareStatement("select titre,duree from abonnement ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonnement ls = new Abonnement();
                 ls.setTitre(rs.getString("titre"));

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
        
           
                         
      titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
      dureea.setCellValueFactory(new PropertyValueFactory<>("duree"));
   
      ObservableList<Abonnement> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tableabonnement.setItems(obl);
      System.out.println(""+obl);

                      
    }
 
 
 public boolean verifOffreChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        tfduree.setStyle(styledefault);
       

     
       
 

       
       
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

    @FXML
    private void allezversabn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/darygym2/GestionOffres.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void allezversoff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/darygym2/GestionAbonnement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    @FXML
    private void stat(ActionEvent event) throws IOException{
       AbonnementServices ser= new AbonnementServices();
        
        List<Abonnement> li =ser.afficher(); 
        int i = 0; 

        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getDuree().equals("un mois"))
        
        {nh=nh+1;}  ;
        if (li.get(i).getDuree().equals("six mois")){nf=nf+1 ; } 
        if (li.get(i).getDuree().equals("un an")){ne=ne+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AbnStat.fxml"));
         Parent root = loader.load();
        tfduree.getScene().setRoot(root);
    }

    
 }
    

    
   
    
    
        
    
    
    

