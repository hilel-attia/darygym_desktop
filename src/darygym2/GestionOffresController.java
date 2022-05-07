/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darygym2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Offres;
import org.controlsfx.control.Notifications;
import services.OffresServices;
import utils.DataSource;
import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javax.swing.JFileChooser;



/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionOffresController implements Initializable {

    
    

   String uploads = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\achraf\\DaryGym2\\src\\img\\";
    private String path = "", imgname = "", fn="";
    
    
    @FXML
    private TableView<Offres> tableoffres;
    @FXML
    private TableColumn<Offres,String> imagea;
    @FXML
    private TableColumn<Offres, String> titrea;
    @FXML
    private TableColumn<Offres,String> descriptiona;
    @FXML
    private TableColumn<Offres,Float> prixa;
    
    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField text_rech;
    
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
      int  index= -1; 
  
    
    
  

   /* private ComboBox<String> cbrechpar;
    private TextField tfrecherche;
    
    /**
     * Initializes the controller class.
     */
    
    OffresServices off = new OffresServices();
    @FXML
    private ImageView imgOffre;
    @FXML
    private TableColumn<Offres, Integer> colid;
    @FXML
    private TextField tfid;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     //   cbrechpar.setItems(FXCollections.observableArrayList("titre", "prix"));
    updateTable();
ObservableList<Offres>  list =  FXCollections.observableArrayList();
ObservableList<Offres>  list1 =  FXCollections.observableArrayList();

        // TODO
       
                 tableoffres.refresh();

                       affiche();
        search_user() ; 

       

    
        // TODO
    }    
    
     public void init() {
        updateTable();
        tftitre.clear();
        tfdescription.clear();
        tfprix.clear();
        
    }
     
     
    public void updateTable() {
        System.out.println("aaaaaaaaa");
        ObservableList<Offres> offres = off.readAll();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        imagea.setCellValueFactory(new PropertyValueFactory<>("image"));
        titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptiona.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixa.setCellValueFactory(new PropertyValueFactory<>("prix"));
    
        tableoffres.setItems(offres);
    }
    
   

    @FXML
    private void selected(MouseEvent event) {
        index=tableoffres.getSelectionModel().getSelectedIndex();
        if (index<= -1)
        {return; } 
//        tfimage.setText(imagea.getCellData(index).toString());
       imgOffre.setImage(new Image("file:" + uploads +  imagea.getCellData(index).toString() ));
                       tfid.setText(colid.getCellData(index).toString());

                tftitre.setText(titrea.getCellData(index).toString());
                tfdescription.setText(descriptiona.getCellData(index).toString());
                tfprix.setText(prixa.getCellData(index).toString());
    }

    @FXML
    private void supprimerOffres(ActionEvent event) {
    OffresServices Offres = new OffresServices();
    Offres ls = new Offres();
    ls = tableoffres.getSelectionModel().getSelectedItem();
                
        System.out.println("id"+ls.getId());
        
       Offres.SupprimerOffres(ls.getId());
           
        
        affiche();
        
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Alert");
        al.setContentText("offre supprimé AVEC SUCCeS");
        al.setHeaderText(null);
        al.show() ; 
        
        tableoffres.refresh();

    }

    @FXML
    private void modifierOffres(ActionEvent event) {
          if(verifUserChamps() ){ 
                        if ( controlSaisie()){

        String titre=tftitre.getText(); 
        String description=tfdescription.getText();
        Float prix=Float.parseFloat(tfprix.getText());
        int id= Integer.valueOf(tfid.getText());


        OffresServices sp = new OffresServices();
        Offres c = new Offres();
    //  c.setImage(image);
 c.setTitre(titre);
        c.setDescription(description);
        c.setPrix(prix);
        c.setId(id);
      c.setImage(imgname);

        
        sp.modifier(c);
        
         affiche();
         Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Alert");
        al.setContentText("offre modifié AVEC SUCCeS");
        al.setHeaderText(null);
        al.show() ; 
         
         tableoffres.refresh();
         
        
        
    }}}

    @FXML
    private void ajouterOffres(ActionEvent event) {
        
        Image pho = new Image("/noti.jpg");

          if(verifUserChamps() ){ 
                        if ( controlSaisie()){

        
       
         OffresServices ts = new OffresServices();
                  OffresServices ts1 = new OffresServices();
       ts.AjouterOffres(new Offres(imgname,tftitre.getText(),tfdescription.getText(),Float.parseFloat(tfprix.getText())));
        affiche();
        Notifications notificationBuilder = Notifications.create()
                                                     .title("offre Ajouter")
                                                      .text("offre ajouter avec succée")
                                                     .graphic(new ImageView(pho))
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_RIGHT) ;
         notificationBuilder.show();
        
                 tableoffres.refresh();
                 

    }
          }}
    
    
    
     public ObservableList<Offres> show1()
    { 
       

           try {
               ObservableList<Offres> obl =FXCollections.observableArrayList();
                             Connection cnx = DataSource.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                 PreparedStatement pt= cnx.prepareStatement("select id,image,titre,description,prix from offres ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Offres ls = new Offres();
                 ls.setImage(rs.getString("image"));
                 ls.setId(rs.getInt("id"));

                 ls.setTitre(rs.getString("titre"));
                 ls.setDescription(rs.getString("description"));
                 ls.setPrix(rs.getFloat("prix"));
                 
             

                  
                  System.out.println("");
         obl.add(ls);
                      System.out.println(" ls id = "+ls.getId());
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
 public void affiche() {
        
           
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      imagea.setCellValueFactory(new PropertyValueFactory<>("image"));
      titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
      descriptiona.setCellValueFactory(new PropertyValueFactory<>("description"));
      prixa.setCellValueFactory(new PropertyValueFactory<>("prix"));
      ObservableList<Offres> obl =FXCollections.observableArrayList();
     obl=show1(); 
      tableoffres.setItems(obl);
      System.out.println(""+obl);

                      
    }

    @FXML
    private void allezversabn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/darygym2/GestionOffres.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void allezversoff(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/darygym2/GestionAbonnement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
    
    
   
     public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
//        tfimage.setStyle(styledefault);
        tftitre.setStyle(styledefault);
        tfdescription.setStyle(styledefault);
        tfprix.setStyle(styledefault);

     
       
 
//
//        if (tfimage.getText().equals("")) {
//            tfimage.setStyle(style);
//            verif = 1;
//        }
       
        if ( tftitre.getText().equals("")) {
             tftitre.setStyle(style);
            verif = 1;
        }
         
        if (tfdescription.getText().equals("")) {
            tfdescription.setStyle(style);
            verif = 1;
        }
       
        if (tfprix.getText().equals("")) {
            tfprix.setStyle(style);
            verif = 1;
        }
       
       
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show() ; 
        
        return false;
    }
    public boolean controlSaisie(){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

//        if(checkIfStringContainsNumber(tfimage.getText())){
//            alert.setContentText("L'image ne doit pas contenir des chiffres");
//            alert.showAndWait();
//            return false;
//        }
                if(checkIfStringContainsNumber(tftitre.getText())){
            alert.setContentText("Le titre ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber(tfdescription.getText())){
            alert.setContentText("La description ne doit pas contenir des chiffres");
            alert.showAndWait();
            return false;
        }
                  if(checkIfStringContainsNumber2(tfprix.getText())){
            alert.setContentText("Le prix ne doit pas contenir des caracteres");
            alert.showAndWait();
            return false;
        }
               
             

        
        return true;
    }
    
    public boolean checkIfNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Erreur de saisie");
         

       return true;
    }
    
    public boolean checkIfStringContainsNumber(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                return true;
            }
        }
        return false;
    }
    public boolean checkIfStringContainsNumber2(String str){
        for (int i=0; i<str.length();i++){
            if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
                return true;
            }
        }
        return false;
    }

    
  /*  public void SearchOffres(KeyEvent event) {
        String search = tfrecherche.getText();
        
        if (search == null) {
            updateTable();
        } else {
           
            ObservableList<Offres> list = off.recherche(search);
            titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
            prixa.setCellValueFactory(new PropertyValueFactory<>("prix"));
          
            tableoffres.setItems(list);
        }

    
        
    }   */

    @FXML
    private void tri(ActionEvent event) {
        
        OffresServices ser= new OffresServices();
        
        List<Offres> li =ser.ListClasse();
        ObservableList<Offres> data = FXCollections.observableArrayList(li);
                  

        
          
        imagea.setCellValueFactory(new PropertyValueFactory<>("image"));
      titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
      descriptiona.setCellValueFactory(new PropertyValueFactory<>("description"));
      prixa.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        
        
        tableoffres.setItems(data);
    }
    
 
    void search_user() {          
   colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        imagea.setCellValueFactory(new PropertyValueFactory<>("image"));
        titrea.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptiona.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixa.setCellValueFactory(new PropertyValueFactory<>("prix"));

      
        OffresServices ser= new OffresServices();
        
        List<Offres> li =ser.ListClasse();
        ObservableList<Offres> data = FXCollections.observableArrayList(li);
                tableoffres.setItems(data);

        FilteredList<Offres> filteredData = new FilteredList<>(data, b -> true);  
 text_rech.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches image
    } else if (person.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches titre
    }else if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches description
    }
    else if (String.valueOf(person.getPrix()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches prix
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Offres> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tableoffres.comparatorProperty());  
  tableoffres.setItems(sortedData);      
  
    }
    
    
       @FXML
    private void printpdf(ActionEvent event) throws FileNotFoundException, Exception {
                File out = new File("tableoffre.pdf") ; 
        FileOutputStream fos = new FileOutputStream(out) ;
        PDF pdf = new PDF(fos) ; 
        Page page = new Page(pdf, A4.PORTRAIT)  ; 
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD) ;
        Font f2 = new Font(pdf, CoreFont.HELVETICA) ;
        Table table = new Table() ; 
        List<List<Cell>> tabledata = new ArrayList<List<Cell>>() ;       
        List<Cell> tablerow = new ArrayList<Cell>() ; 
       
        
         Cell cell = new Cell(f1,titrea.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,descriptiona.getText());
        tablerow.add(cell) ;
        
        cell = new Cell(f1,prixa.getText());
        tablerow.add(cell) ;
        
        
        
    tabledata.add(tablerow) ; 
    
    
     
    
   OffresServices ser= new OffresServices();
        
        
        List<Offres> li =ser.ListClasse();
  int i =0 ; 
