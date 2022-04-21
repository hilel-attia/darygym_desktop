/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.services;

import edu.darygym.entity.Evenement;
import edu.darygym.utiles.Myconnection; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceEvenement implements IService<Evenement> {

    Connection cnx = Myconnection.getInstance().getCnx();
 @Override
      
    public void ajouter(Evenement p) throws SQLException{
        try {
            String req = "INSERT INTO `evenement` (`lieu`,`image`, `date`,`description`,`nom`,`type`,`prix`,`datefin`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getLieu());
            ps.setString(2, p.getImage());
            ps.setString(3, p.getDate());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getNom());
            ps.setString(6, p.getType());
            ps.setString(7, p.getPrix());
            ps.setString(8, p.getDatefin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement p) {
        try {
            String req = "UPDATE `Evenement` SET `lieu` = '" + p.getLieu() + "', `date` = '" + p.getDate() + "',`description` = '"+ p.getDescription() +"',`nom =` '"+ p.getNom() +"',`type =`'"+ p.getType() +"',`prix =`'"+ p.getPrix() +"',`datefin =`'"+ p.getDatefin() +"', WHERE `evenement`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evenement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> getAll() {
        List<Evenement> list = new ArrayList<>();
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Evenement p = new Evenement(rs.getString("nom"), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
