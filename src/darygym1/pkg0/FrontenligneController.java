/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym1.pkg0;

import Service.serviceexercice;
import Service.serviceexercicecategorie;
import entity.exercice;
import entity.exercicecategorie;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MyListener1;

/**
 * FXML Controller class
 *
 * @author fadib
 */
public class FrontenligneController implements Initializable {
      String uploads = "C:\\Users\\fadib\\Desktop\\darygym1.0.1\\src\\img";
      String imgG="";
          List<String> type;
    @FXML
    private TextField tfRecherche;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label DESC;
    private MyListener1 myListener;
    List<exercice> exercice = new ArrayList<>();
     List<exercicecategorie> exercicecategorie = new ArrayList<>();
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    serviceexercice sp = new serviceexercice();
    serviceexercicecategorie sp1 = new serviceexercicecategorie();
    @FXML
    private MediaView mediaView;
     private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Pane fullscreen;
    @FXML
    private Slider progressBar;
    @FXML
    private Button video;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        type=new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");
        
        // TODO

         exercice = sp.getDataexercicecategorie();
          exercicecategorie =sp1.getDatacategorie();
         
        if (exercice.size() > 0) {
            setChosenFruit(exercice.get(0));
            
            myListener = new MyListener1() {
                public void onClickListener(exercice exercice) {
                    setChosenFruit(exercice);
                }     
            };
        }
        int column = 0;
        int row = 1;
        try {
             for (int i = 0; i < exercicecategorie.size(); i++) {
                
            
            for (int j = 0; j < exercice.size(); j++) {
                 if(i==j){
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Item1.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

        Item1Controller itemController = fxmlLoader.getController();
                itemController.setData(exercice.get(j),myListener,exercicecategorie.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

            }}}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    
    
    
     private void setChosenFruit(exercice exercice) {
        fruitNameLable.setText(exercice.getNom());
        fruitPriceLabel.setText("" + exercice.getExercicecategorie_id());
        DESC.setText(exercice.getDescription());
   fruitImg.setImage(new Image("file:" + uploads + exercice.getImage()));
     //fruitImg.setImage(exercice.getImage());
           
            Image i = new Image(exercice.getImage());
            fruitImg.setImage(i);
            
              media = new Media(exercice.getVideo());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
    }

    @FXML
    private void rechercheProd(MouseEvent event) {
    }


    @FXML
    private void video(ActionEvent event) {
        
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("showvideo.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
          
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void fullscreen(MouseEvent event) {
        
//((Stage)mediaView.getScene().getWindow()).setFullScreen(true);
Stage stage = (Stage) mediaView.getScene().getWindow();
    stage.setFullScreen(true);
    

    }

    
    
}