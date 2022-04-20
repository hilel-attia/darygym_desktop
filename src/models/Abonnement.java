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
      private int offres_id  ;
   private String duree ;

    public Abonnement() {
    }

    public Abonnement(int offres_id, String duree) {
        this.offres_id = offres_id;
        this.duree = duree;
    }

    public Abonnement(int id, int offres_id, String duree) {
        this.id = id;
        this.offres_id = offres_id;
        this.duree = duree;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffres_id() {
        return offres_id;
    }

    public void setOffres_id(int offres_id) {
        this.offres_id = offres_id;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
   
     @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", offres_id=" + offres_id + ", duree=" + duree + '}';
    }
    
}
