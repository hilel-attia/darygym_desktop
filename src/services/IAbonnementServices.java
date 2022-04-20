/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.Abonnement;

/**
 *
 * @author LENOVO
 */
public interface IAbonnementServices {
    
     
     public void AjouterAbonnement(Abonnement a);
public void SupprimerAbonnement(String duree);
//public void ModifierTrip(Trip p);
         public List<Abonnement> afficher();
        public void ModifierAbonnement(Abonnement a, Integer offres_id);
        public int modifier (Abonnement a);
    
    
}
