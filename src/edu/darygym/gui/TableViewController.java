/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Evenement;
import edu.darygym.services.ServiceEvenement;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TableViewController implements Initializable {

    @FXML
   private TableView<Evenement> table;
    Stage dialogStage=new Stage();
    Scene scene;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Evenement, String> idCol;
    @FXML
    private TableColumn<Evenement, String> nomCol1;
    @FXML
    private TableColumn<Evenement, String> lieuCol;
    @FXML
    private TableColumn<Evenement, String> dateCol;
    @FXML
    private TableColumn<Evenement, String> typeCol;
    @FXML
    private TableColumn<Evenement, String> descCol;
    @FXML
    private TableColumn<Evenement, String> imgCol;
    @FXML
    private TableColumn<Evenement, String> priceCol;
    @FXML
    private TableColumn<Evenement, String> datefinCol;
    @FXML
    private Button Updatebtn;
    @FXML
    private AnchorPane anchoreUpdate;
    @FXML
    private TextField txtRole;
    @FXML
    private Button brtn_fin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceEvenement ep = new ServiceEvenement();
        ObservableList<Evenement> list = (ObservableList<Evenement>) ep.getAll();
        table.setItems(list);
        nomCol1.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        lieuCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieu"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        descCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        imgCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("image"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("price"));
        datefinCol.setCellValueFactory(new PropertyValueFactory<Evenement, String>("datefin"));
     
        table.setItems(list);
    }    

    private void afficher(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
                        dialogStage = (Stage) source.getScene().getWindow();
                        dialogStage.close();
                        scene = new Scene(FXMLLoader.load(getClass().getResource("TableView.fxml")));
                        dialogStage.setScene(scene);
                        dialogStage.show();
     
    }

    @FXML
    private void Alerte(MouseEvent event) {
    }

    @FXML
    private void Update(MouseEvent event) {
    }

    @FXML
    private void UpdatedUser(ActionEvent event) {
    }
}


