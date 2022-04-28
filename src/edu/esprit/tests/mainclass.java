/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;
import edu.esprit.entite.reclamation;
import edu.esprit.entite.reponse;
import edu.esprit.services.reclamationcrud;
import edu.esprit.services.reponsecrud;
import edu.esprit.util. Myconnection;
import java.util.ArrayList;
import java.util.List;
import static java.util.logging.Level.ALL;


/**
 *
 * @author ASUS
 */
public class mainclass {
    public static void main (String[]args){
       
//reclamationcrud rs=new reclamationcrud();
//reclamation re = new reclamation("test", "hhhhhhh", "llllllllllll", "hasni", "faible", "non");
//rs.ajouter(re);
     // System.out.println(re);
    //   rs.supprimer(620);
    
        //System.out.println(rs.afficher());        

   
    
reponsecrud reps=new reponsecrud();
reponse rep = new reponse("new", "new");
reps.ajouter(rep);
//reps.supprimer(605);

System.out.println(reps.afficher());
//ArrayList<reponse> l = (ArrayList) reps.afficher();
   //     for (reponse elem : l) {
      //   System.out.println(elem);


    //}
    }
}
