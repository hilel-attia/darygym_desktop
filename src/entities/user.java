  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author hilel
 */
public  class user {
    
    private int id;
    private String username;
    private String nom_complet;
    private String email;
    private String password;
    private String image;
    private String role;
   

    
    public user(int id,String username,String nom_complet, String email, String password, String image, String role) {
        this.id=id;
        this.username = username;
        this.nom_complet= nom_complet;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        

    }
     public user(String username,String nom_complet, String email, String password, String role) {
       
        this.username = username;
        this.nom_complet = nom_complet;
        this.email = email;
        this.password = password;
        this.role = role;
        

    }
     
     
  public user(int id ,String username,String nom_complet, String email, String password, String role) {
       this.id = id;
        this.username = username;
        this.nom_complet = nom_complet;
        this.email = email;
        this.password = password;
        
     
        this.role = role;
        

    }
  
   
  
 
   
   
   

    public user() {
    }

    public user(String password) {
        this.password = password;
    }

    public user(String username, String email) {
        this.username = username;
        this.email = email;
      
    }
 public user(int id,String username, String nom_complet) {
     this.id=id;  
     this.username = username;
        this.nom_complet = nom_complet;
      
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNomcomplet(String nom_complet) {
        this.nom_complet = nom_complet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNomcomplet() {
        return nom_complet;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public String getRole() {
        return role;
    }



    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final user other = (user) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
