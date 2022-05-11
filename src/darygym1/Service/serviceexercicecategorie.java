/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import darygym1.utills.DataSource;
import entity.exercicecategorie;
import java.sql.Connection;
import java.sql.Date;
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
 * @author fadib
 */
public class serviceexercicecategorie implements IService<exercicecategorie> {
    
     private Connection cnx = DataSource.getInstance().getConnection();
    private Statement ste;
    private PreparedStatement pre;
 public serviceexercicecategorie() {
        try {
            ste = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
 
 
   

    @Override 
    public void ajouter(exercicecategorie t) {
        try {
            String req = "INSERT INTO exercicecategorie (libelle, image) VALUES ('" + t.getLibelle()+ "','" + t.getImage() + "')";
            Statement st = cnx.createStatement();
            
            st.executeUpdate(req);
            System.out.println("exercicecategorie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
    
     

    public void modifier(exercicecategorie t ) {
        try {
            String req = "UPDATE exercicecategorie SET libelle='" + t.getLibelle()+ "',image='" + t.getImage()+ "' WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("exercicecategorie modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    public boolean delete(int id) throws SQLException {
        
        
            System.out.println("exist");
        pre=cnx.prepareStatement("DELETE FROM exercicecategorie where id = (?);");
        pre.setInt(1,id);
            System.out.println(pre.execute());
       return true;
       
        
    }
     public int update(exercicecategorie t,int id) throws SQLException {
       
   
        pre=cnx.prepareStatement("UPDATE exercicecategorie SET libelle=? , image = ?  WHERE id = ?");

    pre.setString(1, t.getLibelle());
    
    
    pre.setString(2,t.getImage());
    
    pre.setInt(3,id);
    pre.executeUpdate();
    
    return 0;
      
    }

   
    public void supprimer(exercicecategorie t) {
        try {
            String req = "DELETE FROM exercicecategorie where id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("exercicecategorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<exercicecategorie> afficher() {
        List<exercicecategorie> list = new ArrayList<>();
        
        try {
            String req = "SELECT * FROM exercicecategorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new exercicecategorie(rs.getInt(1), rs.getString("libelle"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public ObservableList<exercicecategorie> getDatacategorie() {
            ObservableList<exercicecategorie> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from exercicecategorie ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new exercicecategorie(rs.getInt(1),rs.getString(2),rs.getString(3))  );
            }
        } catch (Exception e) {
        }
        return list;
    
    }
    public ObservableList<exercicecategorie> getDatacategoriename() {
            ObservableList<exercicecategorie> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = cnx.prepareStatement("select id  from exercicecategorie ");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new exercicecategorie(rs.getInt("id"))  );
            }
        } catch (Exception e) {
        }
        return list;
    
    }
    
   
    
 
}

    
    
    
