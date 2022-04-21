/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.test;

import edu.darygym.entity.Evenement;
import edu.darygym.entity.Archive; 
import edu.darygym.entity.Reservation;
import java.util.List;
import edu.darygym.services.ServiceEvenement;
import edu.darygym.services.ServiceArchive;
import edu.darygym.services.ServiceReservation;
import edu.darygym.utiles.Myconnection;
import java.security.Provider.Service;
import java.sql.SQLException;
/**
 *
 * @author delll
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class MainClass {
    
    public static void main(String[] args) throws SQLException {
        
        Evenement p1 = new Evenement(3,"evenement1", "test1","22/12/2022","test4","test5","test7","test8","test9");
        Archive ar1 = new Archive (1,"test1","test1","test11","11");
        Reservation rev = new Reservation ("tzstreservation","reservation","reservation6","50933958","reservation4");
        
        ServiceArchive sa = new ServiceArchive(); 
        // ServiceEvenement sp = new ServiceEvenement();
        ServiceReservation re = new ServiceReservation();
        // List<Evenement> p =sp.getAll(); 
        List<Archive> ar = sa.getAll(); 
        
          System.out.println(ar);
      // System.out.println(p);
        // sp.ajouter(p1);
        //sp.ajouter(p2);
        //sp.ajouter2(p3);
        //sp.ajouter2(p4);*/
      //  sa.ajouter(ar1); 
       // sp.supprimer(127);
        re.ajouter(rev);
    }
    
}
