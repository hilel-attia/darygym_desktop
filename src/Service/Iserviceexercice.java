/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author fadib
 */
public interface Iserviceexercice<exercice> {
    
    
    public void ajouter(exercice t);
  
    public void modifier(exercice t);
      public void supprimer(exercice t);
    public List<exercice> afficher();

    

    
}
