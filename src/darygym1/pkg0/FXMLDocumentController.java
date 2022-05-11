/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym1.pkg0;

import Service.serviceexercice;
import Service.serviceexercicecategorie;
import com.mysql.jdbc.MySQLConnection;

import entity.exercice;
import entity.exercicecategorie;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.management.Query.value;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.runtime.Debug.id;



/**
 *
 * @author fadib
 */
public class FXMLDocumentController implements Initializable {

    private static String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
     @FXML
    private TableView<exercicecategorie> table_exercicecategorie1 ;
  /*   table_exercicecategorie1.setRowFactory( tv -> {
    TableRow<type> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            MyType rowData = row.getItem();
            System.out.println(rowData);
        }
    });
    return row ;
});*/
    
    @FXML
    private TableColumn<?, ?> col_libelle1;
     @FXML
    private TableColumn<?, ?> col_image1;
   
    ObservableList<exercicecategorie> Listt;


    ObservableList<exercicecategorie> Listq;
    ObservableList<exercicecategorie> Listm  ;
    ObservableList<exercice> Listp;
    ObservableList<exercice> dataList;

    
    
     String imgG="";
      String imgG1="";
      String VID="";
       String DOC="";
    List<String> type;
    @FXML
    private AnchorPane libelleInput;
    @FXML
    private Button addcaButton;
    @FXML
    private Button addImage;
    @FXML
    private ImageView imgView;
    @FXML
    private TextField imageInput;
    @FXML
    private TextField libelleInput1;
    @FXML
    private Button updateca;
       private int chosenId;
          private int chosenId1;
           private int categoriea;
    @FXML
    private Button delete;
    @FXML
    private TableColumn<?, ?> categorie;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> imageexercice;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> video;
    @FXML
    private TableColumn<?, ?> document;
    @FXML
    private TextField categorieInput;
    @FXML
    private TextField nomInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField videoInput;
    @FXML
    private TextField docIndput;
    @FXML
    private Button importImage;
    @FXML
    private Button Importvideo;
    @FXML
    private Button Importdoc;
    @FXML
    private Button addexercice;
    @FXML
    private Button deleteexercice;
    @FXML
    private Button updateexercice;
    @FXML
    private ImageView img1;
    @FXML
    private TableView<exercice> table_exercice;
    @FXML
    private TextField imageInput1;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    private FileChooser fileChooser ;
    private ExtensionFilter filter;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button stop;
    @FXML
    private Slider volumeSlider;
    @FXML
    private TextField filterField;
    @FXML
    private ComboBox<exercicecategorie> combo;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView back;
    @FXML
    private ImageView sepa;
    @FXML
    private Button stat;
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type=new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");
        type.add("*.mp4");
        type.add("*.pdf");
    
     
        col_libelle1.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_image1.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
 
categorie.setCellValueFactory(new PropertyValueFactory<>("exercicecategorie_id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        imageexercice.setCellValueFactory(new PropertyValueFactory<>("image"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
        video.setCellValueFactory(new PropertyValueFactory<>("video"));
        document.setCellValueFactory(new PropertyValueFactory<>("docs"));
       
        
        
       serviceexercicecategorie sp=new serviceexercicecategorie();
        Listt = sp.getDatacategorie();
        exercicecategorie s =new exercicecategorie();
                    setCellValueFromTableTOTextField();
                    table_exercicecategorie1.setItems(Listt);
                     combo.setItems(Listt);
                   
                 
           
              
                
                   
                 
                           

        
        
        serviceexercice ex=new serviceexercice();
        Listp = ex.getDataexercice();
        setCellValueFromTableTOTextFields ();
       table_exercice.setItems(Listp);
      /* DoubleProperty widht = mediaView.fitWidthProperty();
              DoubleProperty height = mediaView.fitHeightProperty();
              widht.bind(Bindings.selectDouble(mediaView.sceneProperty(), "widht"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
                volumeSlider.setValue(mediaPlayer.getVolume() * 100);
                 volumeSlider.valueProperty().addListener(new InvalidationListener(){
            @Override
            public void invalidated(Observable observable) {
            mediaPlayer.setVolume(volumeSlider.getValue()/100);
            
            
            
            }
                 
                 
                 
                 
                 });*/

       
        
        
    }    

  

    @FXML
    private void import_image(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            imgG=fc.getAbsoluteFile().toURI().toString();
            System.out.println(imgG);
            Image i = new Image(imgG);
            imgView.setImage(i);
        }
        fc.exists();
}
    
