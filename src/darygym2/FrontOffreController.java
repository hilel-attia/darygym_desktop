/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.MyListener;
import models.Offres;
import services.OffresServices;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontOffreController implements Initializable {

    
             String uploads = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\achraf\\DaryGym2\\src\\img";

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
        private MyListener myListener;

    List<Offres> Offres = new ArrayList<>();

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    OffresServices sp = new OffresServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // TODO

         Offres = sp.afficher();
        if (Offres.size() > 0) {
            setChosenFruit(Offres.get(0));
            
            myListener = new MyListener() {
            @Override
                public void onClickListener(Offres Offres) {
                    setChosenFruit(Offres);
                }     
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Offres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

        ItemController itemController = fxmlLoader.getController();
                itemController.setData(Offres.get(i),myListener);

                if (column == 3) {
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

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    
    
    
     private void setChosenFruit(Offres Offres) {
        fruitNameLable.setText(Offres.getTitre());
        fruitPriceLabel.setText("" + Offres.getPrix());
        DESC.setText(Offres.getDescription());
     fruitImg.setImage(new Image("file:" + uploads + Offres.getImage()));
    }

    @FXML
    private void rechercheProd(MouseEvent event) {
    }

    @FXML
    private void DESC(MouseEvent event) {
    }

    @FXML
    private void ASC(MouseEvent event) {
    }
    
}
