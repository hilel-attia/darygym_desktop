/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.darygym.services;
import edu.darygym.entity.Evenement;
import java.sql.SQLException;
import java.util.List;

public interface IService <Event>{
    public void ajouter(Evenement p) throws SQLException;
    public void supprimer(int id);
    public void modifier(Evenement p);
    public List<Evenement> getAll();
    
} 


