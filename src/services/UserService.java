/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.MD5Utils;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;



/**
 *
 * @author hilel
 */
public class UserService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public UserService() {
        conn = DataSource.getInstance().getCnx();
    }

    public void ajouterUersonne(user u) {
        String req = "insert into user (username,nom_complet) values ('" + u.getUsername()+ "','" + u.getNomcomplet()+ "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean ajouterUserPst(user u) {
        String req = "insert into user (username,nom_complet,email,password,roles) values (?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getNomcomplet());
            pst.setString(3, u.getEmail());
            pst.setString(4, MD5Utils.cryptage(u.getPassword()));
            pst.setString(5, u.getRole());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean modifierUserPst(user u) {
        String req = "update user set username = ? , nom_complet = ? , email= ?  ,password= ?, roles = ?  where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getNomcomplet());
            pst.setString(3, u.getEmail());
  
            pst.setString(4, u.getPassword());
          
            pst.setString(5, u.getRole());
            pst.setInt(6, u.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean suppUserPst(user u) {
        String req = "delete from user where id = ?";

        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public ObservableList<user> readAll() {
         String req = "Select * from `user`";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("username"), rs.getString("nom_complet"), rs.getString("email"),  rs.getString("password"),rs.getString("roles")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public user getUserbyEmailPass(String email, String pass) {

        String req = "select * from user where email = '" + email + "' and password = '" + MD5Utils.cryptage(pass) + "'";

        user u = new user();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            if (rs.first()) {
                u = new user(rs.getInt("id"), rs.getString("username"), rs.getString("nom_complet"), rs.getString("email"),  rs.getString("password"),rs.getString("roles"));
            }
            System.out.println(u);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public ObservableList<String> GetNames() {
        String req = "select username,nom_complet from user";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("username") + " " + rs.getString("nom_complet"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<String> GetNamesLivreur() {
        String req = "SELECT concat(username,' ',nom_complet) as full_name from user where roles='Coach'";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("full_name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public ObservableList<String> GetNamesClient() {
        String req = "SELECT concat(username,' ',nom_complet) as full_name from user where roles='Adherant'";

        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(rs.getString("full_name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<user> recherche(String searchby, String value) {
        String req = "select * from user where " + searchby + " like '%" + value + "%'";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("username"), rs.getString("nom_complet"), rs.getString("email"),  rs.getString("password"),rs.getString("roles")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<user> tri(String value) {
        String req = "select * from user order by " + value;

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                 list.add(new user(rs.getInt("id"), rs.getString("username"), rs.getString("nom_complet"), rs.getString("email"),  rs.getString("password"),rs.getString("roles")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<user> filterRole(String value) {
        String req = "select * from user where roles = '" + value + "'";

        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                list.add(new user(rs.getInt("id"), rs.getString("username"), rs.getString("nom_complet"), rs.getString("email"),  rs.getString("password"),rs.getString("roles")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean MailExiste(String mail) {
        try {
            ResultSet res = ste.executeQuery("Select * from user where email='" + mail + "';");
            return res.next();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;

    }

    public int GetIdUser(String value) {
        String req = "select id from user where CONCAT(username,' ',nom_complet) = '" + value + "';";

        int id = 0;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                id = rs.getInt("id");

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String GetNomutilisateurbyId(int id) {
        String req = "select nom from user where id ='" + id + "'";
        String nom = null;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {//parcourir le resultset
                nom = rs.getString("username");

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }

    public boolean ResetPassword(String pass, int id) throws SQLException {
        String sql = "UPDATE user SET password=? WHERE id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, MD5Utils.cryptage(pass));
            pst.setInt(2, id);

            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean update(user t){
        String sql = "UPDATE user SET username = ? , nom_complet = ? , email = ? WHERE id = ?";
        try {
            pst = conn.prepareStatement(sql);
            System.out.println(t);
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getNomcomplet());
            pst.setString(3, t.getEmail());

            pst.setInt(4, t.getId());
            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
public int rowUSER(){
        ObservableList<user> liste = FXCollections.observableArrayList();
        String req = "SELECT * FROM user";
        int i=0;
        
        try {
            conn = DataSource.getInstance().getCnx();
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            user user1;
            while (rs.next()){
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (ste != null) {
        try {
            ste.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return i;
        
}
}
