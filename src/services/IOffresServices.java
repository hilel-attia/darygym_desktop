/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.List;
import models.Offres;

/**
 *
 * @author LENOVO
 */
public interface IOffresServices {
    
    
     public void AjouterOffres(Offres o);
public void SupprimerOffres(String titre);
//public void ModifierTrip(Trip p);
         public List<Offres> afficher();
        public void ModifierOffres(Offres o, String image);
        public int modifier (Offres o);
    
}
