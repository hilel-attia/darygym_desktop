/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.services;
import edu.darygym.entity.Archive;
import edu.darygym.entity.Reservation;
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
public class ServiceReservation implements IService2<Reservation> {
    Connection cnx = Myconnection.getInstance().getCnx();
 @Override
    public void ajouter(Reservation rev) throws SQLException{
        try {
            String req = "INSERT INTO `reservation` (`namepart`,`usernamepart`,`ville`,`numtelephonepart`,`email_participent`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, rev.getNamepart());
            ps.setString(2, rev.getUsernamepart());
            ps.setString(3, rev.getVille());
            ps.setString(4,rev.getNumtelephonepart()); 
            ps.setString(5,rev.getEmail_participent()); 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }
    
    
         @Override
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
               Reservation ar = new Reservation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
                list.add(ar);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
  }
