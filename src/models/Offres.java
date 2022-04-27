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
public class Offres {
    
   private int id ;
      private String image  ;
   private String titre ;
   private String description;
      private float  prix  ;

    public Offres(int id, String image, String titre, String description, float prix) {
        this.id = id;
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    public Offres(String image, String titre, String description, float prix) {
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }
      public Offres( String titre, float prix) {
          this.titre = titre;
       
        this.prix = prix;
    }

    public Offres() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Offres{" + "id=" + id + ", image=" + image + ", titre=" + titre + ", description=" + description + ", prix=" + prix + '}';
    }
    
      
      

    
    
}
