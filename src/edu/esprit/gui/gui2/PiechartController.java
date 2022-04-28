/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui.gui2;

import javax.activation.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

import edu.esprit.util.Myconnection;

/**
 * FXML Controller class
 *
 * @author kachouri
 */

public class PiechartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ResultSet rs1;
    ResultSet rs2;
    ResultSet rs3;
    ResultSet rs4;
    @FXML
    private javafx.scene.chart.PieChart pieChart;
       Connection cnx = Myconnection.getInstance().getCnx();
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        
        Statement st1;
        Statement st2;
        Statement st3;
        Statement st4;
        
        try {
            st1 = cnx.createStatement();
            st2 = cnx.createStatement();
            st3 =cnx.createStatement();
            st4 =cnx.createStatement();
            
            String sqlENC="select COUNT(*) from `reclamation` WHERE type='moyenne'";
            String sqlENC1="select COUNT(*) from `reclamation` WHERE type='faible'";
            String sqlENC2="select COUNT(*) from `reclamation` WHERE type='elevee'";
           
    
            rs1 = st1.executeQuery(sqlENC);
            
            rs2 = st2.executeQuery(sqlENC1);
            
            rs3 = st3.executeQuery(sqlENC2);
            
            
            rs1.next();
            rs2.next();
            rs3.next();
           
            //labval.setText(Integer.toString(rs1.getInt(1)));
            ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new javafx.scene.chart.PieChart.Data("moyenne", rs1.getInt(1)),
                new javafx.scene.chart.PieChart.Data("faible", rs2.getInt(1)),
                new javafx.scene.chart.PieChart.Data("fort", rs3.getInt(1))
        );
        pieChart.setData(pieChartData);
        pieChart.setClockwise(false);
        
         } catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }    
           
        
        
    
}