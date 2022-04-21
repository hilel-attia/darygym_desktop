/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.exercice;
import entity.exercicecategorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utills.DataSource;

/**
 *
 * @author fadib
 */
public class serviceexercice implements Iserviceexercice<exercice> {
    
     private Connection cnx = DataSource.getInstance().getConnection();
    private Statement ste;
    private PreparedStatement pre;
 public serviceexercice() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouter(exercice r){
//        String req = "insert into billet(chair_billet,voyage_num,terminal,portail,embarquement,localisation_id)"
//                    + "values( '" + b.getChairBillet() + "', '" + b.getVoyageNum() + "',"+ "" + b.getTerminal() + "', '" + b.getPortail() + "', '" ++ "', '" + b.getEmbarquement() + "', '" + b.getLocalisation()  + ")";
        try {
            String req2 = "insert into exercice (exercicecategorie_id,nom,image,description,video,docs)"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req2);
            pst.setInt(1, r.getExercicecategorie_id());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getImage());
            pst.setString(4, r.getDescription());
            pst.setString(5, r.getVideo());
            pst.setString(6, r.getDocs());
            pst.executeUpdate();
            System.out.println("exercice ajoutee avec succes");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
    }
        
    /*public void ajouter(exercice t) {
        try {
            String req = "INSERT INTO exercice (exercicecategorie_id, image,nom,description,video,docs) VALUES ('" + t.getExercicecategorie_id()+ "','" + t.getImage()+ "','" + t.getNom()+ "','" + t.getDescription()+ "','" + t.getVideo()+ "','" + t.getDocs()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("exercice ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
  
    
     

    public void modifier(exercice t) {
        try {
            String req = "UPDATE exercice SET exercicecategorie_id='" + t.getExercicecategorie_id()+ "',image='" + t.getImage()+ "',nom='" + t.getNom()+ "',description='" + t.getDescription()+ "',video='" + t.getVideo()+ "',docs='" + t.getDocs()+  "' WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("exercice modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public void supprimer(exercice t) {
        try {
            String req = "DELETE FROM exercice where id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("exercice supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<exercice> afficher() {
        List<exercice> list = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM exercice";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new exercice(rs.getInt("id"),rs.getInt("exercicecategorie_id"), rs.getString("image"), rs.getString("nom"),rs.getString("description"),rs.getString("video"),rs.getString("docs")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public ObservableList<exercice> getDataexercice() {
            ObservableList<exercice> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from exercice ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new exercice(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7))  );
            }
        } catch (Exception e) {
        }
        return list;
    
    }
   
    
    }

    
    
    
