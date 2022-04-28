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
public class reponse {
    
    
      private int id;
   
    private String description;
    private reclamation reclamationid;
    private String email;
    //private String datedebut;
    //private String datefin;

    public reponse() {
    }

    public reponse(String description, reclamation reclamationid, String email) {
        this.description = description;
        this.reclamationid = reclamationid;
        this.email = email;
       // this.datedebut = datedebut;
        //this.datefin = datefin;
    }

    public reponse(String description, String email) {
        this.description = description;
        this.email = email;
       // this.datedebut = datedebut;
       // this.datefin = datefin;
    }

    public reponse(int id, String description, reclamation reclamationid, String email) {
        this.id = id;
        this.description = description;
        this.reclamationid = reclamationid;
        this.email = email;
      //  this.datedebut = datedebut;
       // this.datefin = datefin;
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

    public reclamation getReclamationid() {
        return reclamationid;
    }

    public void setReclamationid(reclamation reclamationid) {
        this.reclamationid = reclamationid;
    }
   
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public reponse(reclamation reclamationid) {
        this.reclamationid = reclamationid;
    }

    @Override
    public String toString() {
        return "reponse{" + "id=" + id + ", description=" + description + ", reclamationid=" + reclamationid + ", email=" + email + '}';
    }
     

    
    
}
