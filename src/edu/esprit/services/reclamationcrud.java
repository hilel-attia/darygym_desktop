/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import edu.esprit.entite.reclamation;
import edu.esprit.entite.reponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.esprit.util.Myconnection; 
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class reclamationcrud implements IService <reclamation> {
   Connection cnx = Myconnection.getInstance().getCnx();
             
   @Override
 
   public ObservableList<reclamation> afficher() {
        {
            String req = "SELECT * FROM reclamation ";
            ObservableList<reclamation> Reclamation = FXCollections.observableArrayList();
            try {
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(req);
                while (rs.next()) {
                    reclamation r = new reclamation();      
                    r.setNom(rs.getString("nom"));
                    r.setPrenom(rs.getString("prenom"));
                    r.setTitre(rs.getString("titre"));
                    r.setDescription(rs.getString("description"));
                    r.setStatut(rs.getString("statut"));
                    r.setType(rs.getString("type"));
                    
                    Reclamation.add(r);
                }
            } catch (SQLException ex) {
                Logger.getLogger(reclamationcrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Reclamation;
        }
    }
   
   
     public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void supprimerPartitre(String titre) {
        
        try {String req = "DELETE FROM reclamation WHERE titre='" + titre + "'";
             Statement ste = cnx.createStatement();
             ste .executeUpdate(req);
            System.out.println("reclamation supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(reclamationcrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @Override
      public void ajouter(reclamation re)  {
        try {
          String req = "INSERT INTO `reclamation` (`titre`, `description`,`prenom`,`nom`,`type`,`statut`) VALUES (?,?,?,?,?,?)";
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,re.getTitre());
            ps.setString(2, re.getDescription());
            ps.setString(3, re.getPrenom());
            ps.setString(4, re.getNom());
            ps.setString(5, re.getType());
            ps.setString(6, re.getStatut());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
       
    }
       public List<reclamation> getreclamationBynom(String targetString) {
        List<reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation where `nom` like '%" + targetString +"%'";
            Statement st = cnx.createStatement();
            ResultSet resultSet = st.executeQuery(req);
            while(resultSet.next()){
               reclamation re = new reclamation (
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("description"),
                    resultSet.getString("prenom"),
                    resultSet.getString("titre"),
                    resultSet.getString("type"),
                    resultSet.getString("statut"));
                list.add(re);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

   @Override
    public void modifier(reclamation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<reclamation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void supprimer(reclamation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ajouter(reponse rep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   

    
    
}


