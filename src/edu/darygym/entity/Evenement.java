/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.entity;

/**
 *
 * @author delll
 */
public class Evenement {
  private int id;
    private String image;
    private String lieu;
    private String date;
    private String description;
    private String nom;
    private String type;
    private String prix;
    private String datefin;

    public Evenement(int id, String image, String lieu, String date, String description, String nom, String type, String prix, String datefin) {
        this.id = id;
        this.image = image;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.datefin = datefin;
    } 

    public Evenement() {
    }

    public Evenement(String image, String lieu, String date, String description, String nom, String type, String prix, String datefin) {
        this.image = image;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.datefin = datefin;
    }
    
    
    
    
    

    public Evenement(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String lieu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getlieu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", image=" + image + ", lieu=" + lieu + ", date=" + date + ", description=" + description + ", nom=" + nom + ", type=" + type + ", prix=" + prix + ", datefin=" + datefin + '}';
    }

    
       
}
