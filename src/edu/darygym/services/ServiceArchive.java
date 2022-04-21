/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.services;


import edu.darygym.entity.Archive;
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
/**
 *
 * @author delll
 */
public  class ServiceArchive implements IService1<Archive> {
    

    Connection cnx = Myconnection.getInstance().getCnx();
 @Override
      
    public void ajouter(Archive ar) throws SQLException{
        try {
            String req = "INSERT INTO `archive` (`nomarchive`,`description`,`image`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ar.getNomarchive());
            ps.setString(2, ar.getDescription());
            ps.setString(3, ar.getImage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `archive` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Archive deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     @Override
    public List<Archive> getAll() {
        List<Archive> list = new ArrayList<>();
        try {
            String req = "Select * from archive";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
               Archive ar = new Archive(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
                list.add(ar);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
