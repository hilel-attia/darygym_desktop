/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Archive;
import java.net.URL;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.darygym.entity.Evenement;
import edu.darygym.utiles.Myconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property; 
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.darygym.services.ServiceArchive;
import edu.darygym.services.ServiceEvenement;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import static sun.management.GcInfoCompositeData.getId;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class GestionHistoryController implements Initializable {

    @FXML
    private TableView<Archive> ArchiveTable;
       @FXML
    private TableColumn<Archive, String> nom_ArchiveCol;
    @FXML
    private TableColumn<Archive, String> descCol;
 
        @FXML
    private TableColumn<Archive, String> imageCol;

   String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Archive archive = null ;
    
    ObservableList<Archive>  List = FXCollections.observableArrayList();
    @FXML
   private TableColumn<Archive, String> editCol;
    @FXML
    private TextField recherche;
 
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            LoadDate();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
  private void USER_PLUS(MouseEvent event) {
             try {
            Parent parent = FXMLLoader.load(getClass().getResource("./History.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refraiche (){
           try {
            List.clear();
            query = "SELECT * FROM `archive`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new Archive (
                        resultSet.getInt("id"), 
                         resultSet.getString("nomarchive"),
                        resultSet.getString("description"),
                        resultSet.getString("image")));
                ArchiveTable.setItems(List);
                
            }    
        } catch (SQLException ex) {
            Logger.getLogger(GestionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private final EventHandler<MouseEvent> deleteIconCallback = (e) -> {
        FontAwesomeIconView icon = (FontAwesomeIconView) e.getSource();

         ServiceArchive servicearchive = new ServiceArchive();
                            try {
                                archive = ArchiveTable.getSelectionModel().getSelectedItem();
                                System.out.println("selected row : " + archive);
                                servicearchive.supprimer(archive.getId());
                                refraiche();
                                
                            } catch (Exception ex) {
                                Logger.getLogger(GestionHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    };
     
    private void LoadDate() throws SQLException {
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/darygym_database", "root","");
           refraiche();
          
        nom_ArchiveCol.setCellValueFactory(new PropertyValueFactory<>("nomarchive"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        
      Callback<TableColumn<Archive, String>, TableCell<Archive, String>> cellFoctory = (TableColumn<Archive, String> param) -> {
            // make cell containing buttons
            final TableCell<Archive, String> cell = new TableCell<Archive, String>() {
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
         ArchiveTable.setItems(List);
         
         
    }
    public void deleteEvent(MouseEvent event)  {
                            
                           /* try {
                                evenement = EventTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `evenement` WHERE id  ="+evenement.getId();
                                connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/darygym_database", "root","");
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refraiche();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                            
                          
    };

    @FXML
    private void recherche(ActionEvent event) {
        
       if(!recherche.getText().isEmpty()){
            ArchiveTable.getItems().clear();
        ServiceArchive uti = new ServiceArchive();
        List< Archive> a = uti.getArchivesByNname(recherche.getText());
         ArchiveTable.getItems().setAll(a);
         ArchiveTable.refresh();
       }else {
            refraiche();
       } 
    }
    
}
 

