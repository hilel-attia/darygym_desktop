/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import com.esprit.upload.Upload;
import edu.darygym.entity.Evenement;
import edu.darygym.services.ServiceEvenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author delll
 */
public class GestionEvenement implements Initializable {
       private Parent fxml; 
    private AnchorPane root;
 private File file;
    String pic;
String path;
    @FXML
    private TextField tf_desc;
    @FXML
    private TextField tf_lieu;
    @FXML
    private Button btn_create;
    @FXML
    private Button btn_back;
    @FXML
    private TextField tf_price;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_type;
    @FXML
    private Button tf_img;
    @FXML
    private TextField tf_datefin;
    @FXML
    private TextField tf_date; 
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Evenement evenement = null;
    private boolean update;
    int id;
    String toUploadImage ="";
    boolean isupdating = false;
    ServiceEvenement serviceEvent = new ServiceEvenement() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createEvent(ActionEvent event) throws SQLException {
    
            String Nom = tf_nom.getText(); 
            String Lieu = tf_lieu.getText(); 
            String date = tf_date.getText(); 
            String Type = tf_type.getText();
            String description= tf_desc.getText();
          //  String image = tf_img.getText(); 
            String prix = tf_price.getText(); 
            String datefin = tf_datefin.getText(); 
            
            Evenement p = new Evenement(this.id,this.toUploadImage,Lieu,date,Type,description,Lieu,prix,datefin); 
           if(isupdating){
              
               serviceEvent.modifier(p);
                System.out.println("update mdsfsdfe");
           }else {
                serviceEvent.ajouter(p); 
           }
           
    
    }
    
    @FXML
    private void GOBack(MouseEvent event) throws IOException { 
       
       
    }

    @FXML
    private void createEvent(MouseEvent event) {
    }

    @FXML
    private void GOBack(ActionEvent event) {
    }

    @FXML
    private void closeView(MouseEvent event) {
          Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
          stage.close();
    }

    void setUpdate(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTextField(int id,String nom, String description, String type, String lieu, String date) {
        tf_nom.setText(nom); 
        tf_lieu.setText(lieu); 
        tf_date.setText(date); 
        tf_type.setText(type);
        tf_desc.setText(description);
        isupdating =true;
        this.id =id;
        btn_create.setText("update");
            
               
    }
       @FXML
   private void upload() throws IOException {

        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //pic=(file.toURI().toString());
        pic = new Upload().upload(file, "\\assets");
        this.toUploadImage = pic;
        System.out.println(pic);

    }
}
