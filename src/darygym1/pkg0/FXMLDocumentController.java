/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym1.pkg0;

import Service.serviceexercice;
import Service.serviceexercicecategorie;
import com.mysql.jdbc.MySQLConnection;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entity.exercice;
import entity.exercicecategorie;
import java.io.File;
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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import static javax.management.Query.value;
import static jdk.nashorn.internal.objects.NativeJava.type;
import utills.DataSource;

/**
 *
 * @author fadib
 */
public class FXMLDocumentController implements Initializable {
        
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
    private TableColumn<?, ?> col_id1;
     @FXML
    private TableColumn<?, ?> col_image1;
   
    ObservableList<exercicecategorie> Listt;
    ObservableList<exercicecategorie> Listq;
    ObservableList<exercice> Listp;
    
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
    @FXML
    private Button delete;
    @FXML
    private TableColumn<?, ?> id_exercice;
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
    private MediaView media;
    @FXML
    private TableView<exercice> table_exercice;
    @FXML
    private TextField imageInput1;
    @FXML
    private ComboBox<String> categorieInput1;



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
         col_id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_libelle1.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_image1.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
id_exercice.setCellValueFactory(new PropertyValueFactory<>("id"));  
categorie.setCellValueFactory(new PropertyValueFactory<>("exercicecategorie_id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        imageexercice.setCellValueFactory(new PropertyValueFactory<>("image"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
        video.setCellValueFactory(new PropertyValueFactory<>("video"));
        document.setCellValueFactory(new PropertyValueFactory<>("docs"));
        
        
       serviceexercicecategorie sp=new serviceexercicecategorie();
        Listt = sp.getDatacategorie();
                    setCellValueFromTableTOTextField();

       
        table_exercicecategorie1.setItems(Listt);
        serviceexercice ex=new serviceexercice();
        Listp = ex.getDataexercice();
        setCellValueFromTableTOTextField1 ();
       table_exercice.setItems(Listp);
        
        
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
private void setCellValueFromTableTOTextField1 () {
    table_exercice.setOnMouseClicked (new EventHandler<MouseEvent> (){
        @Override
        public void handle (MouseEvent event) {
            exercice pl = table_exercice.getItems ().get (table_exercice.getSelectionModel ().getSelectedIndex());
             int exercicecategorie_id = Integer.parseInt(categorieInput.getText());
        
         

            chosenId = pl.getId();
           
            imageInput.setText(pl.getImage());
           
            Image i = new Image(pl.getImage());
            img1.setImage(i);
        }
        });


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
        ps.delete(chosenId);
        serviceexercicecategorie sp = new serviceexercicecategorie();
         Listt = sp.getDatacategorie();
            table_exercicecategorie1.setItems(Listt);
        
    }

    @FXML
    private void addImage(ActionEvent event) {
         FileChooser f=new FileChooser();
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
    private void importvideo(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("mp4 files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            VID=fc.getAbsoluteFile().toURI().toString();
            System.out.println(VID);
            Media i = new Media(VID);
           // media.setm(i.mp4);
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
      
        int exercicecategorie_id = Integer.parseInt(categorieInput.getText());
        
         
       //int exercicecategorie_id = categorieInput1.getSelectedItem().toString;
        //  categorieInput1.setItems(null);
          //   Integer.parseInt(categorieInput1.setItems(e.getExercicecategorie_id()));

         String nom = nomInput.getText();
        String image = imageInput1.getText();
         String description = descriptionInput.getText();
         String video = videoInput.getText();
        String docs = docIndput.getText();
        System.out.println(image);
        serviceexercice ps =new serviceexercice();
        ps.ajouter(new exercice(exercicecategorie_id,nom,imgG1,description,VID,docs));
        
         serviceexercice sp = new serviceexercice();
        Listp = sp.getDataexercice();
            table_exercice.setItems(Listp);
    }

    @FXML
    private void deleteexercice(ActionEvent event) {
    }

    @FXML
    private void updateexercice(ActionEvent event) {
    }

    @FXML
    private void combobox(ActionEvent event) {
       
    }
   
    

  
}






    

