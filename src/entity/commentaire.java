/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;



/**
 *
 * @author fadib
 */
public class commentaire {
    
      private int id;
    private int user_id;
    private int exercice_id;
    private String content;
    
    private Date publishedat;
   

    public commentaire(int user_id, int exercice_id,String content,Date publishedat ) {
        this.user_id = user_id;
        this.exercice_id = exercice_id;
        this.content = content;
        this.publishedat = publishedat;
    
    }
    
    
    public commentaire(int id,int user_id, int exercice_id,String content,Date publishedat ) {
        this.id = id;
       this.user_id = user_id;
        this.exercice_id = exercice_id;
        this.content = content;
        this.publishedat = publishedat;
       
    }

    
    public commentaire(int id) {
        this.id = id;
    }
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
     public int getExercice_id() {
        return exercice_id;
    }

    public void setExercice_id(int exercice_id) {
        this.exercice_id = exercice_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedat() {
        return publishedat;
    }

    public void setPublishedat(Date publishedat) {
        this.publishedat = publishedat;
    }
}