  @FXML
    private void Addca(ActionEvent event) {
         String libelle = libelleInput1.getText();
        String image = imageInput.getText();
        System.out.println(image);
        
      
        
       
        
        serviceexercicecategorie ps =new serviceexercicecategorie();
        ps.ajouter(new exercicecategorie(libelle,imgG));
        
         serviceexercicecategorie sp = new serviceexercicecategorie();
        Listt = sp.getDatacategorie();
            table_exercicecategorie1.setItems(Listt);
         
    }
private void setCellValueFromTableTOTextField () {
    table_exercicecategorie1.setOnMouseClicked (new EventHandler<MouseEvent> (){
        @Override
        public void handle (MouseEvent event) {
            exercicecategorie pl = table_exercicecategorie1.getItems ().get (table_exercicecategorie1.getSelectionModel ().getSelectedIndex());
            
            chosenId = pl.getId();
            libelleInput1.setText(pl.getLibelle());
            imageInput.setText(pl.getImage());
           
            Image i = new Image(pl.getImage());
            imgView.setImage(i);
        }
        });
        
}    
private void setCellValueFromTableTOTextFields () {
    table_exercice.setOnMouseClicked (new EventHandler<MouseEvent> (){
        @Override
        public void handle (MouseEvent event) {
            exercice c = table_exercice.getItems ().get (table_exercice.getSelectionModel ().getSelectedIndex());
            
            chosenId1 = c.getId();
            //categorieInput.setText(c.getExercicecategorie_id());
            
            //categoriea = c.getExercicecategorie_id();
         categorieInput.setText(String.valueOf(c.getExercicecategorie_id()));
                 nomInput.setText(c.getNom());
            imageInput1.setText(c.getImage());
      
            descriptionInput.setText(c.getDescription());
            videoInput.setText(c.getVideo());
            docIndput.setText(c.getDocs());
            
                  Image i = new Image(c.getImage());
            img1.setImage(i);
                     media = new Media(c.getVideo());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
           
        
        }
        });
    
}
 @FXML
    private void select(ActionEvent event) {
     exercicecategorie s = new exercicecategorie();
     //String s;
     s=combo.getSelectionModel().getSelectedItem();
     categorieInput.setText(String.valueOf(s.getId()));
     
        
      
    }
@FXML
    private void importvideo(ActionEvent event) throws Exception {
        fileChooser  =new FileChooser();
        filter = new ExtensionFilter("choisir un video","*.mp4");
        fileChooser.setSelectedExtensionFilter(filter);
        File file = fileChooser.showOpenDialog(null);
        if(file!=null)
            System.out.println(file.getName());
            //mediaPlayer.stop();
            VID=file.getAbsoluteFile().toURI().toString();
        media = new Media(file.toURI().toURL().toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        media.toString();
        
   
    }
    
        


    @FXML
    private void updatecategorie(ActionEvent event) throws SQLException {
        String libelle = libelleInput1.getText();
        String image = imageInput.getText();
        System.out.println(image);
        
     
        
        serviceexercicecategorie ps =new serviceexercicecategorie();
  ps.update(new exercicecategorie(libelle,imgG),chosenId);        
         serviceexercicecategorie sp = new serviceexercicecategorie();
        Listt = sp.getDatacategorie();
            table_exercicecategorie1.setItems(Listt);
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        serviceexercicecategorie ps =new serviceexercicecategorie();
        System.out.println(chosenId);
        ps.delete(chosenId);
        serviceexercicecategorie sp = new serviceexercicecategorie();
         Listt = sp.getDatacategorie();
            table_exercicecategorie1.setItems(Listt);
        
    }

    @FXML
    private void addImage(ActionEvent event) {
         FileChooser f =new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            imgG1=fc.getAbsoluteFile().toURI().toString();
            System.out.println(imgG1);
            Image i = new Image(imgG1);
            img1.setImage(i);
        }
        fc.exists();
    }

   

    @FXML
    private void importdoc(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter(" PDF", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            DOC=fc.getAbsoluteFile().toURI().toString();
            System.out.println(DOC);
             Image i = new Image(DOC);
            //DOC.setImage(i);
        }
        fc.exists();
    }
    

