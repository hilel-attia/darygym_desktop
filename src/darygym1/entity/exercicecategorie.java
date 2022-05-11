/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author fadib
 */
public class exercicecategorie {
     private int id;
    private String libelle;
    private String image;
   

    public exercicecategorie(String libelle, String image) {
        this.libelle = libelle;
        this.image = image;
    
    }
     public exercicecategorie(int id ,String libelle) {
       this.id =id ;
         this.libelle = libelle;
       
    
    }
    
    
    public exercicecategorie(int id,String libelle, String image) {
        this.id = id;
        this.libelle = libelle;
        this.image = image;
       
    }

    
    public exercicecategorie(int id) {
        this.id = id;
    }

    public exercicecategorie() {
         this.id = id;
        this.libelle = libelle;
        this.image = image;
       
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
     @Override
    public String toString() {
        return   libelle;
    }


    
    
}
    

