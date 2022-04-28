/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;


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
import edu.darygym.services.ServiceEvenement;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import static sun.management.GcInfoCompositeData.getId;




/**
 * FXML Controller class
 *
 * @author delll
 */
public class AfficheEvenementController implements Initializable {

    @FXML
    private TableView<Evenement> EventTable;
    @FXML
    private TableColumn<Evenement, String> NomCol;
    @FXML
    private TableColumn<Evenement, String> DateCol;
    @FXML
    private TableColumn<Evenement, String> descCol;
    @FXML
    private TableColumn<Evenement, String> LieuCol;
    @FXML
    private TableColumn<Evenement, String> PriceCol;
    @FXML
    private TableColumn<Evenement, String> DatefinCol;
    @FXML
    private TableColumn<Evenement, String> TypeCol;
    @FXML
    private TableColumn<Evenement, String> ImageCol;
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Evenement evenement = null ;
    
    ObservableList<Evenement>  List = FXCollections.observableArrayList();
    @FXML
   private TableColumn<Evenement, String> editCol;
    @FXML
    private ComboBox<String> TriChoice;
 
    


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
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAddevent(MouseEvent event) {
             try {
            Parent parent = FXMLLoader.load(getClass().getResource("./GestionEvenement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refraiche (){
           try {
            List.clear();
            
            query = "SELECT * FROM `evenement`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
              
                List.add(new Evenement(
                        resultSet.getInt("id"),
                        resultSet.getString("image"),
                        resultSet.getString("lieu"),
                        resultSet.getString("date"),
                        resultSet.getString("description"),
                        resultSet.getString("nom"), 
                        resultSet.getString("type"), 
                        resultSet.getString("prix"),
                        resultSet.getString("datefin")));
                EventTable.setItems(List);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private final EventHandler<MouseEvent> deleteIconCallback = (e) -> {
        FontAwesomeIconView icon = (FontAwesomeIconView) e.getSource();

         ServiceEvenement serviceEvent = new ServiceEvenement();
                            try {
                                evenement = EventTable.getSelectionModel().getSelectedItem();
                                System.out.println("selected row : " + evenement);
                                serviceEvent.supprimer(evenement.getId());
                                refraiche();
                                
                            } catch (Exception ex) {
                                Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    };
      private final EventHandler<MouseEvent> editIconCallback = (e) -> {
        evenement = EventTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("./GestionEvenement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            GestionEvenement gestionevenement = loader.getController();
                           
                            gestionevenement.setTextField(evenement.getId(),evenement.getNom(), evenement.getDescription(),evenement.getType(),evenement.getLieu(), evenement.getDate());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show(); 
    };

    private void LoadDate() throws SQLException {
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/darygym_database", "root","");
           refraiche();
          
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        LieuCol.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        DatefinCol.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        
      Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
               public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        deleteIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, deleteIconCallback);
                        editIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, editIconCallback);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                       
                       
                     
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

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
         EventTable.setItems(List);
                 TriChoice.getItems().addAll("aucun", "Trier Selon Type", "Trier Selon Prix");

         
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
    private void trie(ActionEvent event) throws IOException {
        
        if (TriChoice.getValue().equals("Trier Selon Type")) {
            TrieType();
        } else if (TriChoice.getValue().equals("Trier Selon Prix")) {
            TriePrix();
        } 
    }
    
      private void TrieType() throws IOException {
          System.out.println("trie me");
           EventTable.getItems().clear();
        ServiceEvenement uti = new ServiceEvenement();
        
        List< Evenement> a = uti.triType();
          System.out.println(a);
       
         EventTable.getItems().setAll(a);
         EventTable.refresh();

    }

    private void TriePrix() throws IOException {
        ServiceEvenement uti = new ServiceEvenement();
        Evenement evenement = new Evenement();
        List<Evenement> a = uti.triPrix();
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        LieuCol.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        DatefinCol.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        

        EventTable.getItems().setAll(a);

    }

}