for ( i=0 ; i<li.size();i++){
 
        Cell prc = new Cell(f2,li.get(i).getTitre()) ;
         
        
        Cell gc = new Cell(f2,li.get(i).getDescription()) ; 
        Cell lc = new Cell(f2,String.valueOf(li.get(i).getPrix())) ; 
        
        tablerow = new ArrayList<Cell>() ; 
       tablerow.add(prc) ; tablerow.add(gc) ; tablerow.add(lc)  ; 
        
    tabledata.add(tablerow) ; 
    table.setData(tabledata);
    table.setPosition(10f, 60f);
    table.setColumnWidth(0, 90f); 
    table.setColumnWidth(1, 90f); 
    table.setColumnWidth(2, 90f); 
    table.setColumnWidth(3, 90f); }
    
    
    while(true){
    table.drawOn(page) ; 
    if(!table.hasMoreData()){
    table.resetRenderedPagesCount(); 
    break ;  
    
    }
    
    page=new Page(pdf,A4.PORTRAIT) ; 
    
    
    }
    
    pdf.flush();
    fos.close(); 
        System.out.println("saved to "+out.getAbsolutePath());
        
        
    }
    
    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        try (
                FileChannel in = new FileInputStream(sourceFile).getChannel();
                FileChannel out = new FileOutputStream(destFile).getChannel();) {

            out.transferFrom(in, 0, in.size());
        }
    }
   




  @FXML
    private void upload(ActionEvent event) throws IOException {
       
            JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        path= f.getAbsolutePath();
        imgname = f.getName();
        fn = imgname;
        Image getAbsolutePath = null;

        String dd = uploads + f.getName();
        File dest = new File(dd);
        this.copyFile(f, dest);

        System.out.println(dd);

        imgOffre.setImage(new Image("file:" + dest.getAbsolutePath()));

    }
    
    
}
