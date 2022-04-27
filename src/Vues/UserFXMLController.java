/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entities.user;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import services.UserService;
import validation.TextFieldValidation;


/**
 * FXML Controller class
 *
 * @author hilel
 */
public class UserFXMLController implements Initializable {

    @FXML
    private TableView<user> tableuser;
    @FXML
    private TableColumn<user, String> colnom;
    @FXML
    private TableColumn<user, String> colprenom;
    @FXML
    private TableColumn<user, String> colemail;
    @FXML
    private TableColumn<user, String> colpassword;
    
    @FXML
    private TableColumn<user, String> colrole;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbtri;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
  
   
    @FXML
    private ComboBox<String> cbrole;
    @FXML
    private Button btncreate;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btndel;
    @FXML
    private Button btntri;
    @FXML
    private RadioButton radtous;
    @FXML
    private ComboBox<String> cbrechpar;
    @FXML
    private MenuBar menu;
    @FXML
    private RadioButton radadmin;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton radclient;
    @FXML
    private Button btnReturnMenu;
    @FXML
    private Button pdf;
    @FXML
    private Button stat;
    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbrole.setItems(FXCollections.observableArrayList("Admin", "Adherant", "Coach"));
        cbrechpar.setItems(FXCollections.observableArrayList("username", "nom_complet", "email"));
        cbtri.setItems(FXCollections.observableArrayList("username", "nom_complet", "email"));
        updateTable();
        // TODO
    }

    @FXML
    public void updateTable() {
        System.out.println("table");
        radtous.setSelected(true);
        ObservableList<user> users = us.readAll();
        colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
     
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    public void init() {
        updateTable();
        tfnom.clear();
        tfprenom.clear();
        tfemail.clear();
        tfpassword.clear();
        cbrole.setValue(null);
    }

    public void AlertWindow(String title, String contenu, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @FXML
    private void CreateUser(ActionEvent event) {
      user u = new user();
        TextFieldValidation uUtiles = new TextFieldValidation();
         String username = tfnom.getText();
        String nom_complet = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
        String role = cbrole.getSelectionModel().getSelectedItem();

         if (username.isEmpty()) {
	    alert_Box("Verifier votre nom", "Votre nom ne doit pas être vide");
         }else if (nom_complet.isEmpty()) {
	    alert_Box("Verifier votre mail", "veillez saisir une adresse mail valide");
	} else if (!uUtiles.testEmail(email)) {    
	    alert_Box("Verifier votre mail", "veillez saisir une adresse non existant");
	} else if (!uUtiles.testPassword(password)) {
	    alert_Box("Verifier mot de passe", "Votre mot de passe doit doit contenir au moins une une majuscule et un chiffre ");
	 
	
	
       
	} else {
          u.setUsername(username);
          u.setNomcomplet(nom_complet);
          u.setEmail(email);
          u.setPassword(password);
            u.setRole(role);
            
            
        us.ajouterUserPst(u);
        uUtiles.information_Box("Compte créé avec succès", "votre compte est ajouté");

        init();
    }
    }
    
    public void alert_Box(String title, String message) {
	Alert dg = new Alert(Alert.AlertType.WARNING);
	dg.setTitle(title);
	dg.setContentText(message);
	dg.show();
    }
    
    @FXML
    private void SearchUser(KeyEvent event) {
        String search = tfrecherche.getText();
        if (search == null) {
            updateTable();
        } else {
            String searchby = cbrechpar.getSelectionModel().getSelectedItem();
            ObservableList<user> users = us.recherche(searchby, search);
            colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
            colprenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
            colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            
            colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
            tableuser.setItems(users);
        }

    }

    @FXML
    private void preModSupp(MouseEvent event) {
        user u = tableuser.getSelectionModel().getSelectedItem();
        System.out.println(u.getId());

        tfnom.setText(u.getUsername());
        tfprenom.setText(u.getNomcomplet());
        tfemail.setText(u.getEmail());
        tfpassword.setText(u.getPassword());
       
       
       
        cbrole.setValue(u.getRole());
    }

     @FXML
    private void ModifUser(ActionEvent event) {
        user u = tableuser.getSelectionModel().getSelectedItem();

        String username = tfnom.getText();
        String nom_complet = tfprenom.getText();
        String email = tfemail.getText();
        String password = tfpassword.getText();
        String roles = cbrole.getSelectionModel().getSelectedItem();

        u.setUsername(username);
        u.setNomcomplet(nom_complet);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(roles);
        if (us.modifierUserPst(u)) {
            AlertWindow("Modifier " + roles, roles + " modifié avec succés", AlertType.INFORMATION);
        } else {
            AlertWindow("Modifier " + roles, "Echec de modificaition", AlertType.ERROR);
        }
        tableuser.refresh();
    }

    private void GotoFXML(String vue, String title, Event aEvent) {
        try {
            Event event;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Node) aEvent.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  @FXML
    private void DeleteUser(ActionEvent event) {
        
        user u = tableuser.getSelectionModel().getSelectedItem();
        if (us.suppUserPst(u)) {
            AlertWindow("Supprimer " + u.getRole(), u.getRole() + " supprimé avec succés", AlertType.INFORMATION);
        } else {
            AlertWindow("Supprimer " + u.getRole(), "Echec de suppression", AlertType.ERROR);
        }
        init();
    }

    @FXML
    private void TriUsers(ActionEvent event) {
        String tri = cbtri.getSelectionModel().getSelectedItem();
        ObservableList<user> users = us.tri(tri);
        colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
     
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
    private void getAdmins(ActionEvent event) {
        ObservableList<user> users = us.filterRole("Admin");
        colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
    private void getClients(ActionEvent event) {
        ObservableList<user> users = us.filterRole("Adherant");
        colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
       
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableuser.setItems(users);
    }

    @FXML
    private void handleReturnMenuAdmin(ActionEvent event) {
        GotoFXML("MainFXML", "Darygym", event);
    }

    @FXML
    private void Pdf(ActionEvent event) {
        String path = "";

        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(j);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();

        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "/User.pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(4);
            table.addCell("NOM");
           
            table.addCell("EMAIL");
          
        
            table.addCell("ROLE");

            UserService u = new UserService();
            for (int i = 0; i < u.rowUSER(); i++) {

                String Nom = tableuser.getColumns().get(0).getCellObservableValue(i).getValue().toString();
               
                String email = tableuser.getColumns().get(1).getCellObservableValue(i).getValue().toString();
                
                String role = tableuser.getColumns().get(2).getCellObservableValue(i).getValue().toString();

                table.addCell(Nom);
               
                table.addCell(email);
               
                table.addCell(role);

            }

            doc.add(table);

        } catch (FileNotFoundException | DocumentException ex) {

        }

        doc.close();
    }

    @FXML
    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tableuser;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A2, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void OnClickedStatistique(ActionEvent event) {
          try {

            Parent parent = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
          
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
    
}