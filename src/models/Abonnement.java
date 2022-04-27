/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author LENOVO
 */
public class Abonnement {
    
    
      private int id ;
   private String titre ;
   private String duree ;

    public Abonnement(int id, String titre, String duree) {
        this.id = id;
        this.titre = titre;
        this.duree = duree;
    }

    public Abonnement(String titre, String duree) {
        this.titre = titre;
        this.duree = duree;
    }

    public Abonnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", titre=" + titre + ", duree=" + duree + '}';
    }

   
    
}
