/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.darygym.entity.Evenement;
import edu.darygym.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class EventMarkertController implements Initializable {

    @FXML
    private Label txtUsername;
      private ListView<Evenement> listOfEvents;
    private TextField event_Name;
    private Pane pan;

    private ObservableList<Evenement> eventData = FXCollections.observableArrayList();
    ServiceEvenement es = new ServiceEvenement();

    private TextField nom;
    private TextField date;
    private TextField description;
    private TextField lieu;
    private TextField prix;
    private TextField datefin;
    private TextField image;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button btn_creerPerso;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void creerEvent(ActionEvent event) {
    }
 public void afficheall(){
         
    List<Evenement> event = new ArrayList<Evenement>();
        event = es.getAll();
  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i <event.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/edu.darygym.gui/OneEvenementEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OneEvenementEventController oneEvent= fxmlLoader.getController();
                oneEvent.setData(event.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.add(anchorPane, column++, row); 
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void refrech(MouseEvent event) { 
             FXMLLoader Loader=new FXMLLoader();
        Loader.setLocation(getClass().getResource("/edu.darygym.gui/EventMarkertController.fxml"));
        try {
            Loader.load();  
        } catch (IOException ex) {
            Logger.getLogger(EventMarkertController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               EventMarkertController fev=Loader.getController();
               
               fev.afficheall();
                Parent p=Loader.getRoot();
                Stage frontView ;
               
                    frontView= (Stage) ((Node) ((MouseEvent)event).getSource()).getScene().getWindow();
                
                Scene scene = new Scene(p);
                frontView.setScene(scene);
                frontView.show(); 
    }
    
}
