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
import utils.DataSource;
import models.Offres;

/**
 *
 * @author LENOVO
 */
public class OffresServices implements IOffresServices {
    
    
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void AjouterOffres(Offres c) {
        try {
             String req = "INSERT INTO offres (image,titre ,description, prix ) VALUES ('" + c.getImage() + "','" + c.getTitre() + "','" + c.getDescription() + "','" + c.getPrix() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
          public void SupprimerOffres(String titre){
       
       String requete = "delete from offres where titre=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1,titre);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            assert titre !=null;
           Logger.getLogger(OffresServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
    }
    

@Override
   public void ModifierOffres(Offres o, String image) {
        	 
		 try{
            
        String req2 = "UPDATE `offres` SET `image`=?,`titre`=?,`description`=?,`prix`=?  WHERE image=? ";
                PreparedStatement st = cnx.prepareStatement(req2);
		
           
            st.setString(1, o.getImage());
            st.setString(2,o.getTitre());
             st.setString(3,o.getDescription());
             st.setFloat(4,o.getPrix());

            st.setString(5,image);
                st.executeUpdate();
                
                 System.out.println("offres mis à jour avec succès !");
                 System.out.println(o.toString());
        }catch (SQLException ex) {
            System.out.println(o.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(OffresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

        
    

    @Override
    public List<Offres> afficher() {
        List<Offres> list = new ArrayList<>();
        
        try {
            String req = "SELECT image , titre, description, prix FROM offres";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Offres( rs.getString("image"),rs.getString("titre"),rs.getString("description"),rs.getFloat("prix")));
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
   public int modifier (Offres o){
String sq13="UPDATE `offres` SET `image`=?,`titre`=?,`description`=?,`prix`=?WHERE image =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, o.getImage());
            pst.setString(2, o.getTitre());
                        pst.setString(3, o.getDescription());

            pst.setFloat(4, o.getPrix());
            pst.setString(5, o.getImage());
                        
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(OffresServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
   public List<Offres> ListClasse1(String o ) {
        List<Offres> Mylist = new ArrayList<>();
        try {
            String requete = "select image,titre,description,prix from offres where titre LIKE ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, o);
      ResultSet e = pst.executeQuery();
            while (e.next()) {
                Offres pre = new Offres();
              
            pre.setImage(e.getString("image"));
            pre.setTitre(e.getString("titre"));
            pre.setDescription(e.getString("description"));
            pre.setPrix(e.getInt("prix"));
           
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    
}
