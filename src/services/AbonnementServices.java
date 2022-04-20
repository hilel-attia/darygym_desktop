/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Abonnement;
import utils.DataSource;

/**
 *
 * @author LENOVO
 */
public class AbonnementServices implements IAbonnementServices {
    
    
     Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void AjouterAbonnement(Abonnement a) {
        try {
             String req = "INSERT INTO abonnement (offres_id,duree  ) VALUES ('" + a.getOffres_id() + "','" + a.getDuree() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
          public void SupprimerAbonnement(String duree){
       
       String requete = "delete from abonnement where duree=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,duree);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            assert duree !=null;
           Logger.getLogger(OffresServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    

@Override
   public void ModifierAbonnement(Abonnement a, Integer offres_id) {
        	 
		 try{
            
        String req2 = "UPDATE `Abonnement` SET `offres_id`=?,`duree`=?  WHERE offres_id=? ";
                PreparedStatement st = cnx.prepareStatement(req2);
		
           
            st.setInt(1, a.getOffres_id());
            st.setString(2,a.getDuree());


            st.setInt(3,offres_id);
                st.executeUpdate();
                
                 System.out.println("abonnement mis à jour avec succès !");
                 System.out.println(a.toString());
        }catch (SQLException ex) {
            System.out.println(a.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(OffresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

        
    

    @Override
    public List<Abonnement> afficher() {
        List<Abonnement> list = new ArrayList<>();
        
        try {
            String req = "SELECT offres_id , duree FROM Abonnement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Abonnement( rs.getInt("offres_id"),rs.getString("duree")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
 

//    public void rechercher(String index){
//List<Trip> result = afficher().stream().filter(line -> index.equals(line.getOffre())).collect(Collectors.toList());
//                    System.out.println("----------");
//                    result.forEach(System.out::println);
////
//}
//   public void TrierParId (){
// TripServices sa = new TripServices();
//List<Trip> TrierParId = sa.afficher().stream().sorted(Comparator.comparing(Trip::getId_trip)).collect(Collectors.toList());
////                            TrierParId.forEach(System.out::println);
//}
@Override
   public int modifier (Abonnement a){
String sq13="UPDATE `Abonnement` SET `offres_id`=?,`duree`=? WHERE offres_id =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setInt(1, a.getOffres_id());
            pst.setString(2, a.getDuree());
            pst.setInt(3, a.getOffres_id());
                        
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(OffresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
   public List<Abonnement> ListClasse1(String o ) {
        List<Abonnement> Mylist = new ArrayList<>();
        try {
            String requete = "select offres_id,duree,from  Abonnement where duree LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, o);
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Abonnement pre = new Abonnement();
              
            pre.setOffres_id(e.getInt("offres_id"));
            pre.setDuree(e.getString("duree"));
           
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    
    
    
}
