/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.gui2;

import edu.esprit.entite.reclamation;
import edu.esprit.services.reclamationcrud;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
import edu.esprit.util.ExcelAp;
import edu.esprit.util.Myconnection;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageReclamationController implements Initializable {

    @FXML
    private TableView<reclamation> table;
    @FXML
    private TableColumn<reclamation, String> nom;
    @FXML
    private TableColumn<reclamation, String> prenom;
    @FXML
    private TableColumn<reclamation, String> Titre;
    @FXML
    private TableColumn<reclamation, String> description;
    @FXML
    private TableColumn<reclamation, String> statut;
    @FXML
    private TableColumn<reclamation, String> type;
    Stage dialogStage=new Stage();
    Scene scene;
    @FXML
    private TableColumn<?, ?> cree;
    @FXML
    private TextField titreTextField;
    /**
     * Initializes the controller class.
     */
    
    reclamationcrud servicereclamation=new reclamationcrud();
    int i;
    @FXML
    private TextField recherche;
    String query =null;
    ResultSet resultSet = null;
    reclamation reclamation =  null;
    Connection conn = Myconnection.getInstance().getCnx();
    ObservableList<reclamation> List = FXCollections.observableArrayList();
    PreparedStatement preparedStatement = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          reclamationcrud rs = new reclamationcrud();
        ObservableList<reclamation> list = rs.afficher();
        table.setItems(list);
        nom.setCellValueFactory(new PropertyValueFactory<reclamation, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<reclamation, String>("prenom"));
        Titre.setCellValueFactory(new PropertyValueFactory<reclamation, String>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<reclamation, String>("description"));
        statut.setCellValueFactory(new PropertyValueFactory<reclamation, String>("statut"));
        type.setCellValueFactory(new PropertyValueFactory<reclamation, String>("type"));
      
        table.setItems(list);
    }    

    @FXML
    private void showvalues(MouseEvent event) {
      
    }
    private void afficher(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
                        dialogStage = (Stage) source.getScene().getWindow();
                        dialogStage.close();
                        scene = new Scene(FXMLLoader.load(getClass().getResource("Reponse.fxml")));
                        dialogStage.setScene(scene);
                        dialogStage.show();
      
    }

    @FXML
       private void btnremove(ActionEvent event) {
   
  
                reclamationcrud rs = new reclamationcrud();
                rs.supprimerPartitre(titreTextField.getText());
                ObservableList<reclamation> list = rs.afficher();
                table.setItems(list);
        
        }

    /*@FXML
    private void btnpdf(ActionEvent event) {
          String path = "";

        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(j);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();

        }

        Document doc = new Document() {};
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "AffichageReclamation.pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(8);
            table.addCell("nom");
            table.addCell("prenom");
            table.addCell("description");
            table.addCell("type");
            table.addCell("titre");
            table.addCell("statut");

            reclamationcrud re = new reclamationcrud();
            for (int i = 0; i < re.rowreclamation(); i++) {

                String nom = table.getColumn().get(1).getCellObservableValue(i).getValue().toString();
                String Prenom = table.getColumn().get(2).getCellObservableValue(i).getValue().toString();
                String description = table.getColumn().get(3).getCellObservableValue(i).getValue().toString();
                String type = table.getColumn().get(4).getCellObservableValue(i).getValue().toString();
                String titre = table.getColumn().get(5).getCellObservableValue(i).getValue().toString();
                String statut = table.getColumn().get(6).getCellObservableValue(i).getValue().toString();
               

                table.addCell(nom);
                table.addCell(Prenom);
                table.addCell(description);
                table.addCell(type);
                table.addCell(titre);
                table.addCell(statut);
            }

            doc.add(table);

        }
        catch (FileNotFoundException | DocumentException ex) {

        }

        doc.close();
    }
        */
       @FXML
    private void Excel(ActionEvent event) throws SQLException, IOException {
        ExcelAp Excel =new ExcelAp();
        Excel.Excel();
        
        
    }
    
    
    
    
    
    
     

    private void refraiche () throws SQLException{
        List.clear();
        query = "SELECT * FROM `reclamation`";
        PreparedStatement ps = conn.prepareStatement(query);
         resultSet  = ps.executeQuery();
    
        while (resultSet.next()){
            List.add(new reclamation (
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("description"),
                    resultSet.getString("prenom"),
                    resultSet.getString("titre"),
                    resultSet.getString("type"),
                    resultSet.getString("statut")));
            table.setItems(List);
            
        }
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
        System.out.println(recherche.getText());
    if(! recherche.getText().isEmpty()){
            table.getItems().clear();
        reclamationcrud uti = new reclamationcrud();
        List< reclamation> r = uti.getreclamationBynom(recherche.getText());
         table.getItems().setAll(r);
         table.refresh();
       }else {
            refraiche();
       }
    }

}
        
    
       
    
           
        
     
        
        
    
    



        
     
        
        
    