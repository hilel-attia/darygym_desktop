/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.darygym.entity.Archive;
import edu.darygym.entity.Reservation;
import edu.darygym.services.ServiceArchive;
import edu.darygym.services.ServiceReservation;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class ParticipentController implements Initializable {

    @FXML
    private TableView<Reservation> ReserveTable;
    @FXML
    private TableColumn<Reservation, String> Name;
    @FXML
    private TableColumn<Reservation, String> UserName;
    @FXML
    private TableColumn<Reservation, String> Ville;
    @FXML
    private TableColumn<Reservation, String> num;
    @FXML
    private TableColumn<Reservation, String> email;
  String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reservation reserve = null ;
    
    ObservableList<Reservation>  List = FXCollections.observableArrayList();
    @FXML
   private TableColumn<Reservation, String> editCol;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadDate();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
   

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void refraiche (){
      
           try {
            List.clear();
            query = "SELECT * FROM `reservation`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new Reservation(
                        resultSet.getInt("id"), 
                         resultSet.getString("namepart"),
                        resultSet.getString("usernamepart"),
                        resultSet.getString("ville"), 
                        resultSet.getString("numtelephonepart"), 
                         resultSet.getString("email_participent")));
                ReserveTable.setItems(List);
                
            }    
        } catch (SQLException ex) {
            Logger.getLogger(GestionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private final EventHandler<MouseEvent> deleteIconCallback = (e) -> {
        FontAwesomeIconView icon = (FontAwesomeIconView) e.getSource();

         ServiceReservation servicereservation = new ServiceReservation();
                            try {
                                reserve = ReserveTable.getSelectionModel().getSelectedItem();
                                System.out.println("selected row : " + reserve);
                                servicereservation.supprimer(reserve.getId());
                                refraiche();
                                
                            } catch (Exception ex) {
                                Logger.getLogger(GestionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    };

    private void LoadDate() throws SQLException {
           connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/darygym_database", "root","");
           refraiche();
          
        Name.setCellValueFactory(new PropertyValueFactory<>("namepart"));
        UserName.setCellValueFactory(new PropertyValueFactory<>("usernamepart"));
        Ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
          num.setCellValueFactory(new PropertyValueFactory<>("numtelephonepart"));
           email.setCellValueFactory(new PropertyValueFactory<>("email_participent"));
          
        
      Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>> cellFoctory = (TableColumn<Reservation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reservation, String> cell = new TableCell<Reservation, String>() {
                @Override
               public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
             
                        deleteIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, deleteIconCallback);
                     
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };
            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         List.forEach(e -> {
             System.out.println(e);
         });
         ReserveTable.setItems(List);     
         
    }
    }

