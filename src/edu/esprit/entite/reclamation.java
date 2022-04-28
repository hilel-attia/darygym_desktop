/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entite;

/**
 *
 * @author ASUS
 */
public class reclamation {


  
   private int id;
    private String titre;
    private String description;
    //private String created_At;
    private String prenom;
    private String nom;
    private String type;
    private String statut;
     
    
    public reclamation() {
    }

    public reclamation(int id, String nom, String description, String prenom, String titre, String type, String statut) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        //this.created_At = created_At;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
        this.statut = statut;
    }

    public reclamation(String titre, String description, String prenom, String nom, String type, String statut) {
        this.titre = titre;
        this.description = description;
        //this.created_At = created_At;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
        this.statut = statut;
    }

 
    public reclamation(int id) {
        this.id = id;
    }

    public reclamation( String description, String prenom, String nom, String type, String statut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public reclamation(int aInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prenom=" + prenom + ", nom=" + nom + ", type=" + type + ", statut=" + statut + '}';
    }
    
    

    

   

   
}

     
    
    

