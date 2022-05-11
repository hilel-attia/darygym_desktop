/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.exercicecategorie;
import java.util.List;

/**
 *
 * @author fadib
 */
public interface IService<exercicecategorie>  {
    
    public void ajouter(exercicecategorie t);
  
    public void modifier(exercicecategorie t);
      public void supprimer(exercicecategorie t);
    public List<exercicecategorie> afficher();

    
}
