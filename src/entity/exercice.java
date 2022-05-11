/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.scene.media.Media;

/**
 *
 * @author fadib
 */
public class exercice {
      private int id ;
      private int exercicecategorie_id ;
    private String nom;
    private String image;
      private String description;
        private String video;
        private String  docs ;
   

    public exercice(int exercicecategorie_id  , String nom,String image ,String description ,String video ,String docs) {
        this.exercicecategorie_id = exercicecategorie_id;
        this.image = image;
        this.nom = nom;
        this.description = description;
        this.video = video;
        this.docs = docs;
    
    }
    
    
    
    public exercice(int id ,int exercicecategorie_id ,String image , String nom ,String description ,String video ,String docs) {
       this.id = id ;
        this.exercicecategorie_id = exercicecategorie_id;
        this.image = image;
        this.nom = nom;
        this.description = description;
        this.video = video;
        this.docs = docs;
    
    }

    
    public exercice(int id) {
        this.id = id;
    }


    public exercice() {
              this.id = id ;
        this.exercicecategorie_id = exercicecategorie_id;
        this.image = image;
        this.nom = nom;
        this.description = description;
        this.video = video;
        this.docs = docs;
    }

  

    

    

   

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExercicecategorie_id() {
        return exercicecategorie_id;
    }

    public void setExercicecategorie_id(int exercicecategorie_id) {
        this.exercicecategorie_id = exercicecategorie_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
     public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
     public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }
     @Override
    public String toString() {
        return "exercice {" + "id=" + id + ", exercicecategorie_id=" + exercicecategorie_id + ", image=" + image + ", nom=" + nom  + ", description=" + description + ", video=" + video+ ", docs=" + docs +'}';
    }
}
