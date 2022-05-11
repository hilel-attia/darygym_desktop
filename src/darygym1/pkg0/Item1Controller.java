/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym1.pkg0;

import entity.exercice;
import entity.exercicecategorie;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import main.MyListener1;

/**
 * FXML Controller class
 *
 * @author fadib
 */
public class Item1Controller implements Initializable {
 
          List<String> type;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    //private ImageView img;
    private exercice exercice;
    private exercicecategorie exercicecategorie;
      private MyListener1 myListener;
      String uploads = "C:\\Users\\fadib\\Desktop\\darygym1.0.1\\src\\img";
    private String path = "", imgname = "", fn="";
    @FXML
    private ImageView img;
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type=new ArrayList<>();
        type.add("*.jpg");
        type.add("*.png");
      
        
        
        // TODO
    } 
    public void setData(exercice exercice, MyListener1 myListener , exercicecategorie exercicecategorie) {
        this.exercice = exercice;
        this.exercicecategorie =exercicecategorie;
        this.myListener = myListener;
        nameLabel.setText(""+exercice.getNom());
        
        priceLable.setText(""+ exercicecategorie.getLibelle());
  img.setImage(new Image("file:" + uploads + exercice.getImage()));
     
    //Image i = new Image(exercice.getImage());
        // img.setImage(i);
           
     
     }

    @FXML
    private void click(MouseEvent event) {
          myListener.onClickListener(exercice);

    }
    
}
