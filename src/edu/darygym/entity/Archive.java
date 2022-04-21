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
public class Archive {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   private int id;
    private String description;
    private String image;
    private String nomarchive;
    private String idarchive;
     
     public Archive() {
    }
    
   public Archive(int id , String description, String image, String nomarchive, String idarchive) {
       this.id=id; 
        this.description = description;
        this.image = image;
        this.description = nomarchive;
        this.idarchive = idarchive;
    }

    public Archive(String description, String image, String nomarchive, String idarchive) {
        this.description = description;
        this.image = image;
        this.nomarchive = nomarchive;
        this.idarchive = idarchive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNomarchive() {
        return nomarchive;
    }

    public void setNomarchive(String nomarchive) {
        this.nomarchive = nomarchive;
    }

    public String getIdarchive() {
        return idarchive;
    }

    public void setIdarchive(String idarchive) {
        this.idarchive = idarchive;
    }

    @Override
    public String toString() {
        return "Archive{" + "id=" + id + ", description=" + description + ", image=" + image + ", nomarchive=" + nomarchive + ", idarchive=" + idarchive + '}';
    }   
}