    @FXML
    private void addexercice(ActionEvent event) {
           if(verifUserChamps() ){
                        if ( controlSaisie()){
      
      int exercicecategorie_id = Integer.parseInt(categorieInput.getText());
        
         
       //int exercicecategorie_id = categorieInput1.getSelectedItem().toString;
        //  categorieInput1.setItems(null);
          //   Integer.parseInt(categorieInput1.setItems(e.getExercicecategorie_id()));
        // int exercicecategorie_id = Integer.parseInt(combo.getItems());
   
         String nom = nomInput.getText();
        String image = imageInput1.getText();
         String description = descriptionInput.getText();
         String video = videoInput.getText();
        String docs = docIndput.getText();
        serviceexercice ps =new serviceexercice();
        media.toString();
        ps.ajouter(new exercice(exercicecategorie_id,nom,imgG1,description,VID,docs));
        
         serviceexercice sp = new serviceexercice();
        Listp = sp.getDataexercice();
            table_exercice.setItems(Listp);
    }}}
    

    @FXML
    private void deleteexercice(ActionEvent event) throws SQLException {
        
        
        serviceexercice ps =new serviceexercice();
        System.out.println(chosenId1);
        ps.delete(chosenId1);
        serviceexercice sp = new serviceexercice();
        Listp = sp.getDataexercice();
            table_exercice.setItems(Listp);
        
    }

    @FXML
    private void updateexercice(ActionEvent event) throws SQLException {
        
         int exercicecategorie_id = Integer.parseInt(categorieInput.getText());
             String nom = nomInput.getText();
        String image = imageInput1.getText();
         String description = descriptionInput.getText();
         String video = videoInput.getText();
        String docs = docIndput.getText();
        serviceexercice ps =new serviceexercice();
        media.toString();
  
     System.out.println(chosenId1);
        
  ps.update(new exercice(exercicecategorie_id,nom,imgG1,description,VID,docs),chosenId1);        
       serviceexercice sp = new serviceexercice();
        Listp = sp.getDataexercice();
            table_exercice.setItems(Listp);
        
    }

    @FXML
    private void play(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void pause(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void stop(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getStopTime());
        mediaPlayer.stop();
    }
    @FXML
    void search_exercice(){
        categorie.setCellValueFactory(new PropertyValueFactory<>("exercicecategorie_id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        imageexercice.setCellValueFactory(new PropertyValueFactory<>("image"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
        video.setCellValueFactory(new PropertyValueFactory<>("video"));
        document.setCellValueFactory(new PropertyValueFactory<>("docs"));
           serviceexercice ex=new serviceexercice();
        dataList = ex.getDataexercice();
        setCellValueFromTableTOTextFields ();
       table_exercice.setItems(dataList);
      
       
       FilteredList<exercice> filteredData= new FilteredList<>(dataList,b ->true);
       filterField.textProperty().addListener((observable,oldValue,newValue) -> {
           
           filteredData.setPredicate(t -> {
               if (newValue == null || newValue.isEmpty()){return true;}
               String lowerCaseFilter = newValue.toLowerCase();
               if(t.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
               }
               else if(t.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
               }
               else 
               return false;
            
               
           });
           
       });
       SortedList<exercice> sortedData = new SortedList<>(filteredData);
       sortedData.comparatorProperty().bind(table_exercice.comparatorProperty());
       table_exercice.setItems(sortedData);
       
       
       
        
    
    
    
    
    }

   
    
   public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
//        tfimage.setStyle(styledefault);
        nomInput.setStyle(styledefault);
        descriptionInput.setStyle(styledefault);
     

     
       
 
//
//        if (tfimage.getText().equals("")) {
//            tfimage.setStyle(style);
//            verif = 1;
//        }
       
        if ( nomInput.getText().equals("")) {
             nomInput.setStyle(style);
            verif = 1;
        }
         
        if (descriptionInput.getText().equals("")) {
            descriptionInput.setStyle(style);
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
         

//        if(checkIfStringContainsNumber(tfimage.getText())){
//            alert.setContentText("L'image ne doit pas contenir des chiffres");
//            alert.showAndWait();
//            return false;
//        }
                if(checkIfStringContainsNumber(nomInput.getText())){
            alert.setContentText("Le nom ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber(descriptionInput.getText())){
            alert.setContentText("La description ne doit pas contenir des chiffres");
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
    private void statestique(ActionEvent event) {
          try {

            Parent parent = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
          
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

   
    

  







    

