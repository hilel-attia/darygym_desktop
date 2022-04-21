/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Service.serviceexercicecategorie;
import entity.exercicecategorie;
import java.sql.SQLException;
import utills.DataSource;
/**
 *
 * @author fadib
 */
public class NewMain {

   public static void main(String[] args)  {
        serviceexercicecategorie su = new serviceexercicecategorie() {};
//su.modifier(new Evenement(9,"libi","lib","iheb","AZE"));        
su.ajouter(new exercicecategorie("fedi","rrgr"));
//su.supprimer(new Evenement(10));
  
    
   }
}
