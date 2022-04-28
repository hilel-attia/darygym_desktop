/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entite.reclamation;
import edu.esprit.entite.reponse;
import edu.esprit.util.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */

    
public class reponsecrud implements IService <reponse> {
   Connection cnx = Myconnection.getInstance().getCnx();
   
   @Override
 public ObservableList<reponse> afficher() {
        {
            String req = "SELECT * FROM reponse ";
            ObservableList<reponse> Reponse = FXCollections.observableArrayList();
            try {
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    reponse rep = new reponse();      
                    rep.setDescription(rs.getString("description"));
                   // r.setDatedebut(rs.getString("datedebut"));
                    //r.setDatefin(rs.getString("datefin"));
                    rep.setEmail(rs.getString("email"));
                    rep.setReclamationid(new reclamation(1));
                    
                    
                    Reponse.add(rep);
                }
            } catch (SQLException ex) {
                Logger.getLogger(reponsecrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Reponse;
        }
    }
    
   
     public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reponse` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reponse deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void supprimerParemail(String email) {
        
        try {String req = "DELETE FROM reponse WHERE email='" + email + "'";
             Statement ste = cnx.createStatement();
             ste .executeUpdate(req);
            System.out.println("reponse supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(reponsecrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      @Override
       public void ajouter(reponse rep)  {
        try {
          String req = "INSERT INTO `reponse` ( `description`,`email`) VALUES (?,?)";
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,rep.getDescription());
            ps.setString(2, rep.getEmail());
          //  ps.setString(3, rep.getDatedebut());
           // ps.setString(4, rep.getDatefin());
            
          ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
}

    @Override
    public void modifier(reponse entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(reponse entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
