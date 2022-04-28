/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.gui;

import edu.darygym.entity.Evenement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author delll
 */
public class PhotoAffController implements Initializable {

    @FXML
    private ImageView image;
    private Image image1;
    Evenement env = new Evenement();
    Connection cnx = null ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadDate() ;
        } catch (SQLException ex) {
            Logger.getLogger(PhotoAffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //image1 = new Image("../../../assets/" + env.getImage());
      //  System.out.println(env.getImage());

    }

    private void LoadDate() throws SQLException {
        cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/darygym_database", "root", "");
 String req = "Select * from evenement where id= 137";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
                        while(rs.next()){

            String x = rs.getString("image");
            System.out.println(x);
                        
                        image1 = new Image("\\assets\\" +x);
                               image.setImage(image1);

        System.out.println(image1);
                        }
    }

}
