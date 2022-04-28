/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.util;

/**
 *
 * @author Hasni safe
 */

    
    
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Rania
 */
public class ExcelAp {
    private Connection connection;
    private ResultSet resultSet ;
    private PreparedStatement pste;
     public void Excel() throws SQLException, IOException{
      
     connection = ConnectionUtil.connectdb();
      pste= connection.prepareStatement("select * from reclamation");
     //ResultSet resultSet = statement.executeQuery("select * from db ");
     // rs = pst.executeQuery("select * from users  ");
      resultSet=pste.executeQuery("SELECT * FROM reclamation");
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("List of reclamation");
      
      XSSFRow row = spreadsheet.createRow(1);
      XSSFCell cell;
      cell = row.createCell(1);
      cell.setCellValue("id");
      cell = row.createCell(2);
      cell.setCellValue("nom");
      cell = row.createCell(3);
      cell.setCellValue("prenom");
      cell = row.createCell(4);
      cell.setCellValue("description");
      cell = row.createCell(5);
      cell.setCellValue("type");
      cell = row.createCell(6);
      cell.setCellValue("statut");
      int i = 2;
    
      

      while(resultSet.next()) {
         row = spreadsheet.createRow(i);
         cell = row.createCell(1);
         cell.setCellValue(resultSet.getString("id"));
      	  cell = row.createCell(2);
         cell.setCellValue(resultSet.getString("nom"));
         cell = row.createCell(3);
         cell.setCellValue(resultSet.getString("prenom"));
          cell = row.createCell(4);
         cell.setCellValue(resultSet.getString("description"));
          cell = row.createCell(5);
         cell.setCellValue(resultSet.getString("type"));
      	 cell = row.createCell(6);
         cell.setCellValue(resultSet.getString("statut"));
         i++;
      }

      FileOutputStream out=null;
    try {
        out = new FileOutputStream(new File("C:\\Users\\Hasni safe\\Desktop\\Excel.csv"));
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ExcelAp.class.getName()).log(Level.SEVERE, null, ex);
    }
    workbook.write(out);
    try {
        out.close();
    } catch (IOException ex) {
        Logger.getLogger(ExcelAp.class.getName()).log(Level.SEVERE, null, ex);
    }
      System.out.println("Fichier Créer");
        
        

}

}
    


