/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.MyListener;
import models.Offres;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    
        private Offres Offres;
    private MyListener myListener;

     String uploads = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\achraf\\DaryGym2\\src\\img";
    private String path = "", imgname = "", fn="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
     
     public void setData(Offres Offres, MyListener myListener) {
        this.Offres = Offres;
        this.myListener = myListener;
        nameLabel.setText(""+Offres.getTitre());
        priceLable.setText(""+ Offres.getPrix());
      img.setImage(new Image("file:" + uploads + Offres.getImage()));
     }

    @FXML
    private void click(MouseEvent event) {
            myListener.onClickListener(Offres);

    }
    
}
