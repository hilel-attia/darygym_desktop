/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.services;
import edu.darygym.entity.Archive;
import java.sql.SQLException;
import java.util.List;

public interface IService1 <Archive>{
    public void ajouter(Archive ar) throws SQLException;
    public void supprimer(int id);
    public List<Archive> getAll();
    
} 
