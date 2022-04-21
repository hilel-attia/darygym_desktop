/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.utiles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;

/**
 *
 * @author delll
 */ 
// en connecte a une seule base de donnée mich les donée ma yodkhlouch b3athhom 
public class Myconnection {
    public String url ="jdbc:mysql://localhost:3306/darygym_database"; 
    public String login = "root" ;
    public String pwd = ""; 
     private Connection cnx ; 
     private static Myconnection instance;
    public Myconnection(){ 
        try { 
           cnx = DriverManager.getConnection(url, login , pwd);
           System.out.println(" votre connexion est etablie ");
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
    } 
     public static Myconnection getInstance() {  //sincletin c'est un patron de conception 
        if(instance == null)
            instance = new Myconnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
